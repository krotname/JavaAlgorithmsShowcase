# Date-Free Kata Policy

Kata tests should stay deterministic. Time should appear only when the kata
statement explicitly requires it.

## Review prompts

- Avoid reading the current clock inside algorithmic solutions.
- Pass time as an argument when time is part of the problem.
- Keep date formatting separate from calculation logic.
- Use fixed fixtures for duration or calendar examples.
- Do not make test results depend on local timezone settings.

## Local check

Search for direct clock calls before adding a time-related kata.
