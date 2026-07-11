# Architecture

## Repository model

- `src/main/java` contains production kata implementations grouped by family (`kyu3`, `kyu4`, `kyu5`, `kyu6`, `kyu7`, `leetcode`, `interview`, `coderun`, `other`, `transactions`).
- `src/test/java` mirrors the same package layout.
- `src/test/java/quality` contains suite-level entry points for local and CI review:
  - `SmokeSuite` – fast baseline visibility of all unit tests.
  - `IntegrationSuite` – stateful transaction validation behavior.
  - `PropertySuite` – jqwik property/invariant tests.
- `.github` contains CI, templates, and dependency automation.
- `scripts/run-offline-gate.ps1` provides a network-free local Maven quality gate.

## Design principles used in this repo

- Deterministic API behavior where possible.
- No mutable side effects in pure kata methods.
- Input validation and boundary handling through explicit conditions.
- Shared conventions and reviewability:
  - test classes are colocated with implementation packages;
  - critical branches are covered by regression-style tests where possible;
  - build uses Maven with repeated quality checks in `verify`.

## Quality boundaries

- **Static gates:** Checkstyle, PMD, SpotBugs with zero-violation baselines enforced by Maven.
- **Coverage:** JaCoCo with explicit thresholds on verify.
- **CI:** split by JUnit tags (`smoke`, `integration`, `property`) plus full matrix verify.
- **Security**: CodeQL workflow and Security policy in the repo root.
- **Intentional static-analysis exceptions:** `config/checkstyle/suppressions.xml`
  documents generated smoke wrappers, while `config/spotbugs-exclude.xml` keeps
  Codewars-compatible public API names documented instead of hiding them in code.

## Future quality directions

- Increase property coverage into additional stable, pure utilities where invariant checks are natural.
- Expand domain-level integration coverage when new transaction rules are introduced.
- Keep production methods with concise intent comments for non-obvious logic.
