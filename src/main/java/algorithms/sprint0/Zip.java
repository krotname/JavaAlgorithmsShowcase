package algorithms.sprint0;

import static common.SafeParse.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static algorithms.sprint0.Utils.printList;

public class Zip {

    private static final int MAX_LIST_SIZE = 100_000;
    private static final int MAX_INPUT_LINE_LENGTH = 1_200_001;

    static List<Integer> zip(List<Integer> a, List<Integer> b, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        int min = Math.min(n, Math.min(a.size(), b.size()));
        ArrayList<Integer> integers = new ArrayList<>(min * 2);

        for (int i = 0; i < min; i++) {
            integers.add(a.get(i));
            integers.add(b.get(i));
        }
        return integers;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8))) {
            try {
                process(reader, writer);
            } catch (IllegalArgumentException | EOFException exception) {
                System.err.println("Invalid input: " + exception.getMessage());
            }
        }
    }

    static void process(BufferedReader reader, BufferedWriter writer) throws IOException {
        String sizeLine = readBoundedLine(reader);
        if (sizeLine == null) {
            throw new EOFException("Missing list size");
        }
        int n = parseInt(sizeLine.trim());
        if (n < 0 || n > MAX_LIST_SIZE) {
            throw new IllegalArgumentException("List size must be between 0 and " + MAX_LIST_SIZE);
        }
        List<Integer> a = parseList(readBoundedLine(reader));
        List<Integer> b = parseList(readBoundedLine(reader));
        if (a.size() < n || b.size() < n) {
            throw new IllegalArgumentException("Each list must contain at least n integers");
        }
        printList(zip(a, b, n), writer);
    }

    private static String readBoundedLine(BufferedReader reader) throws IOException {
        StringBuilder line = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1 && character != '\n') {
            if (line.length() == MAX_INPUT_LINE_LENGTH) {
                throw new IllegalArgumentException("Input line is too long");
            }
            if (character != '\r') {
                line.append((char) character);
            }
        }
        return character == -1 && line.length() == 0 ? null : line.toString();
    }

    private static List<Integer> parseList(String line) throws IOException {
        if (line == null) {
            throw new EOFException("Missing integer list");
        }
        return Utils.readList(new BufferedReader(new StringReader(line)));
    }
}
