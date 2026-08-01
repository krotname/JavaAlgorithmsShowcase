package kyu6;




public class NumberInExpandedForm {

    //6 https://www.codewars.com/kata/5842df8ccbd22792a4000245/train/java

    public static String expandedForm(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                result.append(chars[i]).append("0".repeat(Math.max(0, chars.length - (i + 1)))).append(" + ");
            }
        }
        return result.substring(0, result.length() - 3);
    }

}
