package algorithms.sprint5;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

//https://contest.yandex.ru/contest/24810/run-report/160623687/

public class PyramidSort {
    private static final int MAX_PARTICIPANTS = 100_000;
    private static final int MAX_LOGIN_BYTES = 1_024;

    /*
     * Принцип работы алгоритма:
     * 1) Считываем всех участников в массив.
     * 2) Сортируем массив пирамидальной сортировкой.
     * 3) Для этого сначала строим max-heap, где наверху находится не лучший,
     *    а худший по условию участник. Это удобно для heapsort: на каждой
     *    итерации мы ставим корень кучи в конец ещё неотсортированной части.
     * 4) Участник a считается хуже участника b, если:
     *    - a решил меньше задач;
     *    - при равенстве решённых задач у a штраф больше;
     *    - при равенстве и задач, и штрафа логин a лексикографически больше.
     * 5) После каждого обмена восстанавливаем свойство кучи просеиванием вниз.
     *
     * Почему алгоритм корректен:
     * 1) Компаратор точно совпадает с порядком из условия, только в обратном
     *    направлении: "больше" в куче означает "хуже в итоговой таблице".
     * 2) Поэтому в корне max-heap всегда лежит худший участник среди всех
     *    элементов текущей кучи.
     * 3) На каждом шаге heapsort мы меняем корень с последним элементом
     *    неотсортированной части. Значит, этот худший участник встаёт ровно
     *    на своё окончательное место.
     * 4) После просеивания вниз куча снова корректна.
     * 5) Повторяя этот процесс, получаем массив, отсортированный от лучшего
     *    участника к худшему.
     *
     * Временная сложность:
     * - построение кучи: O(n);
     * - n операций извлечения максимума: O(n log n);
     * - итоговая сложность: O(n log n), где n количество участников;.
     *
     * Пространственная сложность:
     * - O(n) на хранение массива участников, где n количество участников;
     * - дополнительная память алгоритма сортировки: O(1).
     */

    static final class Participant {
        String login;
        int solved;
        int penalty;

        Participant(String login, int solved, int penalty) {
            this.login = login;
            this.solved = solved;
            this.penalty = penalty;
        }
    }

    static void solve(Participant[] a) {
        heapSort(a);
    }

    static int compare(Participant a, Participant b) {
        if (a.solved != b.solved) {
            return Integer.compare(b.solved, a.solved);
        }
        if (a.penalty != b.penalty) {
            return Integer.compare(a.penalty, b.penalty);
        }
        return a.login.compareTo(b.login);
    }

    static void heapSort(Participant[] a) {
        int n = a.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(a, i, n);
        }

        for (int end = n - 1; end > 0; end--) {
            swap(a, 0, end);
            siftDown(a, 0, end);
        }
    }

    static void siftDown(Participant[] a, int i, int size) {
        while (true) {
            int left = i * 2 + 1;
            if (left >= size) {
                return;
            }

            int right = left + 1;
            int worst = left;

            if (right < size && compare(a[right], a[left]) > 0) {
                worst = right;
            }

            if (compare(a[worst], a[i]) <= 0) {
                return;
            }

            swap(a, i, worst);
            i = worst;
        }
    }

    static void swap(Participant[] a, int i, int j) {
        Participant tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

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
                if (c <= ' ') {
                    throw new NumberFormatException("Expected digit after sign");
                }
            }

            int val = 0;
            while (c > ' ') {
                if (c < '0' || c > '9') {
                    throw new NumberFormatException("Invalid integer input");
                }
                int digit = c - '0';
                if (val > (Integer.MAX_VALUE - digit) / 10) {
                    throw new NumberFormatException("Integer input is too large");
                }
                val = val * 10 + digit;
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
                if (n == MAX_LOGIN_BYTES) {
                    throw new IOException("Login token is too long");
                }
                if (n == tmp.length) {
                    byte[] next = new byte[tmp.length * 2];
                    System.arraycopy(tmp, 0, next, 0, tmp.length);
                    tmp = next;
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

    static final class FastOut {
        private final OutputStream out;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0;

        FastOut(OutputStream out) {
            this.out = out;
        }

        void writeByte(int b) throws IOException {
            if (ptr == buf.length) {
                flush();
            }
            buf[ptr++] = (byte) b;
        }

        void writeString(String s) throws IOException {
            for (int i = 0; i < s.length(); i++) {
                writeByte(s.charAt(i));
            }
        }

        void flush() throws IOException {
            out.write(buf, 0, ptr);
            ptr = 0;
        }
    }

    private static void run() throws Exception {
        FastIn in = new FastIn(System.in);
        FastOut out = new FastOut(System.out);

        int n = in.nextInt();
        if (n < 0 || n > MAX_PARTICIPANTS) {
            throw new IllegalArgumentException("Participant count is out of range");
        }
        Participant[] a = new Participant[n];

        for (int i = 0; i < n; i++) {
            String login = in.next();
            int solved = in.nextInt();
            int penalty = in.nextInt();
            a[i] = new Participant(login, solved, penalty);
        }

        solve(a);

        for (Participant p : a) {
            out.writeString(p.login);
            out.writeByte('\n');
        }
        out.flush();
    }

    private static void test() {
        Participant[] a1 = new Participant[] {
                new Participant("alla", 4, 100),
                new Participant("gena", 6, 1000),
                new Participant("gosha", 2, 90),
                new Participant("rita", 2, 90),
                new Participant("timofey", 4, 80)
        };
        solve(a1);
        assertEq("gena", a1[0].login);
        assertEq("timofey", a1[1].login);
        assertEq("alla", a1[2].login);
        assertEq("gosha", a1[3].login);
        assertEq("rita", a1[4].login);

        Participant[] a2 = new Participant[] {
                new Participant("alla", 0, 0),
                new Participant("gena", 0, 0),
                new Participant("gosha", 0, 0),
                new Participant("rita", 0, 0),
                new Participant("timofey", 0, 0)
        };
        solve(a2);
        assertEq("alla", a2[0].login);
        assertEq("gena", a2[1].login);
        assertEq("gosha", a2[2].login);
        assertEq("rita", a2[3].login);
        assertEq("timofey", a2[4].login);

        Participant[] a3 = new Participant[] {
                new Participant("b", 1, 10),
                new Participant("a", 1, 10),
                new Participant("c", 2, 100)
        };
        solve(a3);
        assertEq("c", a3[0].login);
        assertEq("a", a3[1].login);
        assertEq("b", a3[2].login);

        System.out.println("Test OK");
    }

    static void assertEq(String expected, String actual) {
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
