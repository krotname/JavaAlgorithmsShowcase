package algorithms.sprint5;

// https://contest.yandex.ru/contest/24810/run-report/160624089/

public class Solution {

    /*
     * Принцип работы алгоритма:
     * Используем свойство бинарного дерева поиска.
     * Если удаляемый ключ меньше значения в текущей вершине,
     * продолжаем поиск в левом поддереве.
     * Если больше — в правом.
     * Когда вершина с нужным ключом найдена, возможны три случая:
     *
     * 1) У вершины нет левого ребёнка.
     *    Тогда на её место должно встать правое поддерево.
     *
     * 2) У вершины нет правого ребёнка.
     *    Тогда на её место должно встать левое поддерево.
     *
     * 3) У вершины есть оба ребёнка.
     *    Тогда берём максимальный элемент из левого поддерева
     *    (это предшественник текущей вершины в порядке BST),
     *    записываем его значение в текущую вершину,
     *    а затем удаляем этот элемент из левого поддерева.
     *
     * Почему алгоритм корректен:
     * 1) По свойству BST искомый ключ может находиться только
     *    в одном из двух поддеревьев, поэтому спуск
     *    идёт ровно по нужному пути.
     *
     * 2) Если у удаляемой вершины не более одного ребёнка,
     *    замена вершины её единственным поддеревом
     *    сохраняет порядок ключей в дереве.
     *
     * 3) Если у вершины два ребёнка, максимум левого поддерева:
     *    - меньше значения любой вершины из правого поддерева;
     *    - не меньше всех остальных значений из левого поддерева.
     *    Поэтому после замены текущего ключа на этот максимум
     *    свойство BST сохраняется.
     *
     * 4) После этого мы удаляем из левого поддерева вершину,
     *    значение которой перенесли вверх. Так как ключи уникальны,
     *    удаляется ровно одна нужная вершина.
     *
     * Временная сложность: O(h), где h — высота дерева,
     * что в худшем случае даёт O(n), где n — число узлов в дереве.
     *
     * Дополнительная пространственная сложность: O(1), поскольку обход
     * выполняется итеративно.
     */

    public static Node remove(Node root, int key) {
        Node parent = null;
        Node current = root;

        while (current != null && current.getValue() != key) {
            parent = current;
            current = key < current.getValue() ? current.getLeft() : current.getRight();
        }

        if (current == null) {
            return root;
        }

        if (current.getLeft() != null && current.getRight() != null) {
            Node predecessorParent = current;
            Node predecessor = current.getLeft();
            while (predecessor.getRight() != null) {
                predecessorParent = predecessor;
                predecessor = predecessor.getRight();
            }

            current.setValue(predecessor.getValue());
            if (predecessorParent == current) {
                predecessorParent.setLeft(predecessor.getLeft());
            } else {
                predecessorParent.setRight(predecessor.getLeft());
            }
            return root;
        }

        Node replacement = current.getLeft() != null ? current.getLeft() : current.getRight();
        if (parent == null) {
            return replacement;
        }
        if (parent.getLeft() == current) {
            parent.setLeft(replacement);
        } else {
            parent.setRight(replacement);
        }
        return root;
    }
}
