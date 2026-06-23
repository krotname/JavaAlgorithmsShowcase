# Recursion and Backtracking Checklist

Recursive kata need compact base cases and visible pruning rules.

## Review prompts

- The base case should be the first branch a reviewer can verify.
- Mutable path state must be restored before the method returns.
- Pruning should be covered by a test that would otherwise branch deeply.
- Stack depth should be acceptable for the largest kata input.
- A recursive helper should make the changing arguments obvious.

## Refactor note

Move formatting or sorting out of the recursive core when possible.
