package kyu3;


public class BattleField {

    // 3 https://www.codewars.com/kata/52bb6539a4cf1b12d90005b7

    private static final int BATTLESHIPS = 1;
    private static final int CRUISERS = 2;
    private static final int DESTROYERS = 3;
    private static final int SUBMARINES = 4;
    private static final int MAX_SHIP_LENGTH = 4;

    /**
     * Validates whether a battleship field contains exactly the required fleet
     * (1 battleship, 2 cruisers, 3 destroyers, 4 submarines).
     */
    public static boolean fieldValidator(int[][] field) {
        if (!isRectangularField(field)) {
            return false;
        }

        boolean[][] visited = new boolean[field.length][field[0].length];
        int[] shipsByLength = new int[MAX_SHIP_LENGTH + 1];

        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[row].length; column++) {
                if (field[row][column] != 0 && field[row][column] != 1) {
                    return false;
                }
                if (field[row][column] == 0 || visited[row][column]) {
                    continue;
                }
                if (hasDiagonalNeighbor(field, row, column)) {
                    return false;
                }

                int length = classifyShip(field, visited, row, column);
                if (length < 1 || length > MAX_SHIP_LENGTH) {
                    return false;
                }
                shipsByLength[length]++;
            }
        }

        return shipsByLength[4] == BATTLESHIPS
                && shipsByLength[3] == CRUISERS
                && shipsByLength[2] == DESTROYERS
                && shipsByLength[1] == SUBMARINES;
    }

    private static boolean isRectangularField(int[][] field) {
        if (field == null || field.length == 0 || field[0] == null) {
            return false;
        }
        int columns = field[0].length;
        for (int[] row : field) {
            if (row == null || row.length != columns) {
                return false;
            }
        }
        return true;
    }

    private static int classifyShip(int[][] field, boolean[][] visited, int row, int column) {
        boolean horizontal = isOccupied(field, row, column + 1);
        boolean vertical = isOccupied(field, row + 1, column);
        if (horizontal && vertical) {
            return -1;
        }

        if (horizontal) {
            return markHorizontalShip(field, visited, row, column);
        }
        if (vertical) {
            return markVerticalShip(field, visited, row, column);
        }
        if (hasSideNeighbor(field, row, column)) {
            return -1;
        }
        visited[row][column] = true;
        return 1;
    }

    private static int markHorizontalShip(int[][] field, boolean[][] visited, int row, int column) {
        int length = 0;
        int current = column;
        while (isOccupied(field, row, current)) {
            if (visited[row][current]
                    || isOccupied(field, row - 1, current)
                    || isOccupied(field, row + 1, current)) {
                return -1;
            }
            visited[row][current] = true;
            length++;
            current++;
        }
        if (isOccupied(field, row, column - 1) || isOccupied(field, row, current)) {
            return -1;
        }
        return length;
    }

    private static int markVerticalShip(int[][] field, boolean[][] visited, int row, int column) {
        int length = 0;
        int current = row;
        while (isOccupied(field, current, column)) {
            if (visited[current][column]
                    || isOccupied(field, current, column - 1)
                    || isOccupied(field, current, column + 1)) {
                return -1;
            }
            visited[current][column] = true;
            length++;
            current++;
        }
        if (isOccupied(field, row - 1, column) || isOccupied(field, current, column)) {
            return -1;
        }
        return length;
    }

    private static boolean hasDiagonalNeighbor(int[][] field, int row, int column) {
        return isOccupied(field, row - 1, column - 1)
                || isOccupied(field, row - 1, column + 1)
                || isOccupied(field, row + 1, column - 1)
                || isOccupied(field, row + 1, column + 1);
    }

    private static boolean hasSideNeighbor(int[][] field, int row, int column) {
        return isOccupied(field, row - 1, column)
                || isOccupied(field, row + 1, column)
                || isOccupied(field, row, column - 1)
                || isOccupied(field, row, column + 1);
    }

    private static boolean isOccupied(int[][] field, int row, int column) {
        return row >= 0
                && row < field.length
                && column >= 0
                && column < field[row].length
                && field[row][column] == 1;
    }

    public static int[][] fieldWithEdges(int[][] field) {
        int[][] fieldWithEdges = new int[field.length + 2][field[0].length + 2];
        for (int i = 0; i < fieldWithEdges.length; i++)
            for (int j = 0; j < fieldWithEdges[i].length; j++)
                if (i == 0 || j == 0) fieldWithEdges[i][j] = 0;

        for (int i = 1; i < fieldWithEdges.length - 1; i++)
            for (int j = 1; j < fieldWithEdges[i].length - 1; j++)
                fieldWithEdges[i][j] = field[i - 1][j - 1];
        return fieldWithEdges;
    }


}
