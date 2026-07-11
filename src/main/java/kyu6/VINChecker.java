package kyu6;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class VINChecker {

    //6

    private static final Map<Character, Integer> LETTERS = new HashMap<>();

    static {
        LETTERS.put('A', 1);
        LETTERS.put('B', 2);
        LETTERS.put('C', 3);
        LETTERS.put('D', 4);
        LETTERS.put('E', 5);
        LETTERS.put('F', 6);
        LETTERS.put('G', 7);
        LETTERS.put('H', 8);
        LETTERS.put('J', 1);
        LETTERS.put('K', 2);
        LETTERS.put('L', 3);
        LETTERS.put('M', 4);
        LETTERS.put('N', 5);
        LETTERS.put('P', 7);
        LETTERS.put('R', 9);
        LETTERS.put('S', 2);
        LETTERS.put('T', 3);
        LETTERS.put('U', 4);
        LETTERS.put('V', 5);
        LETTERS.put('W', 6);
        LETTERS.put('X', 7);
        LETTERS.put('Y', 8);
        LETTERS.put('Z', 9);
        LETTERS.put('1', 1);
        LETTERS.put('2', 2);
        LETTERS.put('3', 3);
        LETTERS.put('4', 4);
        LETTERS.put('5', 5);
        LETTERS.put('6', 6);
        LETTERS.put('7', 7);
        LETTERS.put('8', 8);
        LETTERS.put('9', 9);
        LETTERS.put('0', 0);
    }

    public static boolean checkVin(String vin) {
        if (vin == null) {
            return false;
        }
        String normalizedVin = vin.toUpperCase(Locale.ROOT);
        if (normalizedVin.contains("I") || normalizedVin.contains("O")
                || normalizedVin.contains("Q") || normalizedVin.length() != 17) return false;

        ArrayList<Integer> convertedToNumber = new ArrayList<>();
        char[] vinChars = normalizedVin.toCharArray();
        for (Character c : vinChars
        ) {
            if (!LETTERS.containsKey(c)) return false;
            convertedToNumber.add(LETTERS.get(c));
        }
        List<Integer> multiplier = Arrays.asList(8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2);

        ArrayList<Integer> product = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            product.add(convertedToNumber.get(i) * multiplier.get(i));
        }
        int sumProduct = 0;

        for (Integer i : product
        ) {
            sumProduct += i;
        }
        int sumProductMod = sumProduct % 11;

        if (sumProductMod == 10 && vinChars[8] == 'X') return true;
        return String.valueOf(sumProductMod).equalsIgnoreCase(String.valueOf(vinChars[8]));
    }

    /**
     * In this Kata you should write a function to validate VINs, Vehicle Identification Numbers. Valid VINs
     * are exactly 17 characters long, its composed of capital letters (except "I","O" and "Q") and digits.
     * The 9th character is a MODULUS 11 check digit. Here is how it works:
     * <p>
     * 1. Letters are converted to numbers
     * Following the table bellow, letters are converted to numbers. "I","O" and "Q" are invalid characters.
     * <p>
     * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
     * 1 2 3 4 5 6 7 8   1 2 3 4 5   7   9 2 3 4 5 6 7 8 9
     * Ex.: VIN 5YJ3E1EA7HF000337 becomes 58135151786000337.
     * <p>
     * 2. Each number is multiplied by a weight
     * The weights are the following: [8,7,6,5,4,3,2,10,0,9,8,7,6,5,4,3,2].
     * <p>
     * Ex.:
     * VIN:     5   Y   J   3   E   1   E   A   7   H   F   0   0   0   3   3   7
     * DECODED: 5   8   1   3   5   1   5   1   7   8   6   0   0   0   3   3   7
     * WEIGHTS: 8   7   6   5   4   3   2   10  0   9   8   7   6   5   4   3   2
     * PRODUCT: 40  56  6   15  20  3   10  10  0   72  48  0   0   0   12  9   14
     * 3. All products are summed up
     * Ex.:
     * 40+56+6+15+20+3+10+10+0+72+48+0+0+0+12+9+14 = 315
     * 4. The modulus 11 of the sum is taken
     * Ex.:
     * 315 mod 11 = 7
     * 5. Check 9th character
     * If the 9th character matches the modulus 11, the VIN is valid.
     * <p>
     * Ex.:
     * 5YJ3E1EA7HF000337 is a valid VIN, 9th character is 7
     * Note
     * If the modulus 11 of the sum is equal to 10, then the digit is "X".
     * <p>
     * Ex.:
     * 5YJ3E1EAXHF000347 is a valid VIN.
     * Input Validation
     * Input validation is part of the Kata, VINs with lenghts different than 17 characters or containing
     * invalid characters should return False as well.
     */

}
