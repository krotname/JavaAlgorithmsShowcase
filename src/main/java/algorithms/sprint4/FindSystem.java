import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

class FindSystem {

    /*
     * Принцип работы алгоритма:
     * 1) Строим обратный индекс:
     *    для каждого слова храним список документов, где оно встречается,
     *    и число его вхождений в каждом документе.
     * 2) Для каждого запроса берём только уникальные слова.
     * 3) Для каждого такого слова прибавляем его частоту ко всем документам,
     *    где это слово есть.
     * 4) Из найденных документов выбираем 5 лучших:
     *    сначала по убыванию релевантности, потом по возрастанию номера документа.
     *
     * Почему алгоритм корректен:
     * - В индексе для каждого слова хранится точное число его вхождений в документ.
     * - По условию релевантность — это сумма частот всех уникальных слов запроса.
     *   Именно такую сумму мы и считаем.
     * - Повторы слов в запросе не влияют на ответ, потому что мы оставляем только
     *   уникальные слова запроса.
     * - После подсчёта релевантностей выбираем 5 лучших документов по правилу из условия,
     *   значит ответ получается правильным.
     *
     * Временная сложность:
     * - Построение индекса: O(n), где n — общее число слов во всех документах.
     * - Обработка одного запроса: O(n), где n — суммарная длина списков документов
     *   для уникальных слов запроса.
     *
     * Пространственная сложность:
     * - O(n), где n — число пар (слово, документ) в индексе.
     */

    private static HashMap<String, ArrayList<int[]>> buildIndex(String[] docs) {
        HashMap<String, ArrayList<int[]>> index = new HashMap<>();

        for (int i = 0; i < docs.length; i++) {
            HashMap<String, Integer> freq = new HashMap<>();
            StringTokenizer st = new StringTokenizer(docs[i]);

            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }

            int docId = i + 1;
            for (Map.Entry<String, Integer> entry : freq.entrySet()) {
                index.computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                        .add(new int[]{docId, entry.getValue()});
            }
        }

        return index;
    }

    private static String processQuery(String query, HashMap<String, ArrayList<int[]>> index) {
        HashSet<String> uniqueWords = new HashSet<>();
        StringTokenizer st = new StringTokenizer(query);

        while (st.hasMoreTokens()) {
            uniqueWords.add(st.nextToken());
        }

        HashMap<Integer, Integer> relevance = new HashMap<>();

        for (String word : uniqueWords) {
            ArrayList<int[]> docs = index.get(word);
            if (docs == null) {
                continue;
            }

            for (int[] pair : docs) {
                int docId = pair[0];
                int count = pair[1];
                relevance.put(docId, relevance.getOrDefault(docId, 0) + count);
            }
        }

        ArrayList<int[]> best = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : relevance.entrySet()) {
            int docId = entry.getKey();
            int score = entry.getValue();

            int pos = 0;
            while (pos < best.size() && !isBetter(docId, score, best.get(pos)[0], best.get(pos)[1])) {
                pos++;
            }

            if (pos < 5) {
                best.add(pos, new int[]{docId, score});
                if (best.size() > 5) {
                    best.remove(5);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < best.size(); i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(best.get(i)[0]);
        }

        return sb.toString();
    }

    private static boolean isBetter(int docId1, int score1, int docId2, int score2) {
        if (score1 != score2) {
            return score1 > score2;
        }
        return docId1 < docId2;
    }

    private static void solve() throws Exception {
        FastReader reader = new FastReader(System.in);

        int n = reader.nextInt();
        String[] docs = new String[n];
        for (int i = 0; i < n; i++) {
            docs[i] = reader.nextLine();
        }

        HashMap<String, ArrayList<int[]>> index = buildIndex(docs);

        int m = reader.nextInt();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < m; i++) {
            String query = reader.nextLine();
            out.append(processQuery(query, index)).append('\n');
        }

        System.out.print(out);
    }

    private static void test() {
        String[] docs1 = {
                "i love coffee",
                "coffee with milk and sugar",
                "free tea for everyone"
        };

        HashMap<String, ArrayList<int[]>> index1 = buildIndex(docs1);
        assertEquals("1 2", processQuery("i like black coffee without milk", index1));
        assertEquals("3", processQuery("everyone loves new year", index1));
        assertEquals("2 1", processQuery("Mary likes black coffee without milk", index1));

        String[] docs2 = {
                "buy flat in Moscow",
                "rent flat in Moscow",
                "sell flat in Moscow",
                "want flat in Moscow like crazy",
                "clean flat in Moscow on weekends",
                "renovate flat in Moscow"
        };

        HashMap<String, ArrayList<int[]>> index2 = buildIndex(docs2);
        assertEquals("4 5 1 2 3", processQuery("flat in Moscow for crazy weekends", index2));

        String[] docs3 = {
                "i like dfs and bfs",
                "i like dfs dfs",
                "i like bfs with bfs and bfs"
        };

        HashMap<String, ArrayList<int[]>> index3 = buildIndex(docs3);
        assertEquals("3 1 2", processQuery("dfs dfs dfs dfs bfs", index3));
        System.out.println("Test OK");
    }

    private static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("expected = [" + expected + "], actual = [" + actual + "]");
        }
    }

    public static void main(String[] args) throws Exception {
        if (System.getProperty("os.name").startsWith("Windows")) {
            test();
        } else {
            solve();
        }
    }
    private static class FastReader {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastReader(InputStream in) {
            this.in = new BufferedInputStream(in);
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

        String nextLine() throws IOException {
            int c = read();

            while (c == '\n' || c == '\r') {
                c = read();
            }

            StringBuilder sb = new StringBuilder();
            while (c != -1 && c != '\n' && c != '\r') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }
    }
}