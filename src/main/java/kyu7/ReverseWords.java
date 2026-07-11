package kyu7;



public class ReverseWords {

    public static String reverseWords(final String original) {
        if (original == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        StringBuilder result = new StringBuilder(original.length());
        int wordStart = 0;
        while (wordStart < original.length()) {
            if (Character.isWhitespace(original.charAt(wordStart))) {
                result.append(original.charAt(wordStart++));
                continue;
            }
            int wordEnd = wordStart + 1;
            while (wordEnd < original.length() && !Character.isWhitespace(original.charAt(wordEnd))) {
                wordEnd++;
            }
            for (int i = wordEnd - 1; i >= wordStart; i--) {
                result.append(original.charAt(i));
            }
            wordStart = wordEnd;
        }
        return result.toString();
    }

}
