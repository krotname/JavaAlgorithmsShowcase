package algorithms.sprint0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static algorithms.sprint0.Utils.readInt;
import static algorithms.sprint0.Utils.readList;

public class SlidingAverage {

    static List<Double> movingAverage(int n, List<Integer> arr, int windowSize) {
        int minSize = Math.min(arr.size(), n);
        int count = minSize - windowSize + 1;
        if (windowSize <= 0 || count <= 0) {
            return Collections.emptyList();
        }
        List<Double> result = new ArrayList<>(count);
        long sum = 0;
        for (int i = 0; i < windowSize; i++) {
            sum += arr.get(i);
        }
        result.add(sum / (double) windowSize);
        for (int i = windowSize; i < minSize; i++) {
            sum += (long) arr.get(i) - arr.get(i - windowSize);
            result.add(sum / (double) windowSize);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int windowSize = readInt(reader);
            List<Double> result = movingAverage(n, arr, windowSize);
            for (double elem : result) {
                writer.write(elem + " ");
            }
        }
    }
}
