import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

// https://contest.yandex.ru/contest/24414/run-report/160043341/

public class Map {

    /*
     * Принцип работы алгоритма:
     * Делаем свою хеш-таблицу методом цепочек.
     * Есть массив бакетов. В каждом бакете лежит связный список элементов.
     *
     * Для ключа key:
     * - считаем номер бакета;
     * - идём по списку этого бакета;
     * - put: если ключ нашли, обновляем значение, иначе добавляем новый узел;
     * - get: если нашли, выводим значение, иначе None;
     * - delete: если нашли, удаляем узел и выводим значение, иначе None.
     *
     * Почему алгоритм корректен:
     * - Каждый ключ всегда попадает в один и тот же бакет.
     * - Все элементы этого бакета хранятся в одном списке.
     * - Поэтому, если пройти весь список, мы точно:
     *   - найдём ключ, если он есть;
     *   - поймём, что его нет, если не нашли.
     *
     * Временная сложность:
     * - put, get, delete — O(1) в среднем.
     * - В худшем случае — O(s), где s — число элементов в таблице,
     *   если все ключи попали в один бакет.
     *
     * Пространственная сложность:
     * - O(m + s), где m — число бакетов, s — число элементов в таблице.
     */

    private static final int SIZE = 100_003;

    static class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    static class HashTable {
        Node[] buckets = new Node[SIZE];

        int index(int key) {
            int x = key % SIZE;
            if (x < 0) {
                x += SIZE;
            }
            return x;
        }

        void put(int key, int value) {
            int i = index(key);
            Node cur = buckets[i];

            while (cur != null) {
                if (cur.key == key) {
                    cur.value = value;
                    return;
                }
                cur = cur.next;
            }

            buckets[i] = new Node(key, value, buckets[i]);
        }

        String get(int key) {
            int i = index(key);
            Node cur = buckets[i];

            while (cur != null) {
                if (cur.key == key) {
                    return String.valueOf(cur.value);
                }
                cur = cur.next;
            }

            return "None";
        }

        String delete(int key) {
            int i = index(key);
            Node cur = buckets[i];
            Node prev = null;

            while (cur != null) {
                if (cur.key == key) {
                    if (prev == null) {
                        buckets[i] = cur.next;
                    } else {
                        prev.next = cur.next;
                    }
                    return String.valueOf(cur.value);
                }
                prev = cur;
                cur = cur.next;
            }

            return "None";
        }
    }

    static class Reader {
        private final InputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        int read() throws IOException {
            if (ptr == len) {
                len = in.read(buffer);
                ptr = 0;
                if (len == -1) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c = read();
            while (c <= ' ') {
                c = read();
            }

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int num = 0;
            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }

            return num * sign;
        }

        char nextCommand() throws IOException {
            int c = read();
            while (c <= ' ') {
                c = read();
            }

            char first = (char) c;

            while (c > ' ') {
                c = read();
            }

            return first;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader reader = new Reader();
        HashTable table = new HashTable();
        StringBuilder out = new StringBuilder();

        int n = reader.nextInt();

        for (int i = 0; i < n; i++) {
            char command = reader.nextCommand();
            int key = reader.nextInt();

            if (command == 'p') {
                int value = reader.nextInt();
                table.put(key, value);
            } else if (command == 'g') {
                out.append(table.get(key)).append('\n');
            } else {
                out.append(table.delete(key)).append('\n');
            }
        }

        System.out.print(out);
    }
}