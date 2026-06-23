# Java Collection Choice Notes

Collection choice should follow the kata access pattern, not habit.

## Review prompts

- Use `ArrayList` when indexed access and append order dominate.
- Use `Deque` for queue or stack behavior instead of `Stack`.
- Use `HashSet` when membership is the only requirement.
- Use `LinkedHashMap` or `LinkedHashSet` when stable iteration matters.
- Use `PriorityQueue` only when repeated min or max extraction is needed.

## Test shape

Add a case that would fail if iteration order were accidentally lost.
