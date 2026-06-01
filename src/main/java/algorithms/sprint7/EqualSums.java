import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
Принцип работы алгоритма:
Нужно понять, можно ли выбрать часть партий так, чтобы сумма очков в них была равна половине общей суммы.
Если общая сумма нечётная, две равные части получить нельзя.
Иначе решаем задачу о достижимой сумме: храним битовую динамику reachable, где бит s равен 1,
если некоторым набором уже рассмотренных партий можно набрать сумму s. Изначально достижима сумма 0.
Для очередного значения x обновляем reachable операцией reachable |= reachable << x.
После обработки всех значений проверяем достижимость суммы total / 2.

Доказательство корректности:
После обработки первых i партий инвариант такой: бит s в reachable установлен тогда и только тогда,
когда из этих i партий можно выбрать подмножество с суммой s.
База: до обработки партий достижима только сумма 0, это пустое подмножество.
Переход: для партии с очками x каждая старая достижимая сумма s остаётся достижимой, если не брать эту партию,
а сумма s + x становится достижимой, если взять её. Именно это делает reachable |= reachable << x.
По индукции после всех партий reachable содержит ровно все суммы, которые можно набрать подмножеством партий.
Разбиение на две равные части существует тогда и только тогда, когда общая сумма чётная и достижима сумма total / 2:
выбранное подмножество даёт первую часть, все остальные партии — вторую с такой же суммой.

Сложность:
Пусть S — общая сумма очков, S <= 300 * 300 = 90000.
Временная сложность — O(n * S / 64), потому что суммы хранятся битами в long-блоках.
Пространственная сложность — O(S / 64).
*/
public class EqualSums {

    static boolean solve(int[] points) {
        int total = 0;
        for (int point : points) {
            total += point;
        }

        if ((total & 1) == 1) {
            return false;
        }

        int target = total / 2;
        long[] reachable = new long[(target >> 6) + 1];
        reachable[0] = 1L;

        for (int point : points) {
            if (point <= 0 || point > target) {
                continue;
            }

            addPoint(reachable, point);

            if (isReachable(reachable, target)) {
                return true;
            }
        }

        return isReachable(reachable, target);
    }

    private static void addPoint(long[] reachable, int point) {
        int wordShift = point >> 6;
        int bitShift = point & 63;

        for (int word = reachable.length - 1; word >= wordShift; word--) {
            long shifted = reachable[word - wordShift] << bitShift;

            if (bitShift != 0 && word - wordShift - 1 >= 0) {
                shifted |= reachable[word - wordShift - 1] >>> (64 - bitShift);
            }

            reachable[word] |= shifted;
        }
    }

    private static boolean isReachable(long[] reachable, int sum) {
        return (reachable[sum >> 6] & (1L << (sum & 63))) != 0;
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
    }

    // -------------------- FAST OUTPUT --------------------
    static final class FastOut {
        private final OutputStream out;
        private final byte[] buf = new byte[1 << 16];
        private int p = 0;

        FastOut(OutputStream out) {
            this.out = out;
        }

        void writeByte(int b) throws IOException {
            if (p == buf.length) {
                flush();
            }

            buf[p++] = (byte) b;
        }

        void writeAscii(String s) throws IOException {
            for (int i = 0; i < s.length(); i++) {
                writeByte(s.charAt(i));
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
        int[] points = new int[n];

        for (int i = 0; i < n; i++) {
            points[i] = in.nextInt();
        }

        out.writeAscii(solve(points) ? "True" : "False");
        out.writeByte('\n');
        out.flush();
    }

    private static void test() {
        assertEq(true, solve(new int[]{1, 5, 7, 1}));
        assertEq(false, solve(new int[]{2, 10, 9}));
        assertEq(true, solve(new int[]{}));
        assertEq(true, solve(new int[]{0, 0, 0}));
        assertEq(false, solve(new int[]{1}));
        assertEq(true, solve(new int[]{1, 1}));
        assertEq(true, solve(new int[]{100, 200, 300}));
        assertEq(false, solve(new int[]{100, 200, 301}));

        System.out.println("Test OK");
    }

    static void assertEq(boolean exp, boolean act) {
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