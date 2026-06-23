# Smoke Suite Mapping

The smoke suite should answer one question: can every public kata entry point
handle representative input without surprising behavior?

## Mapping rules

- Each solution class should have at least one direct smoke-level assertion.
- Smoke tests should avoid slow exhaustive fixtures.
- Boundary cases can live in focused tests when they would obscure the map.
- A new package should be visible in the suite or in a named nested group.

## Review prompt

When adding a kata, check that the fastest suite still exercises its API.
