# Property Test Candidates

Property-based tests fit kata where examples do not cover enough input shape.
They should protect a simple invariant rather than restate the implementation.

## Good candidates

- Round-trip encoders and decoders.
- Sorting or grouping functions with order and membership invariants.
- Arithmetic helpers with identity or monotonicity rules.
- Parsers where invalid input families are easy to generate.

## Review prompts

- Keep generators narrow enough to produce useful failures.
- Add an example-based regression test after a property failure is fixed.
