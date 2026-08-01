package kyu7;



public class Maskify {

    //7 https://www.codewars.com/kata/5412509bd436bd33920011bc/train/java

    public static String maskify(String str) {
        if (str == null || str.length() < 5) return str;
        return "#".repeat(str.length() - 4) + str.substring(str.length() - 4);
    }

}
