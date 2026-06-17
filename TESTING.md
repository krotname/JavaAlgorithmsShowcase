# Testing Strategy

This repo keeps the current test style (JUnit 6 + jqwik + suite-based grouping).
The goal is clarity and reproducibility rather than framework churn.

## 1) Unit / Smoke

- Every solved task has direct tests in `src/test/java/<package>`.
- The smoke suite groups these checks for a fast, deterministic review signal.
- Run:

```bash
mvn -B test -Dgroups='smoke'
mvn -B test -Dtest='quality.SmokeSuite'
```

## 2) Integration

- Transaction state and ordering behavior is covered in
  `transactions.InMemoryValidationServiceTest`.
- The integration suite runs this group in CI and locally.
- Run:

```bash
mvn -B test -Dgroups='integration'
mvn -B test -Dtest='quality.IntegrationSuite'
```

## 3) Property-based

- Property tests use jqwik for invariants and edge-case generation.
- This repository groups the current property-focused tests in `quality.PropertySuite`.
- jqwik reporting is configured in `src/test/resources/junit-platform.properties`
  to keep successful CI logs compact while preserving failure diagnostics.
- Maven Surefire redirects test stdout to per-test files so CI logs show results,
  not framework chatter.
- Run:

```bash
mvn -B test -Dgroups='property'
mvn -B test -Dtest='quality.PropertySuite'
```

## 4) Static quality gates

- Static checks are split into two layers:
  - lightweight `maven verify` run in CI categories/branch matrix;
  - dedicated static-only gates in `.github/workflows/quality.yml`:
    - Checkstyle
    - PMD
    - SpotBugs

- The static workflow does not run tests and provides standalone artifacts for static tooling review.
- Checkstyle, PMD and SpotBugs are expected to stay at zero reported findings in the current baseline.
- All three static tools are enforced: violations fail the Maven build.
- Checkstyle uses `config/checkstyle/checkstyle.xml`; generated smoke wrappers are documented in
  `config/checkstyle/suppressions.xml`.
- SpotBugs compatibility exceptions are explicit in `config/spotbugs-exclude.xml`.

## 5) Additional quality checks (full build)

- Full verification includes tests, JaCoCo reporting/checks and static tools.
- Current enforced JaCoCo minimums:
  - line coverage: 70%
  - branch coverage: 70%
  - instruction coverage: 70%
- Current verified local baseline: 455 tests, 91.4% line coverage,
  85.1% branch coverage and 92.7% instruction coverage.
- Run:

```bash
mvn -B verify
```

- CI uploads `target/surefire-reports` and `target/site/jacoco`.

## 6) CI matrix

- Category workflow: smoke/integration/property tags (JDK 21)
- Full verify workflow: JDK 17 and 21
- Static workflow: static checks only (JDK 21)

## 7) UI category status

- **UI tests: N/A** for this repository (no UI module).
