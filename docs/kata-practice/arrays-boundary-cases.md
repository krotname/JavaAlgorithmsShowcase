# Arrays Boundary Cases

Array kata usually fail at the edges before they fail in the main loop.
Use this checklist before adding or refactoring an array solution.

## Review prompts

- Empty arrays should have an explicit expected result.
- Single element input should not accidentally enter a pair-only branch.
- Repeated values should be tested separately from sorted unique values.
- Negative numbers should be covered when arithmetic comparisons are used.
- The original input should remain unchanged unless mutation is the kata goal.

## Local check

Prefer a small parameterized test table that names the edge it protects.
