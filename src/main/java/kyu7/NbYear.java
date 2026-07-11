package kyu7;



public class NbYear {

    //7 https://www.codewars.com/kata/563b662a59afc2b5120000c6/train/java

    public static int nbYear(int p0, double percent, int aug, int p) {
        if (p0 < 0 || p < 0 || !Double.isFinite(percent) || percent < 0.0) {
            throw new IllegalArgumentException("population and growth inputs must be finite and non-negative");
        }
        if (p0 >= p) {
            return 0;
        }
        int year = 0;
        while (p0 < p) {
            long nextPopulation = (long) (p0 + p0 * (percent / 100.0) + aug);
            if (nextPopulation <= p0) {
                throw new IllegalArgumentException("the supplied growth cannot reach the target population");
            }
            year++;
            if (nextPopulation >= p) {
                return year;
            }
            p0 = Math.toIntExact(nextPopulation);
        }
        return year;
    }


}
