# Stack and Queue Patterns

Stack and queue kata are easiest to review when the data-structure role is
named directly in the solution or test.

## Stack prompts

- Confirm the push and pop conditions are symmetric.
- Check that the final stack state is asserted, not only the return value.
- Use stack size as a cheap invariant when nested tokens are parsed.

## Queue prompts

- Keep enqueue order visible in tests.
- Cover an input that drains the queue completely.
- Add one case where the queue still has pending work after the first pass.
