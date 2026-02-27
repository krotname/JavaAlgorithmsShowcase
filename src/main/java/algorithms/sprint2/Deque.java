import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
Принцип работы алгоритма
Используется дек фиксированного размера m, реализованный на массиве как кольцевой буфер.
Храним:
- a[] — массив ёмкости m;
- head — индекс первого элемента дека;
- tail — индекс позиции "после последнего" элемента дека;
- size — текущее количество элементов.

Индексы head и tail двигаются по кругу: при выходе за границу массива переходят в начало/конец.
Операции:
- push_back(x): записать x в a[tail], сдвинуть tail вперёд по кругу, увеличить size.
- push_front(x): сдвинуть head назад по кругу, записать x в a[head], увеличить size.
- pop_front(): взять a[head], сдвинуть head вперёд, уменьшить size.
- pop_back(): сдвинуть tail назад, взять a[tail], уменьшить size.

Корректность (почему работает)
Поддерживаются инварианты:
1) size всегда в диапазоне [0..m].
2) head указывает на первый элемент (если size > 0).
3) tail указывает на позицию сразу после последнего элемента (если size > 0).
4) Элементы дека в порядке обхода лежат в a по циклу, начиная с head и длиной size.

Проверка по операциям:
- push_back при size < m кладёт новый элемент ровно в позицию tail, затем tail сдвигается на следующую
  позицию по кругу, поэтому tail снова указывает на "после последнего", а порядок элементов сохраняется.
- push_front при size < m сначала сдвигает head на предыдущую позицию по кругу и кладёт туда новый элемент,
  поэтому head начинает указывать на добавленный элемент (он становится первым), а порядок сохраняется.
- pop_front при size > 0 читает первый элемент из a[head], затем сдвигает head вперёд, уменьшая size:
  новым первым становится следующий элемент, инварианты сохраняются.
- pop_back при size > 0 сначала сдвигает tail назад (на позицию последнего элемента), читает его и уменьшает size:
  tail снова становится "после последнего", инварианты сохраняются.

Ошибки "error" выводятся только когда операция невозможна:
- push_* при size == m (переполнение),
- pop_* при size == 0 (пустой дек).
В этих случаях состояние (head, tail, size) не меняется, что сохраняет инварианты.

Сложность
Время: O(n), где n - количество операций. Каждая операция дека выполняется за O(1)
Память: массив из m элементов и несколько целых переменных: O(m) по памяти.
*/


public class Deque {

    // -------------------- RING BUFFER DEQUE --------------------
    static final class RingDeque {
        private final int[] a;
        private final int cap;
        private int head = 0; // индекс первого элемента
        private int tail = 0; // индекс позиции "после последнего"
        private int size = 0;

        RingDeque(int cap) {
            this.cap = cap;
            this.a = new int[cap];
        }

        private int next(int i) {
            return (i+1) % cap;
        }

        private int prev(int i) {
            return (i-1+cap) % cap;
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == cap;
        }

        void pushBack(int x) {
            a[tail] = x;
            tail = next(tail);
            size++;
        }

        void pushFront(int x) {
            head = prev(head);
            a[head] = x;
            size++;
        }

        int popFront() {
            int x = a[head];
            head = next(head);
            size--;
            return x;
        }

        int popBack() {
            tail = prev(tail);
            int x = a[tail];
            size--;
            return x;
        }
    }

    private static void process(FastIn in, FastOut out) throws Exception {
        int n = in.nextInt();
        int m = in.nextInt();

        RingDeque dq = new RingDeque(m);

        for (int i = 0; i < n; i++) {
            String cmd = in.next();

            switch (cmd) {
                case "push_back" -> {
                    int x = in.nextInt();
                    if (dq.isFull()) {
                        out.writeStr("error\n");
                    } else {
                        dq.pushBack(x);
                    }
                }
                case "push_front" -> {
                    int x = in.nextInt();
                    if (dq.isFull()) {
                        out.writeStr("error\n");
                    } else {
                        dq.pushFront(x);
                    }
                }
                case "pop_front" -> {
                    if (dq.isEmpty()) {
                        out.writeStr("error\n");
                    } else {
                        out.writeInt(dq.popFront());
                        out.writeByte('\n');
                    }
                }
                case "pop_back" -> {
                    if (dq.isEmpty()) {
                        out.writeStr("error\n");
                    } else {
                        out.writeInt(dq.popBack());
                        out.writeByte('\n');
                    }
                }
            }
        }
    }

    private static void run() throws Exception {
        FastIn in = new FastIn(System.in);
        FastOut out = new FastOut(System.out);
        process(in, out);
        out.flush();
    }

    // -------------------- TESTS --------------------
    private static String solveIO(String input) throws Exception {
        ByteArrayInputStream bin = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        FastIn in = new FastIn(bin);
        FastOut out = new FastOut(bout);
        process(in, out);
        out.flush();
        return bout.toString();
    }

    private static void test() throws Exception {
        // Пример 1
        assertEq(
                "861\n-819\n",
                solveIO(
                        "4\n" +
                                "4\n" +
                                "push_front 861\n" +
                                "push_front -819\n" +
                                "pop_back\n" +
                                "pop_back\n"
                )
        );

        // Пример 2
        assertEq(
                "-855\n0\n844\n",
                solveIO(
                        "7\n" +
                                "10\n" +
                                "push_front -855\n" +
                                "push_front 0\n" +
                                "pop_back\n" +
                                "pop_back\n" +
                                "push_back 844\n" +
                                "pop_back\n" +
                                "push_back 823\n"
                )
        );

        // Пример 3
        assertEq(
                "20\n102\n",
                solveIO(
                        "6\n" +
                                "6\n" +
                                "push_front -201\n" +
                                "push_back 959\n" +
                                "push_back 102\n" +
                                "push_front 20\n" +
                                "pop_front\n" +
                                "pop_back\n"
                )
        );

        // Емкость 1 + переполнение + попытка pop из пустого
        assertEq(
                "error\n1\nerror\n",
                solveIO(
                        "4\n" +
                                "1\n" +
                                "push_front 1\n" +
                                "push_back 2\n" +
                                "pop_back\n" +
                                "pop_front\n"
                )
        );

        // Wrap-around: head/tail должны корректно "перепрыгивать" границу массива
        assertEq(
                "1\n4\n2\n3\n",
                solveIO(
                        "8\n" +
                                "3\n" +
                                "push_back 1\n" +
                                "push_back 2\n" +
                                "push_back 3\n" +
                                "pop_front\n" +
                                "push_back 4\n" +
                                "pop_back\n" +
                                "pop_front\n" +
                                "pop_front\n"
                )
        );

        System.out.println("Test OK");
    }

    static void assertEq(String exp, String act) {
        if (!exp.equals(act)) {
            throw new AssertionError("Expected:\n" + exp + "\nActual:\n" + act);
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
        private int ptr = 0, len = 0;

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
                    byte[] t2 = new byte[tmp.length * 2];
                    System.arraycopy(tmp, 0, t2, 0, tmp.length);
                    tmp = t2;
                }
                tmp[n++] = (byte) c;
                c = read();
                if (c == -1) {
                    break;
                }
            }
            return new String(tmp, 0, n);
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

        void writeStr(String s) throws IOException {
            for (int i = 0; i < s.length(); i++) {
                writeByte(s.charAt(i));
            }
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
