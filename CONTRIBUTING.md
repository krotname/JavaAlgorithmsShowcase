# Contributing

Thank you for interest in this repository.

## Project scope

- Keep solutions and tests in standard Maven structure.
- Prefer clear, deterministic, and reproducible examples.
- Keep production code in `src/main/java` and tests in `src/test/java` where possible.
- Preserve existing algorithmic approach when refactoring, and add comments for non-obvious logic.

## Working on code

1. Fork or branch from `main`.
2. Implement changes with focused commits.
3. Run:
   ```bash
   mvn -B verify
   mvn -B test -Dtest='quality.SmokeSuite'
   ```
4. Open a pull request with:
   - summary of behavior changes,
   - test coverage impact,
   - risk notes if any logic is touched.
5. Keep the suite coverage clear:
   - smoke/unit: `mvn -B test -Dtest='quality.SmokeSuite'`
   - integration: `mvn -B test -Dtest='quality.IntegrationSuite'`
   - property: `mvn -B test -Dtest='quality.PropertySuite'`

## Code style

- Java 17-compatible code.
- Keep test names readable and explicit.
- Add or adapt tests for any behavior change.
  - Keep at least one unit-level test for touched classes.
  - Add/extend integration tests for `transactions` when stateful behavior changes.
  - If you add property behavior, include `@Property` tests with invariant checks.

## Code quality

- `mvn -B verify` runs the full quality stack: tests, coverage, checkstyle, PMD and SpotBugs.
- CI enforces reproducible builds on Java 17 and 21.
- Prefer explicit comments for non-obvious logic in complex methods.
- If you touch a source-compatible static API, keep test names meaningful and deterministic.
