package kyu4;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;


public class MorseCodeDecoder {

    //4 https://www.codewars.com/kata/54b72c16cd7f5154e9000457
    //6 https://www.codewars.com/kata/54b724efac3d5402db00065e

    private static final Map<String, String> ALPHABET_TO_MORSE = new LinkedHashMap<>();
    private static final Map<String, String> MORSE_TO_ALPHABET = new LinkedHashMap<>();

    static {
        ALPHABET_TO_MORSE.put("a", ".-");
        ALPHABET_TO_MORSE.put("b", "-...");
        ALPHABET_TO_MORSE.put("c", "-.-.");
        ALPHABET_TO_MORSE.put("d", "-..");
        ALPHABET_TO_MORSE.put("e", ".");
        ALPHABET_TO_MORSE.put("f", "..-.");
        ALPHABET_TO_MORSE.put("g", "--.");
        ALPHABET_TO_MORSE.put("h", "....");
        ALPHABET_TO_MORSE.put("i", "..");
        ALPHABET_TO_MORSE.put("j", ".---");
        ALPHABET_TO_MORSE.put("k", "-.-");
        ALPHABET_TO_MORSE.put("l", ".-..");
        ALPHABET_TO_MORSE.put("m", "--");
        ALPHABET_TO_MORSE.put("n", "-.");
        ALPHABET_TO_MORSE.put("o", "---");
        ALPHABET_TO_MORSE.put("p", ".--.");
        ALPHABET_TO_MORSE.put("q", "--.-");
        ALPHABET_TO_MORSE.put("r", ".-.");
        ALPHABET_TO_MORSE.put("s", "...");
        ALPHABET_TO_MORSE.put("t", "-");
        ALPHABET_TO_MORSE.put("u", "..-");
        ALPHABET_TO_MORSE.put("v", "...-");
        ALPHABET_TO_MORSE.put("w", ".--");
        ALPHABET_TO_MORSE.put("x", "-..-");
        ALPHABET_TO_MORSE.put("y", "-.--");
        ALPHABET_TO_MORSE.put("z", "--..");
        ALPHABET_TO_MORSE.put("1", ".----");
        ALPHABET_TO_MORSE.put("2", "..---");
        ALPHABET_TO_MORSE.put("3", "...--");
        ALPHABET_TO_MORSE.put("4", "....-");
        ALPHABET_TO_MORSE.put("5", ".....");
        ALPHABET_TO_MORSE.put("6", "-....");
        ALPHABET_TO_MORSE.put("7", "--...");
        ALPHABET_TO_MORSE.put("8", "---..");
        ALPHABET_TO_MORSE.put("9", "----.");
        ALPHABET_TO_MORSE.put("0", "-----");
        ALPHABET_TO_MORSE.put(" ", "   ");

        MORSE_TO_ALPHABET.put("a", "b");
        MORSE_TO_ALPHABET.put("c", "d");
        MORSE_TO_ALPHABET.put("-.-.-.", ";");
        MORSE_TO_ALPHABET.put("-...-", "=");
        MORSE_TO_ALPHABET.put("---", "O");
        MORSE_TO_ALPHABET.put("----.", "9");
        MORSE_TO_ALPHABET.put("-..-.", "/");
        MORSE_TO_ALPHABET.put(".-...", "&");
        MORSE_TO_ALPHABET.put("...--", "3");
        MORSE_TO_ALPHABET.put(".--", "W");
        MORSE_TO_ALPHABET.put("--", "M");
        MORSE_TO_ALPHABET.put("--..", "Z");
        MORSE_TO_ALPHABET.put(".----.", "'");
        MORSE_TO_ALPHABET.put("-.-.--", "!");
        MORSE_TO_ALPHABET.put("-...", "B");
        MORSE_TO_ALPHABET.put("..-", "U");
        MORSE_TO_ALPHABET.put(".----", "1");
        MORSE_TO_ALPHABET.put("-.--.-", ")");
        MORSE_TO_ALPHABET.put(".-", "A");
        MORSE_TO_ALPHABET.put("-....-", "-");
        MORSE_TO_ALPHABET.put("...-", "V");
        MORSE_TO_ALPHABET.put("...---...", "SOS");
        MORSE_TO_ALPHABET.put("-.--", "Y");
        MORSE_TO_ALPHABET.put("..", "I");
        MORSE_TO_ALPHABET.put("--.-", "Q");
        MORSE_TO_ALPHABET.put("-.", "N");
        MORSE_TO_ALPHABET.put("..---", "2");
        MORSE_TO_ALPHABET.put("-....", "6");
        MORSE_TO_ALPHABET.put("---...", ";");
        MORSE_TO_ALPHABET.put(".-.-.", "+");
        MORSE_TO_ALPHABET.put(".--.-.", "@");
        MORSE_TO_ALPHABET.put("....-", "4");
        MORSE_TO_ALPHABET.put("-----", "0");
        MORSE_TO_ALPHABET.put(".-.-.-", ".");
        MORSE_TO_ALPHABET.put("-.-.", "C");
        MORSE_TO_ALPHABET.put(".", "E");
        MORSE_TO_ALPHABET.put("..-.", "F");
        MORSE_TO_ALPHABET.put(".---", "J");
        MORSE_TO_ALPHABET.put("-.-", "K");
        MORSE_TO_ALPHABET.put(".-..", "L");
        MORSE_TO_ALPHABET.put(".-.", "R");
        MORSE_TO_ALPHABET.put("...", "S");
        MORSE_TO_ALPHABET.put("--.", "G");
        MORSE_TO_ALPHABET.put("---..", "8");
        MORSE_TO_ALPHABET.put("..--..", "?");
        MORSE_TO_ALPHABET.put("-.--.", "(");
        MORSE_TO_ALPHABET.put(".--.", "P");
        MORSE_TO_ALPHABET.put(".....", "5");
        MORSE_TO_ALPHABET.put("..--.-", "_");
        MORSE_TO_ALPHABET.put("-..", "D");
        MORSE_TO_ALPHABET.put(".-..-.", "\"");
        MORSE_TO_ALPHABET.put("-", "T");
        MORSE_TO_ALPHABET.put("....", "H");
        MORSE_TO_ALPHABET.put("--..--", ",");
        MORSE_TO_ALPHABET.put("...-..-", "$");
        MORSE_TO_ALPHABET.put("--...", "7");
        MORSE_TO_ALPHABET.put("-..-", "X");
        MORSE_TO_ALPHABET.put("", " ");
    }

    public static String decodeMorse(String morseCode) {
        if (morseCode == null || morseCode.isBlank()) {
            return "";
        }

        List<String> decodedWords = new ArrayList<>();
        String[] words = morseCode.trim().split("\\s{3,}");
        for (String word : words) {
            StringBuilder decodedWord = new StringBuilder();
            for (String symbol : word.split("\\s+")) {
                decodedWord.append(MORSE_TO_ALPHABET.getOrDefault(symbol, ""));
            }
            decodedWords.add(decodedWord.toString());
        }
        return String.join(" ", decodedWords).toUpperCase(Locale.ROOT);
    }

    public static String encode(String toMorseCode) {
        if (toMorseCode == null || toMorseCode.isBlank()) {
            return "";
        }

        List<String> encodedWords = new ArrayList<>();
        for (String word : toMorseCode.trim().split("\\s+")) {
            StringJoiner letters = new StringJoiner(" ");
            for (char letter : word.toCharArray()) {
                String morse = ALPHABET_TO_MORSE.get(String.valueOf(letter).toLowerCase(Locale.ROOT));
                if (morse == null) {
                    throw new IllegalArgumentException("Unsupported Morse character: " + letter);
                }
                letters.add(morse);
            }
            encodedWords.add(letters.toString());
        }
        return String.join("   ", encodedWords);
    }

    public static String decodeBits(String bits) {
        if (bits == null || bits.isBlank()) {
            return "";
        }

        String signal = trimSignalZeros(bits);
        if (signal.isEmpty()) {
            return "";
        }

        int unit = checkDigits(signal);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < signal.length();) {
            char bit = signal.charAt(i);
            int end = i + 1;
            while (end < signal.length() && signal.charAt(end) == bit) {
                end++;
            }

            appendMorseRun(result, bit, (end - i) / unit);
            i = end;
        }
        return result.toString().trim();
    }

    public static String trimStartZeros(String bits) {
        // Only the active part from the first transmitted pulse is relevant;
        // trailing leading zeros must be discarded before digit sampling.
        if (bits == null) {
            return "";
        }
        while (bits.startsWith("0")) {
            bits = bits.substring(1);
        }
        return bits;
    }

    public static int checkDigits(String bits) {
        // Finds the smallest consecutive run of ones/zeros across the signal.
        // That minimum is the time unit used to discretize pulse lengths.
        if (bits == null || bits.isBlank()) {
            return 0;
        }

        int minRun = Integer.MAX_VALUE;
        for (int i = 0; i < bits.length();) {
            char bit = bits.charAt(i);
            int end = i + 1;
            while (end < bits.length() && bits.charAt(end) == bit) {
                end++;
            }
            minRun = Math.min(minRun, end - i);
            i = end;
        }
        return minRun;
    }

    private static String trimSignalZeros(String bits) {
        int start = 0;
        int end = bits.length();
        while (start < end && bits.charAt(start) == '0') {
            start++;
        }
        while (end > start && bits.charAt(end - 1) == '0') {
            end--;
        }
        return bits.substring(start, end);
    }

    private static void appendMorseRun(StringBuilder result, char bit, int units) {
        if (bit == '1') {
            result.append(units >= 3 ? "-" : ".");
            return;
        }

        if (units >= 7) {
            result.append("   ");
        } else if (units >= 3) {
            result.append(" ");
        }
    }
}
