package algorithms.sprint2;

import static common.SafeParse.parseInt;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;

/*
Принцип:
- Читаем токены ОПН слева направо.
- Если считанный символ - Число -> push в стек.
- Если считанный символ - Математическая Операция (+-*\) -> pop b, pop a, считаем a op b, push результат.
- Ответ: верх стека.

Корректность:
- В ОПН операция применяется к двум ближайшим слева операндам; к моменту чтения операции они лежат
  на вершине стека. Мы заменяем эти два значения на результат, сохраняя корректное состояние.
- Деление требуется “вниз” (floor), поэтому используем Math.floorDiv(a, b).

Сложность:
- Время O(m), m — число токенов.
- Память - в худшем O(m)
*/


public class Calculator {

    private static int eval(FastIn in) throws IOException {
        Deque<Integer> st = new ArrayDeque<>();

        while (true) {
            String t;
            try {
                t = in.next();
            } catch (EOFException e) {
                break;
            }

            if (t.length() == 1) {
                char op = t.charAt(0);
                if (op == '+' || op == '-' || op == '*' || op == '/') {
                    int b = st.pop();
                    int a = st.pop();

                    int r;
                    if (op == '+') {
                        r = a + b;
                    } else if (op == '-') {
                        r = a - b;
                    } else if (op == '*') {
                        r = a * b;
                    } else {
                        r = Math.floorDiv(a, b);
                    }

                    st.push(r);
                    continue;
                }
            }

            st.push(parseInt(t));
        }

        return st.peek();
    }

    private static void run() throws Exception {
        FastIn in = new FastIn(System.in);
        FastOut out = new FastOut(System.out);

        int ans = eval(in);

        out.writeInt(ans);
        out.writeByte('\n');
        out.flush();
    }

    // -------------------- LOCAL TESTS --------------------
    private static void test() throws Exception {
        assertEq(9, evalFromString("2 1 + 3 *"));
        assertEq(38, evalFromString("7 2 + 4 * 2 +"));
        assertEq(-1, evalFromString("-1 3 /"));
        assertEq(-2, evalFromString("-4 3 /"));
        assertEq(2, evalFromString("10 2 4 * -"));
        assertEq(5, evalFromString("5"));
        System.out.println("Test OK");
    }

    private static int evalFromString(String s) throws Exception {
        InputStream is = new ByteArrayInputStream(s.getBytes(StandardCharsets.US_ASCII));
        return eval(new FastIn(is));
    }

    private static void assertEq(int exp, int act) {
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

        String next() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) {
                    throw new EOFException("EOF");
                }
            } while (c <= ' ');

            byte[] tmp = new byte[32];
            int n = 0;

            while (c > ' ') {
                if (n == tmp.length) {
                    byte[] t2 = new byte[tmp.length << 1];
                    System.arraycopy(tmp, 0, t2, 0, tmp.length);
                    tmp = t2;
                }
                tmp[n++] = (byte) c;

                c = read();
                if (c == -1) {
                    break;
                }
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
}
