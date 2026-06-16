package algorithms.sprint0;

import static common.SafeParse.parseInt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static List<Integer> readList(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            throw new NumberFormatException("EOF");
        }
        return Arrays.stream(line.trim().split("\\s+"))
                .map(Utils::parseInteger)
                .collect(Collectors.toList());
    }

    public static <T> void printList(List<T> list, Writer writer) {
        for (T elem : list) {
            try {
                writer.write(String.valueOf(elem));
                writer.write(" ");
            } catch (IOException ignored) {
            }
        }
    }

    public static int readInt(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            throw new NumberFormatException("EOF");
        }
        return parseInt(line);
    }

    private static Integer parseInteger(String value) {
        return parseInt(value);
    }
}
