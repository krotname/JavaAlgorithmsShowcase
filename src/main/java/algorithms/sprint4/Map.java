package algorithms.sprint4;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;

// https://contest.yandex.ru/contest/24414/run-report/160371601/

public class Map {

    /*
     * Принцип работы алгоритма:
     * Делаем свою хеш-таблицу методом цепочек.
     * Есть массив бакетов. В каждом бакете лежит связный список элементов.
     *
     * Для ключа key:
     * - считаем номер бакета по формуле key % SIZE;
     *   если остаток отрицательный, прибавляем SIZE,
     *   чтобы индекс был в диапазоне от 0 до SIZE - 1;
     * - идём по списку этого бакета;
     * - put: если ключ нашли, обновляем значение, иначе
     *   добавляем новый узел в начало списка бакета:
     *   buckets[i] = new Node(key, value, buckets[i]);
     * - get: если нашли, выводим значение, иначе None;
     * - delete: если нашли, удаляем узел из списка:
     *   если удаляется голова, делаем buckets[i] = cur.next,
     *   иначе делаем prev.next = cur.next.
     *
     * Почему алгоритм корректен:
     * - Каждый ключ всегда попадает в один и тот же бакет,
     *   потому что функция index(key) зависит только от key и SIZE.
     * - Все элементы этого бакета хранятся в одном списке.
     * - Поэтому, если пройти весь список, мы точно:
     *   - найдём ключ, если он есть;
     *   - поймём, что его нет, если не нашли.
     *
     * Временная сложность:
     * - одна операция put, get или delete в среднем работает за O(1),
     *   при достаточно равномерном распределении ключей по бакетам;
     * - в худшем случае одна операция работает за O(n),
     *   если все ключи попали в один бакет;
     * - так как выполняется n команд, суммарная сложность программы
     *   в среднем O(n), в худшем случае O(n^2).
     *
     * Пространственная сложность:
     * - в худшем случае таблица хранит до n элементов,
     *   поэтому пространственная сложность O(n);
     * - массив бакетов имеет фиксированный размер SIZE,
     *   то есть даёт только константную добавку.
     */

    private static final int SIZE = 100_003;
    private static final int MAX_COMMANDS = 100_000;
    private static final int HASH_SEED = ThreadLocalRandom.current().nextInt();

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
            int mixed = key ^ HASH_SEED;
            mixed ^= (mixed >>> 16);
            return Math.floorMod(mixed, SIZE);
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

        OptionalInt get(int key) {
            int i = index(key);
            Node cur = buckets[i];

            while (cur != null) {
                if (cur.key == key) {
                    return OptionalInt.of(cur.value);
                }
                cur = cur.next;
            }

            return OptionalInt.empty();
        }

        OptionalInt delete(int key) {
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
                    return OptionalInt.of(cur.value);
                }
                prev = cur;
                cur = cur.next;
            }

            return OptionalInt.empty();
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
            return nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        int nextInt(int min, int max) throws IOException {
            int c = read();
            while (c <= ' ') {
                if (c == -1) {
                    throw new IOException("Unexpected end of input");
                }
                c = read();
            }

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            if (c < '0' || c > '9') {
                throw new IOException("Expected integer");
            }

            long num = 0;
            while (c > ' ') {
                if (c < '0' || c > '9') {
                    throw new IOException("Expected integer");
                }
                num = num * 10 + c - '0';
                long signed = sign * num;
                if (signed < min || signed > max) {
                    throw new IOException("Input value exceeds limit");
                }
                c = read();
            }

            return (int) (num * sign);
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
        try {
            solve();
        } catch (IOException ignored) {
            // Invalid or excessive input is rejected without exhausting memory or CPU.
        }
    }

    private static void solve() throws IOException {
        Reader reader = new Reader();
        HashTable table = new HashTable();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = reader.nextInt(0, MAX_COMMANDS);

        for (int i = 0; i < n; i++) {
            char command = reader.nextCommand();
            int key = reader.nextInt();

            if (command == 'p') {
                int value = reader.nextInt();
                table.put(key, value);
            } else if (command == 'g') {
                OptionalInt result = table.get(key);
                out.write(result.isPresent() ? String.valueOf(result.getAsInt()) : "None");
                out.newLine();
            } else if (command == 'd') {
                OptionalInt result = table.delete(key);
                out.write(result.isPresent() ? String.valueOf(result.getAsInt()) : "None");
                out.newLine();
            } else {
                throw new IOException("Unknown command");
            }
        }

        out.flush();
    }
}
