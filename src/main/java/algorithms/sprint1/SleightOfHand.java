package algorithms.sprint1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

// https://contest.yandex.ru/contest/22450/run-report/157017632/
public class SleightOfHand {
    // -------------------- FAST INPUT --------------------
    static final class FastIn {
        private final InputStream in;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastIn(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) throw new EOFException("Unexpected EOF");
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

        String next(int maxLength) throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) throw new EOFException("Unexpected EOF");
            } while (c <= ' ');

            byte[] tmp = new byte[Math.min(32, maxLength)];
            int n = 0;
            while (c > ' ') {
                if (n == maxLength) {
                    throw new IOException("Token length exceeds " + maxLength);
                }
                if (n == tmp.length) {
                    byte[] t2 = new byte[Math.min(maxLength, tmp.length * 2)];
                    System.arraycopy(tmp, 0, t2, 0, tmp.length);
                    tmp = t2;
                }
                tmp[n++] = (byte) c;
                c = read();
                if (c == -1) break;
            }
            return new String(tmp, 0, n, StandardCharsets.UTF_8);
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
            if (p == buf.length) flush();
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
            for (int i = k - 1; i >= 0; i--) writeByte(tmp[i]);
        }

        void flush() throws IOException {
            out.write(buf, 0, p);
            p = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        if (System.getProperty("os.name").startsWith("Windows")) {
            test();
        } else {
            run();
        }
    }

    private static void run() throws Exception {
        FastIn in = new FastIn(System.in);
        FastOut out = new FastOut(System.out);

        int k = in.nextInt();
        int limit = 2 * k;
        int[] count = new int[10];

        for (int r = 0; r < 4; r++) {
            StringBuilder row = new StringBuilder(in.next(4));
            // На всякий случай, если токенайзер разделит строку (обычно не будет)
            while (row.length() < 4) {
                row.append(in.next(4 - row.length()));
            }
            for (int c = 0; c < 4; c++) {
                char ch = row.charAt(c);
                if (ch != '.') {
                    if (ch < '0' || ch > '9') {
                        throw new IOException("Invalid grid cell: " + ch);
                    }
                    count[ch - '0']++;
                }
            }
        }

        int points = 0;
        for (int d = 1; d <= 9; d++) {
            int cnt = count[d];
            if (cnt != 0 && cnt <= limit) {
                points++;
            }
        }

        out.writeInt(points);
        out.writeByte('\n');
        out.flush();
    }

    private static void test() {
        // Примеры из условия
        assertEq(2, solve(3, new int[][]{
                {1, 2, 3, 1},
                {2, 0, 0, 2},
                {2, 0, 0, 2},
                {2, 0, 0, 2}
        }));

        assertEq(1, solve(4, new int[][]{
                {1, 1, 1, 1},
                {9, 9, 9, 9},
                {1, 1, 1, 1},
                {9, 9, 1, 1}
        }));

        assertEq(0, solve(1, new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        }));

        // Критичный тест: ровно 2*k нажатий — очко должно засчитаться
        assertEq(1, solve(2, new int[][]{
                {5, 5, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {5, 5, 0, 0}
        }));

        // Чуть больше лимита — очко не должно засчитаться
        assertEq(0, solve(2, new int[][]{
                {6, 6, 6, 0},
                {6, 6, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        })); // 6 встречается 5 раз, limit=4

        // Пустое поле
        assertEq(0, solve(5, new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }));

        // Несколько цифр в пределах лимита
        assertEq(2, solve(1, new int[][]{
                {1, 1, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        })); // limit=2, cnt(1)=2, cnt(2)=1

        System.out.println("Test OK");
    }

    static int solve(int k, int[][] a) {
        int points = 0;
        final int limit = 2 * k;
        final int[] count = new int[10];

        for (int[] row : a) {
            for (int v : row) {
                if (v != 0) {
                    if (v < 0 || v > 9) {
                        throw new IllegalArgumentException("Grid values must be between 0 and 9");
                    }
                    count[v]++;
                }
            }
        }

        for (int d = 1; d <= 9; d++) {
            int cnt = count[d];
            if (cnt != 0 && cnt <= limit) {
                points++;
            }
        }
        return points;
    }

    static void assertEq(int exp, int act) {
        if (exp != act) {
            throw new AssertionError("Expected=" + exp + ", actual=" + act);
        }
    }
}
