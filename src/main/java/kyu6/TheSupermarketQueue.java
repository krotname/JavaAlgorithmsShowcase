package kyu6;


import java.util.PriorityQueue;


//6 https://www.codewars.com/kata/57b06f90e298a7b53d000a86/train/java

public class TheSupermarketQueue {

    public static int solveSuperMarketQueue(int[] customers, int n) {
        if (customers == null) {
            throw new IllegalArgumentException("customers must not be null");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("the number of tills must be positive");
        }

        PriorityQueue<Long> tills = new PriorityQueue<>();
        for (int i = 0; i < Math.min(n, customers.length); i++) {
            tills.add(0L);
        }
        for (int customer : customers) {
            if (customer < 0) {
                throw new IllegalArgumentException("service times must not be negative");
            }
            long availableAt = tills.remove();
            tills.add(availableAt + customer);
        }
        return tills.isEmpty() ? 0 : Math.toIntExact(tills.stream().mapToLong(Long::longValue).max().orElse(0L));
    }

}
