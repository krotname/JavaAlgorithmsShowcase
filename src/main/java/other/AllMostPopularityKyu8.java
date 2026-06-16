package other;


import static common.SafeParse.parseInt;

import java.util.Arrays;


public class AllMostPopularityKyu8 {

    public static int sumR(int[] arr) {
        int rezult = 0;
        if (arr == null || arr.length == 0) {
            return rezult;
        }
        for (int num : arr) {
            if (num > 0) {
                rezult += num;
            }
        }
        return rezult;
    }

    public static String repeatStr(final int repeat, final String string) {
        return String.valueOf(string).repeat(Math.max(0, repeat));
    }

    public static String removeFirstAndLastChar(String str) {
        return str.substring(1, str.length() - 1);

    }

    public static int opposite(int number) {
        return number * -1;
    }

    public static String evenOrOdd(int number) {
        return number % 2 == 0 ? "Even" : "Odd";
    }

    public static int sumArray(int[] numbers) {
        //сумма кроме самого большого и маленького
        if (numbers == null || numbers.length < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int n : numbers) {
            sum += n;
            if (n > max) {
                max = n;
            }
            if (n < min) {
                min = n;
            }
        }
        return sum - max - min;
    }

    public static String fakeBin(String numberString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : numberString.toCharArray()) {
            if (Character.getNumericValue(c) >= 5) {
                stringBuilder.append("1");
            } else if (Character.getNumericValue(c) < 5) {
                stringBuilder.append("0");
            }
        }
        return stringBuilder.toString();
    }

    public static int stringToNumber(String str) {
        return parseInt(str);
    }

    public static String reverseWords(String str) {
        String[] s = str.trim().split("\\s+");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.length - 1; i >= 0; i--) {
            stringBuilder.append(s[i]).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static int[] allTo2(int[] arr) {
        return Arrays.stream(arr).map(x -> x * 2).toArray();
    }

    public static int[] invert(int[] array) {
        return Arrays.stream(array).map(x -> x * -1).toArray();
    }

    public static int getAverage(int[] marks) {
        return (int) Arrays.stream(marks).average().orElse(0);
    }

    public static int[] countPositivesSumNegatives(int[] input) {
        if (input == null || input.length == 0) {
            return new int[0];
        }
        int countPositive = 0;
        int summNegative = 0;
        for (int i : input) {
            if (i > 0) {
                countPositive++;
            } else if (i < 0) {
                summNegative += i;
            }
        }
        int[] result = new int[2];
        result[0] = countPositive;
        result[1] = summNegative;
        return result; //return an array with count of positives and sum of negatives
    }

    public static String abbrevName(String name) {
        String[] s = name.split(" ");
        return s[0].substring(0, 1).toUpperCase() + "." + s[1].substring(0, 1).toUpperCase();
    }

    public static int timeInSeconds(int h, int m, int s) {
        return s * 1000 + m * 60 * 1000 + h * 60 * 60 * 1000;
    }

    public static int[] digitize(long n) {
        String digits = Long.toString(Math.abs(n));
        int[] r = new int[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            r[i] = Character.digit(digits.charAt(digits.length() - 1 - i), 10);
        }
        return r;
    }

    public static String boolToWord(boolean b) {
        return b ? "Yes" : "No";
    }

    public static String reversedStrings(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static int summation(int n) {
        if (n <= 0) {
            return 0;
        }
        return n * (n + 1) / 2;
    }

    public static String noSpace(final String x) {
        return x.replaceAll("\\s", "");
    }

    public static String numberToString(Number num) {
        return String.valueOf(num);
    }

    public static int findSmallestInt(int[] args) {
        return Arrays.stream(args).min().orElseThrow();
    }

    public static int findSmallestIntBadO(int[] args) {
        if (args == null || args.length == 0) {
            return 0;
        }
        if (args.length == 1) {
            return args[0];
        }
        int min = args[0];
        for (int i = 1; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }
        return min;
    }

    public static int countSheep(Boolean[] arrayOfSheep) {
        if (arrayOfSheep == null || arrayOfSheep.length == 0) {
            return 0;
        }
        int count = 0;
        for (Boolean b : arrayOfSheep) {
            if (b != null && b) {
                count++;
            }
        }
        return count;
    }

    public static int squareSum(int[] n) {
        if (n == null || n.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i : n) {
            sum += i * i;
        }
        return sum;
    }

    public static int century(int number) {
        return number <= 0 ? 0 : (number + 99) / 100;
    }

    public static int liters(double time) {
        return (int) (time / 2);
    }

    public static boolean isDivisible(long n, long x, long y) {
        if (n <= 0 || x <= 0 || y <= 0) {
            return false;
        }
        return n % x == 0 && n % y == 0;
    }

    public static Integer basicMath(String op, int v1, int v2) {
        if (op == null || (op.equals("/") && v2 == 0)) {
            throw new IllegalArgumentException();
        }
        return switch (op) {
            case "+" -> v1 + v2;
            case "-" -> v1 - v2;
            case "*" -> v1 * v2;
            case "/" -> v1 / v2;
            default -> throw new IllegalArgumentException();
        };
    }

}
