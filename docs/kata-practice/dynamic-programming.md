# Dynamic Programming Review Notes

Dynamic programming solutions are easier to trust when the state definition is
written before the transition is optimized.

## Review prompts

- Name the state dimensions in code or tests.
- Add a minimal input that initializes the table.
- Add a case where the optimal choice changes between adjacent cells.
- Keep the transition formula close to the loop that applies it.
- If memory is compressed, keep one test that would catch overwrite order bugs.

## Complexity note

Record time and memory in terms of the state dimensions.
