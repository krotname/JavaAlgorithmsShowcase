# Refactor Safety Checklist

Kata refactors should improve readability without changing the public contract.

## Review prompts

- Run the smallest relevant test group before and after the refactor.
- Keep one commit focused on behavior and one on movement when possible.
- Avoid renaming public methods unless tests and docs move together.
- Check that helper extraction did not change mutation timing.
- Preserve boundary tests before simplifying the main algorithm.

## Local check

Use `mvn -B test -Dtest=<FocusedTest>` before a full verification run.
