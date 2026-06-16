# Codewars Solutions / Решения с Codewars

[![CI](https://github.com/krotname/CodewarsKataJava/actions/workflows/maven.yml/badge.svg)](https://github.com/krotname/CodewarsKataJava/actions/workflows/maven.yml)
[![Quality Gates](https://github.com/krotname/CodewarsKataJava/actions/workflows/quality.yml/badge.svg)](https://github.com/krotname/CodewarsKataJava/actions/workflows/quality.yml)
[![CodeQL](https://github.com/krotname/CodewarsKataJava/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/krotname/CodewarsKataJava/actions/workflows/codeql-analysis.yml)
[![Coverage Gate](https://img.shields.io/badge/coverage%20gate-JaCoCo%2070%25%2B-2ea44f)](TESTING.md)
[![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/krotname/CodewarsKataJava/badge)](https://securityscorecards.dev/viewer/?uri=github.com/krotname/CodewarsKataJava)
[![License: GPL-3.0](https://img.shields.io/badge/license-GPL--3.0-0f8a16)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17%2B-007396.svg)](https://adoptium.net/)
[![JUnit](https://img.shields.io/badge/JUnit-6-25A162.svg)](https://junit.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36.svg)](https://maven.apache.org/)

This repository is a portfolio-grade Java kata workspace focused on:
- deterministic algorithms with reproducible tests,
- explicit quality workflows (CI, CodeQL, coverage, static checks),
- readable production code and practical refactoring notes,
- bilingual project documentation.

English | [Русский](#русский)

## English

### Why this repository exists

- It demonstrates practical coding and test engineering from **problem solving** to **delivery quality**.
- All solutions are intentionally organized by difficulty/source (kyu/LeetCode/interview/transactions/other).
- Static checks and tests are visible and runnable in one place.

### Repository map

- `src/main/java` — production solutions and domain classes
- `src/test/java` — tests and quality suites
  - `quality.SmokeSuite` (unit/smoke partition)
  - `quality.IntegrationSuite` (transactions scenario tests)
  - `quality.PropertySuite` (property-based tests, currently curated)
- `.github` — CI workflows, dependabot and repo templates
- `ARCHITECTURE.md` — architectural and reviewability notes.
- `CHANGELOG.md`, `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, `SECURITY.md`, `TESTING.md` — project governance

### Testing strategy

- **Smoke / Unit baseline** — each kata has direct tests; grouped by `quality.SmokeSuite`.
- **Integration** — transaction validation state and ordering, via `quality.IntegrationSuite`.
- **Property-based** — jqwik property tests are grouped in `quality.PropertySuite`.
- **UI tests** — not applicable (`N/A` for this repository, no UI layer).
- **Static quality gates** — Checkstyle, PMD, SpotBugs run in Maven verify and fail the build on violations.
- **Static artifact capture** — reports from Checkstyle/PMD/SpotBugs are attached to CI artifacts for reviewer inspection.
- **Coverage gate** — JaCoCo fails `mvn verify` below 70% line, branch or instruction coverage.

### CI and quality signals

- GitHub Actions workflow: `.github/workflows/maven.yml`
  - category jobs (`smoke`, `integration`, `property`) on JDK 21 via JUnit tags
  - full `mvn verify` on JDK 17 and 21
  - uploaded test and coverage artifacts
- Quality workflow: `.github/workflows/quality.yml`
  - runs static checks with `checkstyle`, `pmd`, and `spotbugs` without executing tests
  - Checkstyle, PMD and SpotBugs are kept clean in the current quality baseline
  - Checkstyle uses a project-specific baseline in `config/checkstyle/checkstyle.xml`
  - Generated smoke wrappers have explicit suppressions in `config/checkstyle/suppressions.xml`
  - SpotBugs is kept clean with documented compatibility exceptions in `config/spotbugs-exclude.xml`
- Security workflow: `.github/workflows/codeql-analysis.yml`
- Dependency automation: `.github/dependabot.yml`
- Coverage tracked in JaCoCo + Codecov; the current local baseline is 455 tests with 91.4% line,
  85.1% branch and 92.7% instruction coverage.

### Portfolio checklist

- Deterministic implementations with package-localized responsibilities.
- Clear QA signals in CI (unit/integration/property + static checks).
- Explicit bilingual documentation for contributors and users.
- Reproducible Maven build commands.
- Separate quality workflow for static tooling visibility.

### Default branch governance note

`main` intentionally uses lightweight branch governance. This is a personal educational kata journal where small solution, test, and explanation updates are often pushed directly to preserve a fast practice loop. Public quality assurance comes from CI, CodeQL, quality gates, Dependabot, the Security Policy, and reproducible local commands. Substantive changes that affect kata behavior or quality policy still use the normal PR/review workflow.
The absence of branch protection on `main` is an intentional project-specific exception and should not be treated as a hardening defect.

### Особенность управления default branch

`main` намеренно остается с облегченным branch governance. Это персональный учебный kata-журнал, где небольшие правки решений, тестов и пояснений часто вносятся напрямую, чтобы сохранять быстрый цикл тренировки. Публичные гарантии качества обеспечиваются CI, CodeQL, quality gates, Dependabot, Security Policy и воспроизводимыми локальными командами. Для содержательных изменений, влияющих на поведение задач или процесс качества, по-прежнему используется обычный PR/review-подход.
Отсутствие branch protection для `main` является осознанным исключением этого проекта и не должно трактоваться как дефект hardening-модели.

### Local run

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

### What code reviewers can verify quickly

- No hidden mutation of test inputs inside algorithmic methods.
- Meaningful method-level comments in non-obvious logic.
- Deterministic edge-case handling in boundary tests.
- Governance artifacts for maintainability and process.

### Local quality policy

- Use ASCII-only patch style unless a file already contains another script.
- Keep public method behavior deterministic.
- Add/adjust tests for any changed branch behavior.
- Keep complexity-local comments in algorithmic methods.

### Architecture

- [ARCHITECTURE.md](ARCHITECTURE.md)
