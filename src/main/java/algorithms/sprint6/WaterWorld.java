/*
 * Принцип работы алгоритма:
 * Поле рассматривается как неориентированный граф: каждая клетка земли '#' — вершина,
 * а рёбра есть между соседними по стороне клетками земли. Идём по всем клеткам поля.
 * Если встречаем ещё не обработанную землю, значит найден новый остров. Запускаем из неё
 * итеративный BFS, помечаем все клетки этого острова как воду '.', одновременно считаем
 * размер острова и обновляем максимум.
 *
 * Почему алгоритм корректен:
 * Остров — это компонента связности клеток земли по четырём направлениям. BFS из любой
 * клетки такой компоненты посещает ровно все клетки этой компоненты: к каждой достижимой
 * по сторонам клетке он перейдёт, а за пределы компоненты не выйдет, потому что переходит
 * только в клетки '#'. После посещения клетки помечаются как '.', поэтому один и тот же
 * остров не будет посчитан повторно. Значит, каждое новое начало BFS соответствует ровно
 * одному острову, а посчитанный внутри BFS размер равен количеству клеток этого острова.
 * Перебор всех клеток гарантирует, что будут найдены все острова.
 *
 * Временная сложность: O(n * m), каждая клетка проверяется и обрабатывается не более одного раза.
 * Пространственная сложность: O(n * m), храним поле и очередь для BFS.
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WaterWorld {

    static int[] solve(byte[] map, int n, int m) {
        int total = n * m;
        int[] queue = new int[total];

        int islandCount = 0;
        int maxIslandSize = 0;

        for (int start = 0; start < total; start++) {
            if (map[start] != '#') {
                continue;
            }

            islandCount++;
            int currentSize = 0;
            int head = 0;
            int tail = 0;

            queue[tail++] = start;
            map[start] = '.';

            while (head < tail) {
                int v = queue[head++];
                currentSize++;

                int col = v % m;

                int up = v - m;
                if (v >= m && map[up] == '#') {
                    map[up] = '.';
                    queue[tail++] = up;
                }

                int down = v + m;
                if (down < total && map[down] == '#') {
                    map[down] = '.';
                    queue[tail++] = down;
                }

                int left = v - 1;
                if (col > 0 && map[left] == '#') {
                    map[left] = '.';
                    queue[tail++] = left;
                }

                int right = v + 1;
                if (col + 1 < m && map[right] == '#') {
                    map[right] = '.';
                    queue[tail++] = right;
                }
            }

            if (currentSize > maxIslandSize) {
                maxIslandSize = currentSize;
            }
        }

        return new int[]{islandCount, maxIslandSize};
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
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        byte[] nextBytes(int length) throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) {
                    throw new EOFException("Unexpected EOF");
                }
            } while (c <= ' ');

            byte[] result = new byte[length];
            for (int i = 0; i < length; i++) {
                if (c <= ' ') {
                    throw new EOFException("Unexpected end of token");
                }
                result[i] = (byte) c;
                if (i + 1 < length) {
                    c = read();
                }
            }
            return result;
        }
    }

    // -------------------- FAST OUTPUT --------------------
    static final class FastOut {
        private final OutputStream out;
        private final byte[] buf = new byte[1 << 16];
        private int p = 0;
        private final byte[] tmp = new byte[12];

        FastOut(OutputStream out) {
            this.out = out;
        }

        void writeByte(int b) throws IOException {
            if (p == buf.length) {
                flush();
            }
            buf[p++] = (byte) b;
        }

        void writeInt(int x) throws IOException {
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
        byte[] map = new byte[n * m];

        for (int row = 0; row < n; row++) {
            byte[] line = in.nextBytes(m);
            System.arraycopy(line, 0, map, row * m, m);
        }

        int[] answer = solve(map, n, m);
        out.writeInt(answer[0]);
        out.writeByte(' ');
        out.writeInt(answer[1]);
        out.writeByte('\n');
        out.flush();
    }

    private static void test() {
        assertAnswer(5, 1, solve(map(
                "#.#",
                ".#.",
                "#.#"
        ), 3, 3));

        assertAnswer(2, 6, solve(map(
                "#####",
                ".#...",
                "..#..",
                "#####"
        ), 4, 5));

        assertAnswer(0, 0, solve(map(
                "...",
                "..."
        ), 2, 3));

        assertAnswer(1, 6, solve(map(
                "###",
                "###"
        ), 2, 3));

        assertAnswer(4, 1, solve(map(
                "#.#",
                "...",
                "#.#"
        ), 3, 3));

        assertAnswer(2, 3, solve(map(
                "##.",
                "#..",
                "..#"
        ), 3, 3));

        System.out.println("Test OK");
    }

    private static byte[] map(String... rows) {
        int n = rows.length;
        int m = rows[0].length();
        byte[] result = new byte[n * m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                result[row * m + col] = (byte) rows[row].charAt(col);
            }
        }
        return result;
    }

    private static void assertAnswer(int expectedCount, int expectedMaxSize, int[] actual) {
        if (actual[0] != expectedCount || actual[1] != expectedMaxSize) {
            throw new AssertionError(
                    "Expected=" + expectedCount + " " + expectedMaxSize
                            + ", actual=" + actual[0] + " " + actual[1]
            );
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