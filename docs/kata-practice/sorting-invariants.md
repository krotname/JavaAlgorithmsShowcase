# Sorting Invariants

Sorting kata can pass examples while still losing stability, duplicates, or
tie-breaking behavior.

## Review prompts

- State whether the sort must be stable.
- Test duplicate keys with different payload values.
- Keep comparator direction readable at the call site.
- Cover already sorted, reverse sorted, and all-equal input.
- Avoid subtraction-based comparators when values may overflow.

## Test shape

Use a named fixture for each ordering rule instead of one large sample.
