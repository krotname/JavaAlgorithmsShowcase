package algorithms.sprint8;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/*
 * Принцип работы алгоритма:
 * Сначала распаковываем первую строку: она задаёт максимальную возможную длину ответа.
 * Для каждой следующей строки генерируем символы её распакованного вида только до текущей
 * длины общего префикса и сразу сравниваем их с первой распакованной строкой.
 *
 * Распаковка сделана итеративно, без рекурсии. Сначала строится массив соответствия
 * открывающих и закрывающих скобок. Затем стек кадров имитирует рекурсивный обход:
 * буквы выдаются как есть, а конструкция k[A] обходит содержимое A ровно k раз.
 * Конструкции 1[A] предварительно заменяются на A, чтобы длинные цепочки единичных
 * повторов не заставляли многократно проходить одни и те же скобки.
 *
 * Почему алгоритм корректен:
 * Общий префикс уже обработанных строк всегда является префиксом первой строки.
 * При добавлении очередной строки новый общий префикс не может быть длиннее текущего,
 * поэтому достаточно сравнить только первые prefixLength символов её распакованного вида.
 * Итеративный распаковщик выдаёт символы в том же порядке, что и рекурсивное определение ЗС:
 * конкатенация сохраняет порядок частей, а k[A] повторяет A ровно k раз.
 * Первая позиция несовпадения даёт новую длину общего префикса. Если строка закончилась
 * раньше, новая длина равна длине этой строки. По индукции после обработки всех строк
 * получаем наибольший общий префикс всех распакованных строк.
 *
 * Временная сложность:
 * Для каждой строки: O(m + p), где m — длина запакованной строки, p — число реально
 * проверенных символов распакованной строки, p <= 100000.
 * В худшем случае: O(n * L), где L <= 100000.
 *
 * Пространственная сложность:
 * O(L + m), где L — длина первой распакованной строки, m — длина текущей запакованной строки.
 */
public class PackedPrefix {
    private static final int MAX_UNPACKED_LENGTH = 100_000;

    static String solve(String[] packedStrings) {
        if (packedStrings.length == 0 || !isValidPacked(packedStrings[0])) {
            return "";
        }

        String first = decodePrefix(packedStrings[0], MAX_UNPACKED_LENGTH);
        int prefixLength = first.length();

        for (int i = 1; i < packedStrings.length; i++) {
            if (prefixLength == 0) {
                break;
            }
            if (!isValidPacked(packedStrings[i])) {
                return "";
            }
            prefixLength = commonPrefixWithPacked(packedStrings[i], first, prefixLength);
        }

        return first.substring(0, prefixLength);
    }

    private static String decodePrefix(String packed, int limit) {
        if (limit == 0) {
            return "";
        }

        packed = removeSingleRepeats(packed);

        int[] matchingBracket = buildMatchingBrackets(packed);
        int maxFrames = packed.length() + 1;
        int[] starts = new int[maxFrames];
        int[] positions = new int[maxFrames];
        int[] ends = new int[maxFrames];
        int[] repeatsLeft = new int[maxFrames];

        int top = 0;
        starts[top] = 0;
        positions[top] = 0;
        ends[top] = packed.length();
        repeatsLeft[top] = 1;

        StringBuilder decoded = new StringBuilder(Math.min(limit, packed.length()));

        while (top >= 0 && decoded.length() < limit) {
            if (positions[top] >= ends[top]) {
                repeatsLeft[top]--;

                if (repeatsLeft[top] > 0) {
                    positions[top] = starts[top];
                } else {
                    top--;
                }

                continue;
            }

            char current = packed.charAt(positions[top]);

            if (current >= 'a' && current <= 'z') {
                decoded.append(current);
                positions[top]++;
            } else {
                int repeat = current - '0';
                int openBracket = positions[top] + 1;
                int closeBracket = matchingBracket[openBracket];

                positions[top] = closeBracket + 1;

                top++;
                starts[top] = openBracket + 1;
                positions[top] = openBracket + 1;
                ends[top] = closeBracket;
                repeatsLeft[top] = repeat;
            }
        }

        return decoded.toString();
    }

    private static int commonPrefixWithPacked(String packed, String base, int limit) {
        if (limit == 0) {
            return 0;
        }

        packed = removeSingleRepeats(packed);

        int[] matchingBracket = buildMatchingBrackets(packed);
        int maxFrames = packed.length() + 1;
        int[] starts = new int[maxFrames];
        int[] positions = new int[maxFrames];
        int[] ends = new int[maxFrames];
        int[] repeatsLeft = new int[maxFrames];

        int top = 0;
        starts[top] = 0;
        positions[top] = 0;
        ends[top] = packed.length();
        repeatsLeft[top] = 1;

        int matched = 0;

        while (top >= 0 && matched < limit) {
            if (positions[top] >= ends[top]) {
                repeatsLeft[top]--;

                if (repeatsLeft[top] > 0) {
                    positions[top] = starts[top];
                } else {
                    top--;
                }

                continue;
            }

            char current = packed.charAt(positions[top]);

            if (current >= 'a' && current <= 'z') {
                if (base.charAt(matched) != current) {
                    return matched;
                }

                matched++;
                positions[top]++;
            } else {
                int repeat = current - '0';
                int openBracket = positions[top] + 1;
                int closeBracket = matchingBracket[openBracket];

                positions[top] = closeBracket + 1;

                top++;
                starts[top] = openBracket + 1;
                positions[top] = openBracket + 1;
                ends[top] = closeBracket;
                repeatsLeft[top] = repeat;
            }
        }

        return matched;
    }

    private static String removeSingleRepeats(String packed) {
        int length = packed.length();
        boolean hasSingleRepeat = false;

        for (int i = 0; i + 1 < length; i++) {
            if (packed.charAt(i) == '1' && packed.charAt(i + 1) == '[') {
                hasSingleRepeat = true;
                break;
            }
        }

        if (!hasSingleRepeat) {
            return packed;
        }

        int[] matchingBracket = buildMatchingBrackets(packed);
        boolean[] skip = new boolean[length];

        for (int i = 0; i + 1 < length; i++) {
            if (packed.charAt(i) == '1' && packed.charAt(i + 1) == '[') {
                int openBracket = i + 1;
                int closeBracket = matchingBracket[openBracket];

                skip[i] = true;
                skip[openBracket] = true;
                skip[closeBracket] = true;
            }
        }

        StringBuilder normalized = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            if (!skip[i]) {
                normalized.append(packed.charAt(i));
            }
        }

        return normalized.toString();
    }

    private static int[] buildMatchingBrackets(String packed) {
        int[] matchingBracket = new int[packed.length()];
        int[] stack = new int[packed.length()];
        int top = 0;

        for (int i = 0; i < packed.length(); i++) {
            char current = packed.charAt(i);

            if (current == '[') {
                stack[top] = i;
                top++;
            } else if (current == ']') {
                top--;
                int openBracket = stack[top];
                matchingBracket[openBracket] = i;
            }
        }

        return matchingBracket;
    }

    private static boolean isValidPacked(String packed) {
        int top = 0;

        for (int i = 0; i < packed.length(); i++) {
            char current = packed.charAt(i);

            if (current >= '1' && current <= '9') {
                if (i + 1 >= packed.length() || packed.charAt(i + 1) != '[') {
                    return false;
                }
            } else if (current == '[') {
                if (i == 0 || packed.charAt(i - 1) < '1' || packed.charAt(i - 1) > '9') {
                    return false;
                }
                top++;
            } else if (current == ']') {
                if (top == 0 || packed.charAt(i - 1) == '[') {
                    return false;
                }
                top--;
            } else if (current < 'a' || current > 'z') {
                return false;
            }
        }

        return top == 0;
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

            byte[] tmp = new byte[32];
            int n = 0;

            while (c > ' ') {
                if (n == tmp.length) {
                    byte[] resized = new byte[tmp.length * 2];
                    System.arraycopy(tmp, 0, resized, 0, tmp.length);
                    tmp = resized;
                }

                tmp[n] = (byte) c;
                n++;

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

            buf[p] = (byte) b;
            p++;
        }

        void writeStringPrefix(String s, int length) throws IOException {
            for (int i = 0; i < length; i++) {
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
        if (n <= 0) {
            out.writeByte('\n');
            out.flush();
            return;
        }

        String packedFirst = in.next();
        if (!isValidPacked(packedFirst)) {
            out.writeByte('\n');
            out.flush();
            return;
        }

        String first = decodePrefix(packedFirst, MAX_UNPACKED_LENGTH);
        int prefixLength = first.length();

        for (int i = 1; i < n; i++) {
            if (prefixLength == 0) {
                break;
            }

            String packed = in.next();
            if (!isValidPacked(packed)) {
                prefixLength = 0;
                break;
            }
            prefixLength = commonPrefixWithPacked(packed, first, prefixLength);
        }

        out.writeStringPrefix(first, prefixLength);
        out.writeByte('\n');
        out.flush();
    }

    private static void test() {
        assertEq("aaa", solve(new String[]{
                "2[a]2[ab]",
                "3[a]2[r2[t]]",
                "a2[aa3[b]]"
        }));

        assertEq("aba", solve(new String[]{
                "abacabaca",
                "2[abac]a",
                "3[aba]"
        }));

        assertEq("ab", solve(new String[]{
                "abcde",
                "ab"
        }));

        assertEq("", solve(new String[]{
                "abc",
                "2[z]"
        }));

        assertEq("abcccabccc", solve(new String[]{
                "2[ab3[c]]",
                "abcccabcccx"
        }));

        assertEq("abc", solve(new String[]{
                "1[1[1[abc]]]"
        }));

        System.out.println("Test OK");
    }

    private static void assertEq(String expected, String actual) {
        if (!expected.equals(actual)) {
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
