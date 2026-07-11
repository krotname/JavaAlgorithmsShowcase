package kyu6;


public class FindEvenIndex {

    public static int findEvenIndexV2(int[] arr) {
        return findEvenIndex(arr);
    }

    public static int findEvenIndexV1(int[] arr) {
        return findEvenIndex(arr);
    }

    private static int findEvenIndex(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array must not be null");
        }
        long rightSum = 0L;
        for (int value : arr) {
            rightSum += value;
        }
        long leftSum = 0L;
        for (int i = 0; i < arr.length; i++) {
            rightSum -= arr[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += arr[i];
        }
        return -1;
    }


}
