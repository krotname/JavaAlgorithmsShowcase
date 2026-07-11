package other;



// Разворачивает каждое слово в предложении

public class SpinWords {

    public String spinWords(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("sentence must not be null");
        }
        StringBuilder result = new StringBuilder(sentence.length());
        int wordStart = 0;
        while (wordStart < sentence.length()) {
            if (Character.isWhitespace(sentence.charAt(wordStart))) {
                result.append(sentence.charAt(wordStart++));
                continue;
            }
            int wordEnd = wordStart + 1;
            while (wordEnd < sentence.length() && !Character.isWhitespace(sentence.charAt(wordEnd))) {
                wordEnd++;
            }
            if (wordEnd - wordStart >= 5) {
                for (int i = wordEnd - 1; i >= wordStart; i--) {
                    result.append(sentence.charAt(i));
                }
            } else {
                result.append(sentence, wordStart, wordEnd);
            }
            wordStart = wordEnd;
        }
        return result.toString();
    }

}
