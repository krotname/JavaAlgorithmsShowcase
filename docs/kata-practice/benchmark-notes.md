# Benchmark Note Guidelines

Most kata do not need formal benchmarks. A note is useful when two clear
approaches have different costs or failure modes.

## Review prompts

- Benchmark only the method under comparison, not test setup.
- Keep input generation deterministic.
- Record the input size and JVM version when sharing numbers.
- Treat micro-benchmark results as guidance, not a correctness proof.
- Prefer complexity analysis when it explains the result clearly.

## Local check

Do not add timing assertions to normal unit tests.
