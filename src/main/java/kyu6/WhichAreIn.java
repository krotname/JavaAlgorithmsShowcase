package kyu6;


import java.util.stream.Stream;


public class WhichAreIn {

    //6 https://www.codewars.com/kata/550554fd08b86f84fe000a58/train/java

    public static String[] inArray(String[] array1, String[] array2) {
        return Stream.of(array1)
                .filter(word -> Stream.of(array2)
                        .anyMatch(bigWord -> bigWord.contains(word)))
                .distinct().sorted().toList()
                .toArray(String[]::new);
    }

}
