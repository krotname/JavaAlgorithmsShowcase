package kyu6;



public class BuildAPileOfCubes {

    //6 https://www.codewars.com/kata/5592e3bd57b64d00f3000047

    public static long findNb(long m) {
        for (long i = 0; m >= 0; i++) {
            m -= i * i * i;
            if (m == 0) return i;
        }
        return -1;
    }

    public static long findM(long n) {
        long t = n;
        long r = 0;
        for (long i = 0; i < n; i++) {
            r += t * t * t;
            t--;
        }
        return r;
    }



}
