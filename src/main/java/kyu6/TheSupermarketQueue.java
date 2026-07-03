package kyu6;


import java.util.LinkedList;


//6 https://www.codewars.com/kata/57b06f90e298a7b53d000a86/train/java

public class TheSupermarketQueue {

    public static int solveSuperMarketQueue(int[] customers, int n) {

        if (customers.length == 0) return 0;

        LinkedList<Integer> integers = new LinkedList<>();
        for (int i : customers)
            integers.add(i);

        int[] workList = new int[n];

        for (int i = 0; i < n; i++) {
            if (!integers.isEmpty()) {

                workList[i] = integers.pollFirst();
            } else {
                workList[i] = 0;
            }
        }

        int count = 0;

        while (true) {
            int stopCount = 0;
            for (int j : workList) {
                if (j == -1) {
                    stopCount++;
                }
            }

            if (stopCount == n) break;

            count++;

            for (int i = 0; i < workList.length; i++) {
                if (workList[i] > 1) {
                    workList[i]--;
                } else if (!integers.isEmpty()) {
                    workList[i] = integers.pollFirst();
                } else {
                    workList[i] = -1;
                }
            }
        }

        return count;
    }

}
