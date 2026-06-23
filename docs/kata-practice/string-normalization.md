# String Normalization Checks

String kata often combine parsing, casing, trimming, and token ordering.
Make the normalization decision visible before optimizing the algorithm.

## Review prompts

- State whether whitespace is meaningful or ignored.
- Keep case folding close to the comparison that requires it.
- Split parsing from scoring so tests can isolate malformed input.
- Cover punctuation and repeated separators when the kata allows free text.
- Prefer named helper methods when the transformation has more than one step.

## Test shape

Use examples that differ by only one normalization rule.
