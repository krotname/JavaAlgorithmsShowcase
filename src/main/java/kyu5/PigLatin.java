package kyu5;



public class PigLatin {

    //5

    /**
     * Move the first letter of each word to the end of it, then add "ay" to the end of the word. Leave punctuation marks untouched.
     * <p>
     * Examples
     * pigIt('Pig latin is cool'); // igPay atinlay siay oolcay
     * pigIt('Hello world !');     // elloHay orldway !
     */

    private static final String AY = "ay ";

    public static String pigIt(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = str.split("\\s");
        for (String w : words
        ) {
            if (w.isEmpty()) {
                continue;
            } else if (!w.matches("\\W")) {
                stringBuilder.append(w.substring(1)).append(w.charAt(0)).append(AY);
            } else {
                stringBuilder.append(w).append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }

}
