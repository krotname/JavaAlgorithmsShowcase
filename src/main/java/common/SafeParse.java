package common;

public final class SafeParse {

    private SafeParse() {
    }

    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            NumberFormatException wrapped = new NumberFormatException("Invalid integer: " + value);
            wrapped.initCause(e);
            throw wrapped;
        }
    }

    public static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            NumberFormatException wrapped = new NumberFormatException("Invalid number: " + value);
            wrapped.initCause(e);
            throw wrapped;
        }
    }
}
