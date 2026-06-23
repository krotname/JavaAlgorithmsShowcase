# Input Parsing Rules

Parsing is part of the solution only when the kata statement makes it part of
the contract.

## Review prompts

- Keep parsing helpers small and directly tested.
- Decide whether invalid input returns a value or throws.
- Trim only when the kata permits extra whitespace.
- Preserve empty tokens when they carry meaning.
- Avoid regular expressions that hide simple delimiter rules.

## Test shape

Use one valid sample and one malformed sample for each parsing branch.
