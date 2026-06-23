# Integration Fixture Notes

Integration fixtures belong where a kata has state, ordering, or multi-step
validation that a single method assertion cannot explain.

## Review prompts

- Name the scenario in business or kata terms, not by implementation detail.
- Keep setup data small enough to inspect in one screen.
- Assert final state and the most important intermediate signal.
- Avoid sharing mutable fixture instances between tests.
- Prefer builders only when repeated setup becomes hard to read.

## Local check

Run the integration group before changing transaction ordering logic.
