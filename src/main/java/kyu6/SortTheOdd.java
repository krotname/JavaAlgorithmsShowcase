package kyu6;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SortTheOdd {

    // 6 https://www.codewars.com/kata/578aa45ee9fd15ff4600090d/train/java

    public static int[] sortArray(int[] array) {
        ArrayList<Integer> odd = new ArrayList<>();

        for (int value : array) {
            if (value % 2 != 0) {
                odd.add(value);
            }
        }

        Collections.sort(odd);

        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                array[i] = odd.get(index++);
            }
        }
        return array;
    }

    public static int[] sortArrayStream(int[] array) {
        LinkedList<Integer> oddSorted = IntStream
                .range(0, array.length)
                .filter(x -> array[x] % 2 != 0)
                .map(x -> array[x])
                .sorted()
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        IntStream.range(0, array.length).forEach(
                x -> {
                    if (array[x] % 2 != 0) {
                        array[x] = oddSorted.pollFirst();
                    }
                });

        return array;
    }


}
