# Changelog

## Unreleased

- Corrected boundary, overflow, validation, determinism and complexity defects across kata utilities.
- Hardened transaction validation and made returned status collections immutable.
- Replaced unbounded iteration in encryption and range-counting helpers with logarithmic algorithms.
- Made the generated smoke harness fail when every executable API path fails.
- Added regression coverage for scheduler extremes, queueing, Fibonacci, trailing zeros and other edge cases.
- Added a PowerShell offline quality gate with strict dependency-cache mode and a documented cached-JUnit fallback.
- Added a Windows CI job that primes dependencies and then exercises the strict offline gate.
- Expanded the verified test baseline to 594 tests.

## 1.1 - public quality hardening (2026-06-09)

- Added CI, static analysis, and dependency management updates for repository quality.
- Standardized test strategy and introduced a shared smoke harness for kata classes.
- Added dedicated integration tests for the transactions validation flow.
- Expanded README with bilingual project guidance.
- Added community/maintenance docs (`CONTRIBUTING`, `SECURITY`, `CODE_OF_CONDUCT`).
- Added a project-specific Checkstyle baseline and made Checkstyle fail the build on violations.
- Promoted PMD and SpotBugs from report-only checks to enforced quality gates.
- Updated the stable test/build toolchain, including JUnit 6, jqwik, Lombok, JaCoCo, PMD and SpotBugs.
- Removed compiler warning noise by typing a raw collection API and configuring Lombok annotation processing explicitly.
- Added JUnit Platform configuration to keep successful jqwik runs out of stdout noise.
- Redirected test stdout through Surefire files to keep CI logs compact and review-safe.
- Added repository-level line-ending normalization with `.gitattributes`.
- Expanded the meaningful test baseline to 455 tests across unit/smoke, integration and property categories.
- Set enforced JaCoCo coverage gates to 70% line, branch and instruction coverage.
- Reduced SpotBugs debt to zero reported findings with explicit compatibility exclusions for Codewars API names.
- Reduced PMD debt to zero reported findings by removing unused code/imports and simplifying safe expressions.
