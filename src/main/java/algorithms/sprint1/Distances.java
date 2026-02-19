import java.io.*;
import java.util.Arrays;

// https://contest.yandex.ru/contest/22450/run-report/157017559/
public class Distances {

    // -------------------- SOLUTION --------------------
    static int[] solve(int[] a) {
        int n = a.length;
        int[] dist = new int[n];

        int lastZero = -n;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                lastZero = i;
            }
            dist[i] = i - lastZero;
        }

        int nextZero = n;
        for (int i = lastZero; i >= 0; i--) {
            if (a[i] == 0) nextZero = i;
            dist[i] = Math.min(dist[i], nextZero - i);
        }
        return dist;
    }

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
                val = val * 10 + (c - '0');
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

    // -------------------- INPUT / OUTPUT --------------------
    static void run() {

        try (InputStream input = new BufferedInputStream(new FileInputStream("input.txt")); OutputStream output = new BufferedOutputStream(new FileOutputStream("output.txt"))) {


            FastIn in = new FastIn(input);
            FastOut out = new FastOut(output);

            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();

            int[] dist = solve(a);

            for (int i = 0; i < n; i++) {
                if (i > 0) out.writeByte(' ');
                out.writeInt(dist[i]);
            }
            out.writeByte('\n');
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // -------------------- TESTS --------------------
    static void assertEq(int[] exp, int[] act, String name) {
        if (!Arrays.equals(exp, act)) {
            throw new AssertionError(name + "\nexp=" + Arrays.toString(exp) + "\nact=" + Arrays.toString(act));
        }
    }

    static void test() {
        assertEq(new int[]{0, 1, 2, 1, 0}, solve(new int[]{0, 1, 4, 9, 0}), "sample1");
        assertEq(new int[]{0, 1, 2, 3, 4, 5}, solve(new int[]{0, 7, 9, 4, 8, 20}), "sample2");
        assertEq(new int[]{0}, solve(new int[]{0}), "n=1");
        assertEq(new int[]{0, 0, 0}, solve(new int[]{0, 0, 0}), "all zeros");
        assertEq(new int[]{2, 1, 0, 1, 2}, solve(new int[]{5, 6, 0, 7, 8}), "zero in middle");
        System.out.println("OK");
    }

    // -------------------- MAIN --------------------
    public static void main(String[] args) throws Exception {
        if (System.getProperty("os.name").startsWith("Windows")) {
            test();
        } else {
            run();
        }
    }
}
