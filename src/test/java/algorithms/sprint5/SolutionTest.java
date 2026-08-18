package algorithms.sprint5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class SolutionTest {

    @Test
    void removeReturnsNullForEmptyTree() {
        assertNull(Solution.remove(null, 1));
    }

    @Test
    void removeDeletesLeafNode() {
        Node root = node(node(null, null, 1), node(null, null, 3), 2);

        Node result = Solution.remove(root, 1);

        assertEquals(2, result.getValue());
        assertNull(result.getLeft());
        assertEquals(3, result.getRight().getValue());
    }

    @Test
    void removeDeletesNodeWithOneChild() {
        Node root = node(node(null, node(null, null, 2), 1), node(null, null, 4), 3);

        Node result = Solution.remove(root, 1);

        assertEquals(3, result.getValue());
        assertEquals(2, result.getLeft().getValue());
        assertEquals(4, result.getRight().getValue());
    }

    @Test
    void removeDeletesNodeWithTwoChildrenUsingPredecessor() {
        Node root = node(
                node(node(null, null, 1), node(null, null, 3), 2),
                node(null, null, 5),
                4);

        Node result = Solution.remove(root, 4);

        assertEquals(3, result.getValue());
        assertEquals(2, result.getLeft().getValue());
        assertNull(result.getLeft().getRight());
        assertEquals(5, result.getRight().getValue());
    }

    @Test
    void removeHandlesDeepSkewedTreeWithoutOverflowingStack() {
        Node root = node(null, null, 0);
        Node current = root;
        for (int value = 1; value < 100_000; value++) {
            Node next = node(null, null, value);
            current.setRight(next);
            current = next;
        }

        Node result = Solution.remove(root, 99_999);

        assertEquals(0, result.getValue());
        assertNull(current.getRight());
        current = result;
        for (int value = 0; value < 99_999; value++) {
            assertEquals(value, current.getValue());
            current = current.getRight();
        }
        assertNull(current);
    }

    private static Node node(Node left, Node right, int value) {
        return new Node(left, right, value);
    }
}
