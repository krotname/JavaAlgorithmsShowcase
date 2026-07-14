package other;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("smoke")
class AllMostPopularityKyu8Test {

    @Test
    void shouldCoverBasicStringAndNumberHelpers() {
        assertEquals(20, AllMostPopularityKyu8.sumR(new int[]{1, -4, 7, 12, -2}));
        assertEquals("ababab", AllMostPopularityKyu8.repeatStr(3, "ab"));
        assertEquals("ell", AllMostPopularityKyu8.removeFirstAndLastChar("hello"));
        assertEquals(-42, AllMostPopularityKyu8.opposite(42));
        assertEquals("Even", AllMostPopularityKyu8.evenOrOdd(-4));
        assertEquals("Odd", AllMostPopularityKyu8.evenOrOdd(7));
        assertEquals(16, AllMostPopularityKyu8.sumArray(new int[]{6, 2, 1, 8, 10}));
        assertEquals("01011110001100111", AllMostPopularityKyu8.fakeBin("45385593107843568"));
        assertEquals(-123, AllMostPopularityKyu8.stringToNumber("-123"));
        assertEquals("world hello", AllMostPopularityKyu8.reverseWords("hello   world"));
    }

    @Test
    void shouldCoverArrayHelpers() {
        assertArrayEquals(new int[]{2, 4, 6}, AllMostPopularityKyu8.allTo2(new int[]{1, 2, 3}));
        assertArrayEquals(new int[]{-1, 2, -3}, AllMostPopularityKyu8.invert(new int[]{1, -2, 3}));
        assertArrayEquals(new int[]{10, -65}, AllMostPopularityKyu8.countPositivesSumNegatives(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15}));
        assertArrayEquals(new int[0], AllMostPopularityKyu8.countPositivesSumNegatives(null));
        assertArrayEquals(new int[]{0}, AllMostPopularityKyu8.digitize(0));
        assertArrayEquals(new int[]{1, 3, 2, 5, 3}, AllMostPopularityKyu8.digitize(35_231));
        assertArrayEquals(
                new int[]{8, 0, 8, 5, 7, 7, 4, 5, 8, 6, 3, 0, 2, 7, 3, 3, 2, 2, 9},
                AllMostPopularityKyu8.digitize(Long.MIN_VALUE)
        );
    }

    @Test
    void shouldCoverFormattingAndAggregationHelpers() {
        assertEquals(3, AllMostPopularityKyu8.getAverage(new int[]{2, 3, 4}));
        assertEquals("S.H", AllMostPopularityKyu8.abbrevName("Sam Harris"));
        assertEquals(61_000, AllMostPopularityKyu8.timeInSeconds(0, 1, 1));
        assertEquals("Yes", AllMostPopularityKyu8.boolToWord(true));
        assertEquals("dcba", AllMostPopularityKyu8.reversedStrings("abcd"));
        assertEquals(15, AllMostPopularityKyu8.summation(5));
        assertEquals("abc", AllMostPopularityKyu8.noSpace(" a b c "));
        assertEquals("1234", AllMostPopularityKyu8.numberToString(1234));
    }

    @Test
    void shouldCoverSearchAndCountingHelpers() {
        assertEquals(-10, AllMostPopularityKyu8.findSmallestInt(new int[]{78, 56, -10, 12}));
        assertEquals(-10, AllMostPopularityKyu8.findSmallestIntBadO(new int[]{78, 56, -10, 12}));
        assertEquals(2, AllMostPopularityKyu8.countSheep(new Boolean[]{true, null, false, true}));
        assertEquals(14, AllMostPopularityKyu8.squareSum(new int[]{1, 2, 3}));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "100, 1",
            "101, 2",
            "1905, 20",
            "2000, 20",
            "2001, 21",
            "2147483647, 21474837",
            "0, 0"
    })
    void shouldCalculateCentury(int year, int century) {
        assertEquals(century, AllMostPopularityKyu8.century(year));
    }

    @Test
    void shouldCoverRemainingSmallHelpers() {
        assertEquals(1, AllMostPopularityKyu8.liters(3.0));
        assertTrue(AllMostPopularityKyu8.isDivisible(12, 3, 4));
        assertFalse(AllMostPopularityKyu8.isDivisible(12, 0, 4));
        assertEquals(7, AllMostPopularityKyu8.basicMath("+", 3, 4));
        assertEquals(-1, AllMostPopularityKyu8.basicMath("-", 3, 4));
        assertEquals(12, AllMostPopularityKyu8.basicMath("*", 3, 4));
        assertEquals(3, AllMostPopularityKyu8.basicMath("/", 12, 4));
        assertThrows(IllegalArgumentException.class, () -> AllMostPopularityKyu8.basicMath("/", 1, 0));
    }

    @Test
    void shouldFailWhenAnIntegerResultCannotBeRepresented() {
        assertThrows(ArithmeticException.class, () -> AllMostPopularityKyu8.opposite(Integer.MIN_VALUE));
        assertThrows(ArithmeticException.class,
                () -> AllMostPopularityKyu8.sumR(new int[]{Integer.MAX_VALUE, 1}));
        assertThrows(ArithmeticException.class,
                () -> AllMostPopularityKyu8.sumArray(
                        new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 0}));
        assertThrows(ArithmeticException.class,
                () -> AllMostPopularityKyu8.timeInSeconds(Integer.MAX_VALUE, 0, 0));
        assertThrows(ArithmeticException.class,
                () -> AllMostPopularityKyu8.squareSum(new int[]{Integer.MAX_VALUE}));
        assertThrows(ArithmeticException.class,
                () -> AllMostPopularityKyu8.basicMath("+", Integer.MAX_VALUE, 1));
        assertThrows(IllegalArgumentException.class, () -> AllMostPopularityKyu8.abbrevName(null));
        assertThrows(IllegalArgumentException.class, () -> AllMostPopularityKyu8.removeFirstAndLastChar("x"));
    }
}
