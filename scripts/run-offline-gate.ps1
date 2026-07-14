[CmdletBinding()]
param(
    [ValidateSet('test', 'verify')]
    [string] $Goal = 'verify',

    [switch] $StrictDependencies
)

Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'

$repoRoot = Split-Path -Parent $PSScriptRoot
$pomPath = Join-Path $repoRoot 'pom.xml'
$maven = Get-Command mvn -ErrorAction Stop
$localRepository = if ($env:MAVEN_REPO_LOCAL) {
    $env:MAVEN_REPO_LOCAL
} else {
    Join-Path $env:USERPROFILE '.m2\repository'
}

[xml] $pom = Get-Content -LiteralPath $pomPath -Raw
$configuredJUnit = [string] $pom.project.properties.'junit.version'
$configuredPlatform = [string] $pom.project.properties.'junit.platform.version'
$configuredBom = Join-Path $localRepository (
    'org\junit\junit-bom\{0}\junit-bom-{0}.pom' -f $configuredJUnit
)

$mavenArguments = @(
    '--offline'
    '--batch-mode'
    '--no-transfer-progress'
    '--file'
    $pomPath
)

if (-not (Test-Path -LiteralPath $configuredBom)) {
    if ($StrictDependencies) {
        throw "The configured JUnit BOM $configuredJUnit is absent from the local Maven cache."
    }

    $configuredMajor = ([version] $configuredJUnit).Major
    $bomRoot = Join-Path $localRepository 'org\junit\junit-bom'
    $fallback = Get-ChildItem -LiteralPath $bomRoot -Directory -ErrorAction SilentlyContinue |
        Where-Object {
            $_.Name -match '^\d+\.\d+\.\d+$' -and
            ([version] $_.Name).Major -eq $configuredMajor -and
            (Test-Path -LiteralPath (Join-Path $_.FullName ("junit-bom-{0}.pom" -f $_.Name)))
        } |
        Sort-Object { [version] $_.Name } -Descending |
        Select-Object -First 1

    if ($null -eq $fallback) {
        throw "No JUnit $configuredMajor.x BOM is available in the local Maven cache."
    }

    Write-Warning ((
            "JUnit BOM $configuredJUnit is not cached; offline validation uses cached {0}. " +
            "Run with -StrictDependencies to require the exact POM version."
        ) -f $fallback.Name)
    $mavenArguments += "-Djunit.version=$($fallback.Name)"

    if ($configuredPlatform -eq $configuredJUnit) {
        $mavenArguments += "-Djunit.platform.version=$($fallback.Name)"
    }
}

Push-Location $repoRoot
try {
    & $maven.Source @mavenArguments $Goal
    if ($LASTEXITCODE -ne 0) {
        throw "Offline Maven gate failed with exit code $LASTEXITCODE."
    }
} finally {
    Pop-Location
}
