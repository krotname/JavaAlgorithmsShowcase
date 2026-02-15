package algorithms.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// <template>
class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}
// <template>

public class Solution2 {
    public static void solution(Node<String> head) {
        // Your code
        // ヽ(´▽`)/
    }

    private static void test() {
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
        StringBuilder output_buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num_lines = Integer.parseInt(reader.readLine());
        for (int i = 0; i < num_lines; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int value_1 = Integer.parseInt(tokenizer.nextToken());
            int value_2 = Integer.parseInt(tokenizer.nextToken());
            int result = value_1 + value_2;
            output_buffer.append(result).append("\n");
        }
        System.out.println(output_buffer.toString());
    }
    
}