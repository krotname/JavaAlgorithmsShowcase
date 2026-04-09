import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

//https://contest.yandex.ru/contest/23815/run-report/160042811/

public class FastSort {

    /*
    Принцип работы алгоритма:
    1) Считываем n участников в массив.
    2) Сортируем массив in-place быстрой сортировкой.
       В качестве опорного элемента (pivot) берём участника из середины текущего отрезка.
       Далее двумя указателями:
         - указатель i двигаем слева направо, пока a[i] должен стоять левее pivot;
         - указатель j двигаем справа налево, пока a[j] должен стоять правее pivot.
       Когда находятся два элемента не на своих сторонах, меняем их местами.
       После завершения разбиения рекурсивно сортируем меньшую из двух частей,
       а большую продолжаем обрабатывать в цикле. Это уменьшает глубину стека.
    3) Определяем лучше участника методом boolean better(a, b) по правилам задачи:
        - у A решено больше задач;
        - при равенстве задач у A меньше штраф;
        - при равенстве штрафа логин A меньше лексикографически.

    Почему алгоритм корректен:
    1) Функция better(a, b) задаёт ровно тот порядок, который требуется в условии.
    2) На каждом шаге quicksort мы делим массив относительно pivot:
      слева остаются элементы, которые должны идти не позже pivot,
      справа — элементы, которые должны идти не раньше pivot.
    3) После этого рекурсивно сортируем обе части.
    4) База рекурсии — отрезок длины 0 или 1, он уже отсортирован.
      Значит, весь массив будет отсортирован правильно.

    Временная сложность: в среднем O(n log n), в худшем случае O(n^2),
    где n количество участников

    Пространственная сложность:
    - O(1) дополнительной памяти на перестановки;
    - O(log n) на стек вызовов даже в худшем случае,
      потому что рекурсивный вызов делается только для меньшей части массива.
      Размер подмассива в следующем рекурсивном вызове не больше половины текущего,
      поэтому глубина рекурсии не превосходит O(log n).
      Большая часть обрабатывается итеративно в том же вызове функции.
    */

    static class Participant {
        String login;
        int solved;
        int penalty;

        Participant(String login, int solved, int penalty) {
            this.login = login;
            this.solved = solved;
            this.penalty = penalty;
        }
    }

    static boolean better(Participant a, Participant b) {
        if (a.solved != b.solved) {
            return a.solved > b.solved;
        }
        if (a.penalty != b.penalty) {
            return a.penalty < b.penalty;
        }
        return a.login.compareTo(b.login) < 0;
    }

    static void swap(Participant[] a, int i, int j) {
        Participant temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static void quickSort(Participant[] a, int left, int right) {
        while (left < right) {
            int i = left;
            int j = right;
            Participant pivot = a[left + (right - left) / 2];

            while (i <= j) {
                while (better(a[i], pivot)) {
                    i++;
                }
                while (better(pivot, a[j])) {
                    j--;
                }
                if (i <= j) {
                    swap(a, i, j);
                    i++;
                    j--;
                }
            }

            if (j - left < right - i) {
                if (left < j) {
                    quickSort(a, left, j);
                }
                left = i;
            } else {
                if (i < right) {
                    quickSort(a, i, right);
                }
                right = j;
            }
        }
    }

    static String solve(Participant[] participants) {
        if (participants.length > 1) {
            quickSort(participants, 0, participants.length - 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Participant p : participants) {
            sb.append(p.login).append('\n');
        }
        return sb.toString();
    }

    static final class FastIn {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastIn(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) {
                    throw new EOFException();
                }
            } while (c <= ' ');

            int value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }
            return value;
        }

        String next() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) {
                    throw new EOFException();
                }
            } while (c <= ' ');

            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }
    }

    private static void run() throws Exception {
        FastIn in = new FastIn(System.in);

        int n = in.nextInt(); // n — количество участников
        Participant[] participants = new Participant[n];

        for (int i = 0; i < n; i++) {
            String login = in.next();
            int solved = in.nextInt();
            int penalty = in.nextInt();
            participants[i] = new Participant(login, solved, penalty);
        }

        System.out.print(solve(participants));
    }

    private static void test() {
        Participant[] a = {new Participant("alla", 4, 100), new Participant("gena", 6, 1000), new Participant("gosha", 2, 90), new Participant("rita", 2, 90), new Participant("timofey", 4, 80)};

        String expected = "gena\ntimofey\nalla\ngosha\nrita\n";
        String actual = solve(a);

        if (!expected.equals(actual)) {
            throw new AssertionError("Expected:\n" + expected + "Actual:\n" + actual);
        }

        System.out.println("Test OK");
    }

    public static void main(String[] args) throws Exception {
        if (System.getProperty("os.name").startsWith("Windows")) {
            test();
        } else {
            run();
        }
    }
}