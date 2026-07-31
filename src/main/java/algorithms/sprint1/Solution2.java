package algorithms.sprint1;

import static common.SafeParse.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class Solution2 {
    private static final int MAX_LINE_COUNT = 1_000_000;

    public static void solution(Node<String> head) {
        StringBuilder output = new StringBuilder();
        Node<String> current = head;
        while (current != null) {
            output.append(current.value).append('\n');
            current = current.next;
        }
        System.out.print(output);
    }

    static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        solution(node0);
        /*
        Output is:
        node0
        node1
        node2
        node3
        */
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        int lineCount = readLineCount(reader);
        PrintWriter writer = new PrintWriter(System.out, false, StandardCharsets.UTF_8);
        for (int i = 0; i < lineCount; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(readInputLine(reader, i + 1));
            int firstValue = parseInt(nextToken(tokenizer, i + 1));
            int secondValue = parseInt(nextToken(tokenizer, i + 1));
            if (tokenizer.hasMoreTokens()) {
                throw new IllegalArgumentException("Line " + (i + 1) + " must contain exactly two integers");
            }
            int result = Math.addExact(firstValue, secondValue);
            writer.println(result);
        }
        writer.println();
        writer.flush();
    }

    private static int readLineCount(BufferedReader reader) throws IOException {
        String countLine = reader.readLine();
        if (countLine == null) {
            throw new IllegalArgumentException("Missing line count");
        }
        int lineCount = parseInt(countLine);
        if (lineCount < 0 || lineCount > MAX_LINE_COUNT) {
            throw new IllegalArgumentException("Line count must be between 0 and " + MAX_LINE_COUNT);
        }
        return lineCount;
    }

    private static String readInputLine(BufferedReader reader, int lineNumber) throws IOException {
        String inputLine = reader.readLine();
        if (inputLine == null) {
            throw new IllegalArgumentException("Missing input line " + lineNumber);
        }
        return inputLine;
    }

    private static String nextToken(StringTokenizer tokenizer, int lineNumber) {
        if (!tokenizer.hasMoreTokens()) {
            throw new IllegalArgumentException("Line " + lineNumber + " must contain exactly two integers");
        }
        return tokenizer.nextToken();
    }
}
