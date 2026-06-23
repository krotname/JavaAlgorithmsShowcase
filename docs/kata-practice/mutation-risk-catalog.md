# Mutation Risk Catalog

Mutation is sometimes the simplest kata solution, but it should be explicit.

## Risk areas

- Sorting an input array changes caller-visible order.
- Removing from a list while iterating can skip elements.
- Reusing a buffer across calls can leak previous state.
- Updating a map value in place can affect grouped aliases.
- Passing mutable collections into helpers can hide side effects.

## Review prompt

If mutation is intentional, add a test or name that makes the contract clear.
