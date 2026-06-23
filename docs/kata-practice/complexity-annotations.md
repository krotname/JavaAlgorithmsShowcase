# Complexity Annotation Notes

Complexity notes are useful when they explain a tradeoff a reviewer cannot see
from examples alone.

## Review prompts

- Express time in terms of the actual input dimensions.
- Include memory when an auxiliary collection is central to the approach.
- Mention sorting cost separately from a later linear pass.
- Avoid complexity comments on one-line code where the cost is obvious.
- Update the note when refactoring changes the data structure.

## Test link

Performance-sensitive notes should point to a boundary or large-input test.
