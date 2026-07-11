package kyu7;


public class RoundToTheNextMultipleOf5 {

    //7 https://www.codewars.com/kata/55d1d6d5955ec6365400006d/train/java

    public static int roundToNext5(int number) {
        int remainder = Math.floorMod(number, 5);
        return remainder == 0 ? number : Math.addExact(number, 5 - remainder);
    }



}
