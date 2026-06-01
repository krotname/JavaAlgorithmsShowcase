import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
Принцип работы алгоритма:
Используем динамическое программирование по префиксам строк. Пусть dp[i][j] — минимальное
число операций, чтобы превратить первые i символов первой строки в первые j символов второй.
Переход берёт минимум из трёх вариантов: удалить символ, вставить символ или заменить символ.
Если текущие символы равны, замена имеет цену 0. Полную таблицу хранить не нужно: для расчёта
текущей строки достаточно предыдущей строки и уже посчитанных значений текущей строки.

Доказательство корректности:
База корректна: пустую строку можно превратить в префикс длины j ровно j вставками, а префикс
длины i в пустую строку — ровно i удалениями. Для непустых префиксов последняя операция в любом
оптимальном преобразовании — это либо удаление последнего символа первой строки, либо вставка
последнего символа второй строки, либо замена/сохранение последнего символа. Алгоритм перебирает
все эти возможные последние операции и выбирает минимум, значит находит оптимальное значение для
каждой пары префиксов. По индукции по сумме длин префиксов значение для полных строк тоже
оптимально.

Временная сложность: O(n * m), где n и m — длины строк.
Пространственная сложность: O(min(n, m)), потому что хранится только две строки динамики.
*/
public class LevenshteinDistance {

    static int solve(String first, String second) {
        String s = first;
        String t = second;

        if (t.length() > s.length()) {
            String tmp = s;
            s = t;
            t = tmp;
        }

        int n = s.length();
        int m = t.length();

        int[] previous = new int[m + 1];
        int[] current = new int[m + 1];

        for (int j = 0; j <= m; j++) {
            previous[j] = j;
        }

        for (int i = 1; i <= n; i++) {
            current[0] = i;
            char fromChar = s.charAt(i - 1);

            for (int j = 1; j <= m; j++) {
                int deletion = previous[j] + 1;
                int insertion = current[j - 1] + 1;

                int replacement = previous[j - 1];
                if (fromChar != t.charAt(j - 1)) {
                    replacement++;
                }

                current[j] = Math.min(Math.min(deletion, insertion), replacement);
            }

            int[] tmp = previous;
            previous = current;
            current = tmp;
        }

        return previous[m];
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

        String nextLine() throws IOException {
            byte[] tmp = new byte[1024];
            int size = 0;
            int c = read();

            if (c == -1) {
                throw new EOFException("Unexpected EOF");
            }

            while (c != -1 && c != '\n') {
                if (c != '\r') {
                    if (size == tmp.length) {
                        byte[] grown = new byte[tmp.length * 2];
                        System.arraycopy(tmp, 0, grown, 0, tmp.length);
                        tmp = grown;
                    }
                    tmp[size++] = (byte) c;
                }
                c = read();
            }

            return new String(tmp, 0, size);
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

        String s = in.nextLine();
        String t = in.nextLine();

        out.writeInt(solve(s, t));
        out.writeByte('\n');
        out.flush();
    }

    private static void test() {
        assertEq(2, solve("abacaba", "abaabc"));
        assertEq(3, solve("innokentiy", "innnokkentia"));
        assertEq(1, solve("r", "x"));

        assertEq(0, solve("abc", "abc"));
        assertEq(3, solve("", "abc"));
        assertEq(3, solve("abc", ""));
        assertEq(3, solve("kitten", "sitting"));
        assertEq(1, solve("abc", "ab"));
        assertEq(1, solve("ab", "abc"));

        System.out.println("Test OK");
    }

    static void assertEq(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("Expected=" + expected + ", actual=" + actual);
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