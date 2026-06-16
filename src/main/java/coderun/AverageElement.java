package coderun;


import static common.SafeParse.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;



public class AverageElement {

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
        BufferedReader reader = new BufferedReader(input);
        BufferedWriter writer = new BufferedWriter(output);
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        String[] parts = line.trim().split("\\s+");

        writer.write(String.valueOf(average(parts)));
        writer.flush();
    }

    static Integer average(String[] list) {
        return Arrays
                .stream(list)
                .sequential()
                .map(AverageElement::parseInteger)
                .sorted()
                .toList()
                .get(1);
    }

    private static Integer parseInteger(String value) {
        return parseInt(value);
    }
}
