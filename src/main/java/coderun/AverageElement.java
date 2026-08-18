package coderun;


import static common.SafeParse.parseInt;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;



public class AverageElement {

    private static final int MAX_INPUT_CHARACTERS = 4096;

    /**
     * Для чтения входных данных необходимо получить их
     * из стандартного потока ввода (System.in).
     * Данные во входном потоке соответствуют описанному
     * в условии формату. Обычно входные данные состоят
     * из нескольких строк. Можно использовать более производительные
     * и удобные классы BufferedReader, BufferedWriter, Scanner, PrintWriter.
     * <p>
     * С помощью BufferedReader можно прочитать из стандартного потока:
     * строку -- reader.readLine()
     * число -- int n = Integer.parseInt(reader.readLine());
     * массив чисел известной длины (во входном потоке каждое число на новой строке) --
     * int[] nums = new int[len];
     * for (int i = 0; i < len; i++) {
     * nums[i] = Integer.parseInt(reader.readLine());
     * }
     * последовательность слов в строке --
     * String[] parts = reader.readLine().split(" ");
     * <p>
     * Чтобы вывести результат в стандартный поток вывода (System.out),
     * Через BufferedWriter можно использовать методы
     * writer.write("Строка"), writer.write('A') и writer.newLine().
     * <p>
     * Возможное решение задачи "Вычислите сумму чисел в строке":
     * int sum = 0;
     * String[] parts = reader.readLine().split(" ");
     * for (int i = 0; i < parts.length; i++) {
     * int num = Integer.parseInt(parts[i]);
     * sum += num;
     * }
     * writer.write(String.valueOf(sum));
     */


    public static void main(String[] args) throws IOException {
        solve(
                new InputStreamReader(System.in, StandardCharsets.UTF_8),
                new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
    }

    static void solve(Reader input, Writer output) throws IOException {
        BufferedWriter writer = new BufferedWriter(output);
        String line = readBoundedLine(input);
        if (line == null || line.isBlank()) {
            return;
        }
        String[] parts = line.trim().split("\\s+");

        try {
            writer.write(String.valueOf(average(parts)));
        } catch (IllegalArgumentException ignored) {
            return;
        }
        writer.flush();
    }

    static Integer average(String[] list) {
        if (list.length < 2) {
            throw new IllegalArgumentException("At least two integers are required");
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        for (String value : list) {
            int number = parseInteger(value);
            if (number < smallest) {
                secondSmallest = smallest;
                smallest = number;
            } else if (number < secondSmallest) {
                secondSmallest = number;
            }
        }
        return secondSmallest;
    }

    private static String readBoundedLine(Reader input) throws IOException {
        StringBuilder line = new StringBuilder();
        for (int character = input.read(); character != -1 && character != '\n'; character = input.read()) {
            if (character == '\r') {
                break;
            }
            if (line.length() == MAX_INPUT_CHARACTERS) {
                return null;
            }
            line.append((char) character);
        }
        return line.isEmpty() ? null : line.toString();
    }

    private static Integer parseInteger(String value) {
        return parseInt(value);
    }
}
