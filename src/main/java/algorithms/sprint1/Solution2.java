package algorithms.sprint1;

import static common.SafeParse.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class Solution2 {
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
        StringBuilder outputBuffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        int lineCount = parseInt(reader.readLine());
        for (int i = 0; i < lineCount; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int firstValue = parseInt(tokenizer.nextToken());
            int secondValue = parseInt(tokenizer.nextToken());
            int result = firstValue + secondValue;
            outputBuffer.append(result).append("\n");
        }
        System.out.println(outputBuffer);
    }
}
