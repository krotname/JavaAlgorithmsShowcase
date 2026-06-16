package algorithms.sprint0;

import static common.SafeParse.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static algorithms.sprint0.Utils.printList;
import static algorithms.sprint0.Utils.readList;

public class Zip {

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
            String sizeLine = reader.readLine();
            if (sizeLine == null) {
                throw new EOFException("Missing list size");
            }
            int n = parseInt(sizeLine.trim());
            List<Integer> a = readList(reader);
            List<Integer> b = readList(reader);
            printList(zip(a, b, n), writer);
        }
    }
}
