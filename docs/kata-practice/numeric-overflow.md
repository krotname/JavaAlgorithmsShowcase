# Numeric Overflow Notes

Numeric kata should document when `int` is enough and when a wider type is
required for intermediate values.

## Review prompts

- Check multiplication before addition when both can overflow.
- Use `long` for intermediate products when limits are unclear.
- Keep modulo operations close to each arithmetic step that needs them.
- Cover zero, one, negative values, and the largest allowed input.
- Avoid comparator subtraction for possibly large values.

## Test shape

Include at least one case that is small in length but large in magnitude.
