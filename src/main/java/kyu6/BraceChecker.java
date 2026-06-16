package kyu6;


import java.util.LinkedList;
import java.util.Objects;


public class BraceChecker {

    // 6 https://www.codewars.com/kata/5277c8a221e209d3f6000b56/train/java

    public static boolean isValid(String braces) {
        var bracketsStack = new LinkedList<Character>();
        for (char c : braces.toCharArray()) {
            if (c == '(') bracketsStack.add(c);
            if (c == '[') bracketsStack.add(c);
            if (c == '{') bracketsStack.add(c);
            if (c == ')') {
                if (!bracketsStack.isEmpty() && Objects.equals(bracketsStack.peekLast(), '(')) {
                    bracketsStack.removeLast();
                } else return false;
            }
            if (c == ']') {
                if (!bracketsStack.isEmpty() && Objects.equals(bracketsStack.peekLast(), '[')) {
                    bracketsStack.removeLast();
                } else return false;
            }
            if (c == '}') {
                if (!bracketsStack.isEmpty() && Objects.equals(bracketsStack.peekLast(), '{')) {
                    bracketsStack.removeLast();
                } else return false;
            }

        }
        return bracketsStack.isEmpty();
    }


}
