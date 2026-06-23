# Hash Map Kata Patterns

Hash maps are useful when the kata needs counting, lookup, or grouping.
The important review question is which key identity is intended.

## Review prompts

- Normalize the key before storing it when comparison rules require it.
- Separate counting from answer extraction for clearer tests.
- Cover missing keys and keys with zero-like values.
- Use merge or compute methods when they improve clarity.
- Avoid leaking mutable grouped lists from helper methods.

## Local check

Add one test with colliding logical groups, such as anagrams or equal scores.
