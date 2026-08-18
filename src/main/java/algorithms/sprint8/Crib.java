package algorithms.sprint8;

/*
Принцип работы алгоритма:
строим бор по допустимым словам. Затем запускаем динамику по тексту:
dp[i] означает, что префикс text[0..i) можно разбить на слова из словаря.
Из каждой достижимой позиции i идём по бору вдоль text[i..]. Каждый раз,
когда попадаем в терминальную вершину, помечаем позицию после найденного слова
как достижимую.

Корректность:
База dp[0] истинна, потому что пустой префикс уже корректно разбит.
Если dp[i] истинна и из позиции i в боре найдено слово text[i..j), то префикс
text[0..j) разбивается корректно: сначала берём корректное разбиение text[0..i),
затем добавляем найденное слово. Поэтому каждый переход алгоритма сохраняет
корректность.
Обратно, если текст имеет корректное разбиение на слова w1, w2, ..., wk, то после
обработки позиции 0 алгоритм отметит конец w1, затем из этой позиции отметит
конец w2 и так далее. По индукции будет отмечена позиция |text|. Значит,
алгоритм вернёт YES тогда и только тогда, когда разбиение существует.

Сложность:
Пусть N = |text|, S — сумма длин слов, L — максимальная длина слова.
Построение бора занимает O(S). Динамика проходит из каждой достижимой позиции
не глубже L символов, поэтому работает за O(N * L). Здесь L <= 100.
Память: O(S * 26 + N) на бор и массив dp.
*/

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Crib {

    static boolean solve(String text, String[] words) {
        if (!isLowercaseWord(text)) {
            return false;
        }

        long totalLength = 0;
        for (String word : words) {
            if (!isLowercaseWord(word)) {
                return false;
            }
            totalLength += word.length();
            if (totalLength >= Integer.MAX_VALUE) {
                return false;
            }
        }

        Trie trie = new Trie((int) totalLength + 1);
        for (String word : words) {
            trie.add(word);
        }

        char[] chars = text.toCharArray();
        boolean[] dp = new boolean[chars.length + 1];
        dp[0] = true;

        for (int start = 0; start < chars.length; start++) {
            if (!dp[start]) {
                continue;
            }

            int node = 0;
            for (int pos = start; pos < chars.length; pos++) {
                int letter = chars[pos] - 'a';
                node = trie.next[node][letter];

                if (node == 0) {
                    break;
                }

                if (trie.terminal[node]) {
                    dp[pos + 1] = true;
                }
            }

            if (dp[chars.length]) {
                return true;
            }
        }

        return dp[chars.length];
    }

    private static boolean isLowercaseWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if (current < 'a' || current > 'z') {
                return false;
            }
        }

        return true;
    }

    static final class Trie {
        final int[][] next;
        final boolean[] terminal;
        int size = 1;

        Trie(int capacity) {
            next = new int[capacity][26];
            terminal = new boolean[capacity];
        }

        void add(String word) {
            int node = 0;
            for (int i = 0; i < word.length(); i++) {
                int letter = word.charAt(i) - 'a';
                if (next[node][letter] == 0) {
                    next[node][letter] = size;
                    size++;
                }
                node = next[node][letter];
            }
            terminal[node] = true;
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

        String next() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) {
                    throw new EOFException("Unexpected EOF");
                }
            } while (c <= ' ');

            byte[] tmp = new byte[128];
            int n = 0;
            while (c > ' ') {
                if (n == tmp.length) {
                    byte[] resized = new byte[tmp.length * 2];
                    System.arraycopy(tmp, 0, resized, 0, tmp.length);
                    tmp = resized;
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

        void flush() throws IOException {
            out.write(buf, 0, p);
            p = 0;
        }
    }

    private static void run() throws Exception {
        FastIn in = new FastIn(System.in);
        FastOut out = new FastOut(System.out);

        String text = in.next();
        int n = in.nextInt();
        if (n < 0) {
            out.writeString("NO");
            out.writeByte('\n');
            out.flush();
            return;
        }

        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = in.next();
        }

        out.writeString(solve(text, words) ? "YES" : "NO");
        out.writeByte('\n');
        out.flush();
    }

    private static void test() {
        assertEq(true, solve("examiwillpasstheexam", new String[]{
                "will", "pass", "the", "exam", "i"
        }));

        assertEq(false, solve("abacaba", new String[]{
                "abac", "caba"
        }));

        assertEq(true, solve("abacaba", new String[]{
                "abac", "caba", "aba"
        }));

        assertEq(true, solve("aaaaaa", new String[]{
                "a", "aa", "aaa"
        }));

        assertEq(false, solve("aaaaab", new String[]{
                "a", "aa", "aaa"
        }));

        assertEq(true, solve("leetcode", new String[]{
                "leet", "code"
        }));

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
