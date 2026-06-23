# Kata Code Review Prompts

Reviewing kata code is faster when the reviewer checks behavior, readability,
and test evidence in the same pass.

## Prompts

- Is the public method contract visible from the test names?
- Does the solution mutate input, and is that intentional?
- Are boundary cases represented by focused tests?
- Is the algorithmic complexity acceptable for the kata limits?
- Could a helper method hide an important rule from the reader?

## Outcome

The review should leave either a stronger invariant or a clearer name.
