# Graph Traversal Review Guide

Graph kata should make traversal ownership clear: building the graph, visiting
nodes, and deriving the answer are separate concerns.

## Review prompts

- Cover a disconnected component when the kata permits it.
- Add a cycle case even when the happy path is a tree.
- Assert visit order only when order is part of the contract.
- Keep visited-state updates next to enqueue or recurse operations.
- Distinguish missing nodes from nodes with no outgoing edges.

## Local check

Small hand-drawn graphs are better than large random fixtures.
