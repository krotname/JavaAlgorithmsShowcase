package kyu7;


import java.util.ArrayList;
import java.util.Comparator;


public class DescendingOrder {

    //7 https://www.codewars.com/kata/5467e4d82edf8bbf40000155/train/java

    public static int sortDesc(int num) {
        if (num <= 0) return 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (num != 0) {
            int i = num % 10;
            num /= 10;
            arrayList.add(i);
        }
        arrayList.sort(Comparator.reverseOrder());
        int result = 0;
        for (int i : arrayList
        ) {
            result = result * 10 + i;
        }
        return result;
    }

}
