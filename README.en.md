# Codewars Solutions

[Russian](README.md)


This repository is a portfolio-grade Java kata workspace focused on deterministic algorithms, reproducible tests, visible quality workflows, readable production code, and bilingual project documentation.

## Why This Repository Exists

- It demonstrates practical coding and test engineering from problem solving to delivery quality.
- Solutions are organized by difficulty/source: kyu, LeetCode, interview, transactions, and other groups.
- Static checks and tests are visible and runnable in one place.

## Repository Map

- `src/main/java` - production solutions and domain classes.
- `src/test/java` - tests and quality suites:
  - `quality.SmokeSuite` for unit/smoke tests.
  - `quality.IntegrationSuite` for transaction scenario tests.
  - `quality.PropertySuite` for curated property-based tests.
- `.github` - CI workflows, Dependabot, and repository templates.
- `ARCHITECTURE.md` - architectural and reviewability notes.
- `CHANGELOG.md`, `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, `SECURITY.md`, `TESTING.md` - project governance.

## Testing Strategy

- Smoke/unit tests cover each kata directly and are grouped by `quality.SmokeSuite`.
- Integration tests cover transaction validation state and ordering through `quality.IntegrationSuite`.
- Property-based tests are grouped in `quality.PropertySuite`.
- UI tests are not applicable because this repository has no UI layer.
- Static quality gates include Checkstyle, PMD, and SpotBugs in Maven verify.
- JaCoCo fails `mvn verify` below 70% line, branch, or instruction coverage.

## CI and Quality Signals

- `.github/workflows/maven.yml` runs category jobs on JDK 21 and full `mvn verify` on JDK 17 and 21.
- The same workflow validates the strict offline PowerShell gate on Windows after an explicit dependency-prime step.
- `.github/workflows/quality.yml` runs Checkstyle, PMD, and SpotBugs without executing tests.
- `.github/workflows/codeql-analysis.yml` runs security analysis.
- `.github/dependabot.yml` keeps dependency automation visible.

## Default Branch Governance

`main` intentionally uses lightweight branch governance. This is a personal educational kata journal where small solution, test, and explanation updates are often pushed directly to keep a fast practice loop. Public quality assurance comes from CI, CodeQL, quality gates, Dependabot, the Security Policy, and reproducible local commands. Substantive changes that affect kata behavior or quality policy still use the normal PR/review workflow.

The absence of branch protection on `main` is an intentional project-specific exception and should not be treated as a hardening defect.

## Local Run

```bash
mvn -B verify
mvn -B test
mvn -B test -Dgroups='smoke'
mvn -B test -Dgroups='integration'
mvn -B test -Dgroups='property'
mvn -B -DskipTests checkstyle:check pmd:check spotbugs:check
mvn -B test -Dtest='quality.SmokeSuite'
mvn -B test -Dtest='quality.IntegrationSuite'
mvn -B test -Dtest='quality.PropertySuite'
```

Run the complete PowerShell gate without network access:

```powershell
.\scripts\run-offline-gate.ps1
```

See [`TESTING.md`](TESTING.md) for strict dependency-cache mode and the documented
cached-JUnit fallback.

## Reviewer Checklist

- No hidden mutation of test inputs inside algorithmic methods.
- Meaningful method-level comments in non-obvious logic.
- Deterministic edge-case handling in boundary tests.
- Governance artifacts for maintainability and process.

## Local Quality Policy

- Use ASCII-only patch style unless a file already contains another script.
- Keep public method behavior deterministic.
- Add or adjust tests for changed branch behavior.
- Keep comments close to complex algorithmic logic.
