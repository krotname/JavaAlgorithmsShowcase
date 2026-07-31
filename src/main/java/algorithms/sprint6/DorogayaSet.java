package algorithms.sprint6;

/*
 * Принцип работы алгоритма:
 * Нужно найти максимальное остовное дерево, поэтому используем алгоритм Краскала
 * в обратном порядке: сортируем все рёбра по убыванию веса и последовательно
 * добавляем ребро в ответ, если оно соединяет две разные компоненты связности.
 * Для проверки компонент используем DSU (Disjoint Set Union, система
 * непересекающихся множеств). DSU хранит разбиение вершин на компоненты:
 * parent[v] ведёт к представителю компоненты вершины v, а size[root] хранит
 * размер компоненты с корнем root. Операция find(v) возвращает корень
 * компоненты и сжимает путь до него, чтобы следующие запросы были быстрее.
 * Операция union(a, b) объединяет компоненты вершин a и b, если они разные.
 * Если find(a) == find(b), значит вершины уже соединены выбранными рёбрами,
 * и добавление ребра a-b создало бы цикл, поэтому такое ребро пропускаем.
 *
 * Петли не влияют на ответ: ребро из вершины в саму себя не соединяет разные
 * компоненты, поэтому DSU его не добавит. Кратные рёбра обрабатываются естественно:
 * из них раньше будут рассмотрены более тяжёлые.
 *
 * Почему алгоритм корректен:
 * В алгоритме Краскала на каждом шаге выбирается самое тяжёлое ребро, которое
 * не создаёт цикл. По свойству разреза для максимального остовного дерева такое
 * ребро можно безопасно добавить в некоторый оптимальный остов. Повторяя этот
 * шаг, получаем максимальное остовное дерево. Если после обработки всех рёбер
 * выбрано меньше n - 1 ребра, значит граф несвязный и остовного дерева нет.
 *
 * Временная сложность: O(m log m), где m — число рёбер.
 * Основное время занимает сортировка рёбер. Операции DSU работают почти за O(1),
 * точнее O(alpha(n)), где n — число вершин, а alpha(n) — обратная функция Аккермана.
 * Она растёт настолько медленно, что для практических размеров входа считается константой.
 * Пространственная сложность: O(n + m), где n — число вершин, m — число рёбер.
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

// https://contest.yandex.ru/contest/25070/run-report/162675299/

public class DorogayaSet {

    static final String FAIL = "Oops! I did it again";
    private static final int MAX_VERTICES = 200_000;
    private static final int MAX_EDGES = 200_000;

    static long solve(int n, Edge[] edges) {
        if (!isValidGraph(n, edges)) {
            return -1;
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(b.weight, a.weight));

        DSU dsu = new DSU(n);
        long totalWeight = 0;
        int usedEdges = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.from, edge.to)) {
                totalWeight += edge.weight;
                usedEdges++;

                if (usedEdges == n - 1) {
                    break;
                }
            }
        }

        if (usedEdges != n - 1) {
            return -1;
        }

        return totalWeight;
    }

    private static boolean isValidGraph(int n, Edge[] edges) {
        if (n < 1 || n > MAX_VERTICES || edges == null || edges.length > MAX_EDGES) {
            return false;
        }

        for (Edge edge : edges) {
            if (edge == null || !isValidVertex(edge.from, n) || !isValidVertex(edge.to, n)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidVertex(int vertex, int n) {
        return vertex >= 1 && vertex <= n;
    }

    static final class Edge {
        final int from;
        final int to;
        final int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static final class DSU {
        private final int[] parent;
        private final int[] size;

        DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int v) {
            if (parent[v] != v) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return false;
            }

            if (size[rootA] < size[rootB]) {
                int tmp = rootA;
                rootA = rootB;
                rootB = tmp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            return true;
        }
    }

    // -------------------- FAST INPUT --------------------
    static final class FastIn {
        private final InputStream in;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastIn(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;

                if (len <= 0) {
                    return -1;
                }
            }

            return buf[ptr++];
        }

        int nextInt() throws IOException {
            int c;

            do {
                c = read();

                if (c == -1) {
                    throw new EOFException("Unexpected EOF");
                }
            } while (c <= ' ');

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;

            while (c > ' ') {
                val = val * 10 + c - '0';
                c = read();
            }

            return val * sign;
        }
    }

    // -------------------- FAST OUTPUT --------------------
    static final class FastOut {
        private final OutputStream out;
        private final byte[] buf = new byte[1 << 16];
        private int p = 0;
        private final byte[] tmp = new byte[20];

        FastOut(OutputStream out) {
            this.out = out;
        }

        void writeByte(int b) throws IOException {
            if (p == buf.length) {
                flush();
            }

            buf[p++] = (byte) b;
        }

        void writeString(String s) throws IOException {
            for (int i = 0; i < s.length(); i++) {
                writeByte(s.charAt(i));
            }
        }

        void writeLong(long x) throws IOException {
            if (x == 0) {
                writeByte('0');
                return;
            }

            if (x < 0) {
                writeByte('-');
                x = -x;
            }

            int k = 0;

            while (x > 0) {
                tmp[k++] = (byte) ('0' + (x % 10));
                x /= 10;
            }

            for (int i = k - 1; i >= 0; i--) {
                writeByte(tmp[i]);
            }
        }

        void flush() throws IOException {
            out.write(buf, 0, p);
            p = 0;
        }
    }

    private static void run() throws Exception {
        FastIn in = new FastIn(System.in);
        FastOut out = new FastOut(System.out);

        int n = in.nextInt();
        int m = in.nextInt();

        long answer = -1;

        if (n >= 1 && n <= MAX_VERTICES && m >= 0 && m <= MAX_EDGES) {
            Edge[] edges = new Edge[m];
            boolean validEdges = true;

            for (int i = 0; i < m; i++) {
                int from = in.nextInt();
                int to = in.nextInt();
                int weight = in.nextInt();

                if (!isValidVertex(from, n) || !isValidVertex(to, n)) {
                    validEdges = false;
                }

                edges[i] = new Edge(from, to, weight);
            }

            if (validEdges) {
                answer = solve(n, edges);
            }
        }

        if (answer == -1) {
            out.writeString(FAIL);
        } else {
            out.writeLong(answer);
        }

        out.writeByte('\n');
        out.flush();
    }

    private static void test() {
        assertEq(19, solve(4, new Edge[]{
                new Edge(1, 2, 5),
                new Edge(1, 3, 6),
                new Edge(2, 4, 8),
                new Edge(3, 4, 3)
        }));

        assertEq(3, solve(3, new Edge[]{
                new Edge(1, 2, 1),
                new Edge(1, 2, 2),
                new Edge(2, 3, 1)
        }));

        assertEq(-1, solve(2, new Edge[]{}));

        assertEq(0, solve(1, new Edge[]{}));

        assertEq(10, solve(2, new Edge[]{
                new Edge(1, 1, 100),
                new Edge(1, 2, 10),
                new Edge(2, 2, 100)
        }));

        assertEq(0, solve(3, new Edge[]{
                new Edge(1, 2, 0),
                new Edge(2, 3, 0)
        }));

        System.out.println("Test OK");
    }

    static void assertEq(long exp, long act) {
        if (exp != act) {
            throw new AssertionError("Expected=" + exp + ", actual=" + act);
        }
    }

    public static void main(String[] args) throws Exception {
        if (System.getProperty("os.name").startsWith("Windows")) {
            test();
        } else {
            run();
        }
    }
}
