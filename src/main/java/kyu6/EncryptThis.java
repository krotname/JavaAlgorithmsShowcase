package kyu6;



public class EncryptThis {

    //6 https://www.codewars.com/kata/5848565e273af816fb000449/train/java

    public static String encryptThis(String text) {
        if (text == null || text.isBlank()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        String[] split = text.trim().split("\\s+");
        for (String s : split) {
            String res = "";
            if (s.length() == 1) {
                res = String.valueOf((int) s.toCharArray()[0]);
            } else if (s.length() == 2) {
                res = String.valueOf((int) s.toCharArray()[0]) + s.charAt(1);
            } else {
                int s1 = s.substring(0, 1).toCharArray()[0];
                String s2 = s.substring(1, 2);
                String sLast = s.substring(s.length() - 1);
                res = s1 + sLast + s.substring(2, s.length() - 1) + s2;
            }
            stringBuilder.append(res).append(" ");
        }
        return stringBuilder.toString().trim();
    }

}
