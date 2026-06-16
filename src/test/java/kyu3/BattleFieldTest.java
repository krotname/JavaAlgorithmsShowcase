package kyu3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
class BattleFieldTest {

    @Test
    void shouldAcceptAValidFleetLayout() {
        assertTrue(BattleField.fieldValidator(validField()));
    }

    @Test
    void shouldAcceptAValidFleetLayoutWithVerticalShips() {
        assertTrue(BattleField.fieldValidator(transpose(validField())));
    }

    @Test
    void shouldRejectShipsThatTouchDiagonally() {
        int[][] field = validField();
        field[1][4] = 1;

        assertFalse(BattleField.fieldValidator(field));
    }

    @Test
    void shouldRejectShipsThatTouchBySide() {
        int[][] field = validField();
        field[1][0] = 1;

        assertFalse(BattleField.fieldValidator(field));
    }

    @Test
    void shouldRejectShipsLongerThanBattleship() {
        int[][] field = validField();
        field[0][4] = 1;

        assertFalse(BattleField.fieldValidator(field));
    }

    @Test
    void shouldRejectWrongFleetComposition() {
        int[][] field = validField();
        field[8][5] = 0;

        assertFalse(BattleField.fieldValidator(field));
    }

    @Test
    void shouldRejectMalformedFields() {
        assertFalse(BattleField.fieldValidator(null));
        assertFalse(BattleField.fieldValidator(new int[0][0]));
        assertFalse(BattleField.fieldValidator(new int[][]{null}));
        assertFalse(BattleField.fieldValidator(new int[][]{{1, 0}, null}));
        assertFalse(BattleField.fieldValidator(new int[][]{{1, 0}, {1}}));
    }

    @Test
    void shouldRejectCellsOutsideBinaryDomain() {
        int[][] field = validField();
        field[9][9] = 2;

        assertFalse(BattleField.fieldValidator(field));
    }

    @Test
    void shouldRejectBentShips() {
        int[][] field = emptyField();
        field[0][0] = 1;
        field[0][1] = 1;
        field[1][0] = 1;

        assertFalse(BattleField.fieldValidator(field));
    }

    @Test
    void shouldRejectSideContactAlongHorizontalShipBody() {
        int[][] field = validField();
        field[1][2] = 1;

        assertFalse(BattleField.fieldValidator(field));
    }

    @Test
    void shouldRejectSideContactAlongVerticalShipBody() {
        int[][] field = transpose(validField());
        field[2][1] = 1;

        assertFalse(BattleField.fieldValidator(field));
    }

    @Test
    void shouldPadFieldWithZeroEdges() {
        int[][] padded = BattleField.fieldWithEdges(new int[][]{
                {1, 0},
                {0, 1}
        });

        assertEquals(4, padded.length);
        assertArrayEquals(new int[]{0, 0, 0, 0}, padded[0]);
        assertArrayEquals(new int[]{0, 1, 0, 0}, padded[1]);
        assertArrayEquals(new int[]{0, 0, 1, 0}, padded[2]);
        assertArrayEquals(new int[]{0, 0, 0, 0}, padded[3]);
    }

    private static int[][] validField() {
        return new int[][]{
                {1, 1, 1, 1, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    private static int[][] emptyField() {
        return new int[10][10];
    }

    private static int[][] transpose(int[][] field) {
        int[][] transposed = new int[field[0].length][field.length];
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[row].length; column++) {
                transposed[column][row] = field[row][column];
            }
        }
        return transposed;
    }
}
