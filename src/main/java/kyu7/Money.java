package kyu7;



public class Money {

    //7 https://www.codewars.com/kata/563f037412e5ada593000114/train/java

    public static int calculateYears(double principal, double interest, double tax, double desired) {
        if (!Double.isFinite(principal) || !Double.isFinite(interest)
                || !Double.isFinite(tax) || !Double.isFinite(desired)
                || principal < 0.0 || interest < 0.0 || tax < 0.0 || tax > 1.0 || desired < 0.0) {
            throw new IllegalArgumentException("financial inputs must be finite and non-negative");
        }
        if (principal >= desired) {
            return 0;
        }
        int years = 0;
        while (principal < desired) {
            double addOneYear = principal * interest;
            double nextPrincipal = principal + addOneYear * (1.0 - tax);
            if (!(nextPrincipal > principal)) {
                throw new IllegalArgumentException("the supplied growth cannot reach the desired amount");
            }
            principal = nextPrincipal;
            years++;
        }
        return years;
    }

}
