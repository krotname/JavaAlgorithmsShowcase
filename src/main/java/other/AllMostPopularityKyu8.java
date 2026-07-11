package other;


import static common.SafeParse.parseInt;

import java.util.Arrays;
import java.util.Locale;


public class AllMostPopularityKyu8 {

    public static int sumR(int[] arr) {
        long result = 0L;
        if (arr == null || arr.length == 0) {
            return 0;
        }
        for (int num : arr) {
            if (num > 0) {
                result += num;
            }
        }
        return Math.toIntExact(result);
    }

    public static String repeatStr(final int repeat, final String string) {
        return String.valueOf(string).repeat(Math.max(0, repeat));
    }

    public static String removeFirstAndLastChar(String str) {
        if (str == null || str.length() < 2) {
            throw new IllegalArgumentException("input must contain at least two characters");
        }
        return str.substring(1, str.length() - 1);

    }

    public static int opposite(int number) {
        return Math.negateExact(number);
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
        long sum = 0L;
        for (int n : numbers) {
            sum += n;
            if (n > max) {
                max = n;
            }
            if (n < min) {
                min = n;
            }
        }
        return Math.toIntExact(sum - max - (long) min);
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
        return Arrays.stream(arr).map(x -> Math.multiplyExact(x, 2)).toArray();
    }

    public static int[] invert(int[] array) {
        return Arrays.stream(array).map(Math::negateExact).toArray();
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
                summNegative = Math.addExact(summNegative, i);
            }
        }
        int[] result = new int[2];
        result[0] = countPositive;
        result[1] = summNegative;
        return result; //return an array with count of positives and sum of negatives
    }

    public static String abbrevName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must contain exactly two non-empty parts");
        }
        String[] s = name.trim().split("\\s+");
        if (s.length != 2 || s[0].isEmpty() || s[1].isEmpty()) {
            throw new IllegalArgumentException("name must contain exactly two non-empty parts");
        }
        return s[0].substring(0, 1).toUpperCase(Locale.ROOT)
                + "." + s[1].substring(0, 1).toUpperCase(Locale.ROOT);
    }

    public static int timeInSeconds(int h, int m, int s) {
        long milliseconds = s * 1_000L + m * 60_000L + h * 3_600_000L;
        return Math.toIntExact(milliseconds);
    }

    public static int[] digitize(long n) {
        String digits = Long.toString(n);
        int firstDigit = digits.charAt(0) == '-' ? 1 : 0;
        int[] r = new int[digits.length() - firstDigit];
        for (int i = 0; i < r.length; i++) {
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
        return Math.toIntExact((long) n * (n + 1L) / 2L);
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
        long sum = 0L;
        for (int i : n) {
            sum = Math.addExact(sum, Math.multiplyExact((long) i, i));
        }
        return Math.toIntExact(sum);
    }

    public static int century(int number) {
        return number <= 0 ? 0 : (number - 1) / 100 + 1;
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
            case "+" -> Math.addExact(v1, v2);
            case "-" -> Math.subtractExact(v1, v2);
            case "*" -> Math.multiplyExact(v1, v2);
            case "/" -> v1 == Integer.MIN_VALUE && v2 == -1
                    ? Math.negateExact(v1)
                    : v1 / v2;
            default -> throw new IllegalArgumentException();
        };
    }

}
