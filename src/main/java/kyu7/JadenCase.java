package kyu7;


public class JadenCase {

    //7 https://www.codewars.com/kata/5390bac347d09b7da40006f6/train/java

    public static String toJadenCase(String phrase) {
        if (phrase == null || phrase.isEmpty()) {
            return null;
        }
        StringBuilder result = new StringBuilder(phrase.length());
        boolean capitalizeNext = true;
        for (int i = 0; i < phrase.length(); i++) {
            char current = phrase.charAt(i);
            if (Character.isWhitespace(current)) {
                result.append(current);
                capitalizeNext = true;
            } else {
                result.append(capitalizeNext ? Character.toUpperCase(current) : current);
                capitalizeNext = false;
            }
        }
        return result.toString();
    }



}
