package kyu6;


public class SimpleEncryption {

    //6 https://www.codewars.com/SimpleEncryption/57814d79a56c88e3e0000786/train/java

    public static String encrypt(final String text, final int n) {
        return transform(text, n, false);
    }

    public static String decrypt(final String encryptedText, final int n) {
        return transform(encryptedText, n, true);
    }

    private static String transform(String text, int iterations, boolean inverse) {
        if (text == null || text.isEmpty() || iterations < 1) {
            return text;
        }
        int[] permutation = encryptionPermutation(text.length());
        if (inverse) {
            permutation = invert(permutation);
        }
        int[] poweredPermutation = power(permutation, iterations);
        char[] transformed = new char[text.length()];
        for (int source = 0; source < text.length(); source++) {
            transformed[poweredPermutation[source]] = text.charAt(source);
        }
        return String.valueOf(transformed);
    }

    private static int[] encryptionPermutation(int length) {
        int[] permutation = new int[length];
        int split = length / 2;
        for (int source = 0; source < length; source++) {
            permutation[source] = (source & 1) == 1 ? source / 2 : split + source / 2;
        }
        return permutation;
    }

    private static int[] invert(int[] permutation) {
        int[] inverse = new int[permutation.length];
        for (int source = 0; source < permutation.length; source++) {
            inverse[permutation[source]] = source;
        }
        return inverse;
    }

    private static int[] power(int[] permutation, int exponent) {
        int[] result = new int[permutation.length];
        int[] factor = permutation.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        int remaining = exponent;
        while (remaining > 0) {
            if ((remaining & 1) == 1) {
                result = compose(result, factor);
            }
            remaining >>>= 1;
            if (remaining > 0) {
                factor = compose(factor, factor);
            }
        }
        return result;
    }

    private static int[] compose(int[] first, int[] second) {
        int[] result = new int[first.length];
        for (int i = 0; i < first.length; i++) {
            result[i] = second[first[i]];
        }
        return result;
    }


}
