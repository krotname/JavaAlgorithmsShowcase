package kyu5;


import static common.SafeParse.parseDouble;


public class ResistorColorCodes2 {
    //5

    public static String encodeResistorColors(String ohmsString) {
        if (ohmsString == null || !ohmsString.endsWith(" ohms")) return "";
        int resistorOhms = encodeResistorOhms(ohmsString);
        int[] resistorOhmsArr = encodeResistorOhmsToArr(resistorOhms);
        return encodeResistorArrToColor(resistorOhmsArr);
    }

    private static int encodeResistorOhms(String ohmsString) {
        double tempValue;
        String[] tempArray = ohmsString.split("\\s");
        if (tempArray[0].endsWith("k"))
            tempValue = parseDouble(tempArray[0].trim().substring(0, tempArray[0].length() - 1)) * 1_000;
        else if (tempArray[0].endsWith("M"))
            tempValue = parseDouble(tempArray[0].trim().substring(0, tempArray[0].length() - 1)) * 1_000_000;
        else tempValue = parseDouble(tempArray[0].trim());
        return (int) Math.round(tempValue);
    }

    private static String encodeResistorArrToColor(int[] resistorOhms) {
        return colors(resistorOhms[0]) + " " + colors(resistorOhms[1]) +
                " " + colors(resistorOhms[2]) + " " + colors(10);
    }

    private static int[] encodeResistorOhmsToArr(int resistorOhms) {
        int[] encodeResistorOhmsArr = new int[3];

        while (resistorOhms >= 100) {
            resistorOhms /= 10;
            encodeResistorOhmsArr[2]++;
        }
        encodeResistorOhmsArr[1] = resistorOhms % 10;
        encodeResistorOhmsArr[0] = resistorOhms / 10;
        return encodeResistorOhmsArr;
    }

    private static String colors(int num) {
        return switch (num) {
            case 0 -> "black";
            case 1 -> "brown";
            case 2 -> "red";
            case 3 -> "orange";
            case 4 -> "yellow";
            case 5 -> "green";
            case 6 -> "blue";
            case 7 -> "violet";
            case 8 -> "gray";
            case 9 -> "white";
            case 10 -> "gold";
            default -> "";
        };
    }

    /**
     * Overview
     * Resistors are electrical components marked with colorful stripes/bands to indicate both their resistance value
     * in ohms and how tight a tolerance that value has. If you did my Resistor Color Codes kata, you wrote a function
     * which took a string containing a resistor's band colors, and returned a string identifying the resistor's ohms and tolerance values.
     * <p>
     * Well, now you need that in reverse: The previous owner of your "Beyond-Ultimate Raspberry Pi Starter Kit"
     * (as featured in my Fizz Buzz Cuckoo Clock kata) had emptied all the tiny labeled zip-lock bags of components
     * into the box, so that for each resistor you need for a project, instead of looking for text on a label,
     * you need to find one with the sequence of band colors that matches the ohms value you need.
     * <p>
     * The resistor color codes
     * You can see this Wikipedia page for a colorful chart, but the basic resistor color codes are:
     * <p>
     * 0: black, 1: brown, 2: red, 3: orange, 4: yellow, 5: green, 6: blue, 7: violet, 8: gray, 9: white
     * <p>
     * All resistors have at least three bands, with the first and second bands indicating the first two digits of
     * the ohms value, and the third indicating the power of ten to multiply them by, for example a resistor with a
     * value of 47 ohms, which equals 47 * 10^0 ohms, would have the three bands "yellow violet black".
     * <p>
     * Most resistors also have a fourth band indicating tolerance -- in an electronics kit like yours, the tolerance
     * will always be 5%, which is indicated by a gold band. So in your kit, the 47-ohm resistor in the above paragraph
     * would have the four bands "yellow violet black gold".
     * <p>
     * Your mission
     * Your function will receive a string containing the ohms value you need, followed by a space and the word "ohms"
     * (to avoid Codewars unicode headaches I'm just using the word instead of the ohms unicode symbol).
     * The way an ohms value is formatted depends on the magnitude of the value:
     * <p>
     * For resistors less than 1000 ohms, the ohms value is just formatted as the plain number. For example,
     * with the 47-ohm resistor above, your function would receive the string "47 ohms",
     * and return the string `"yellow violet black gold".
     * <p>
     * For resistors greater than or equal to 1000 ohms, but less than 1000000 ohms, the ohms value is divided by 1000,
     * and has a lower-case "k" after it. For example, if your function received the string "4.7k ohms",
     * it would need to return the string "yellow violet red gold".
     * <p>
     * For resistors of 1000000 ohms or greater, the ohms value is divided by 1000000, and has an upper-case "M" after it.
     * For example, if your function received the string "1M ohms", it would need to return the string "brown black green gold".
     * <p>
     * Test case resistor values will all be between 10 ohms and 990M ohms.
     * <p>
     * More examples, featuring some common resistor values from your kit
     * "10 ohms"        "brown black black gold"
     * "100 ohms"       "brown black brown gold"
     * "220 ohms"       "red red brown gold"
     * "330 ohms"       "orange orange brown gold"
     * "470 ohms"       "yellow violet brown gold"
     * "680 ohms"       "blue gray brown gold"
     * "1k ohms"        "brown black red gold"
     * "10k ohms"       "brown black orange gold"
     * "22k ohms"       "red red orange gold"
     * "47k ohms"       "yellow violet orange gold"
     * "100k ohms"      "brown black yellow gold"
     * "330k ohms"      "orange orange yellow gold"
     * "2M ohms"        "red black green gold"
     */

}
