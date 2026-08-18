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

    public static int parseUnsignedInt(CharSequence value) {
        if (value == null || value.length() == 0) {
            throw new NumberFormatException("Expected unsigned integer");
        }

        int result = 0;
        for (int index = 0; index < value.length(); index++) {
            char current = value.charAt(index);
            if (current < '0' || current > '9') {
                throw new NumberFormatException("Invalid unsigned integer: " + value);
            }
            int digit = current - '0';
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                throw new NumberFormatException("Unsigned integer is out of range: " + value);
            }
            result = result * 10 + digit;
        }
        return result;
    }
}
