package pro.sorokovsky.hard;

public class GardenSquareCounter {

    private int[][] garden;
    private boolean[][] visited;
    private int rows, cols;
    private int squareCount;
    private int totalRectCount;

    public String countSquareBeds(int[][] gardenGrid) {
        if (gardenGrid == null || gardenGrid.length == 0) {
            return "ПОМИЛКА: Порожня ділянка";
        }

        this.garden = gardenGrid;
        this.rows = garden.length;
        this.cols = garden[0].length;
        this.visited = new boolean[rows][cols];
        this.squareCount = 0;
        this.totalRectCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cell = garden[i][j];
                if (cell != 0 && !visited[i][j]) {
                    if (!isValidRectangleBed(i, j, cell)) {
                        return "ПОМИЛКА: Грядка " + cell + " не є прямокутною або дотикається до іншої!";
                    }
                }
            }
        }

        if (totalRectCount == 0) {
            return "На ділянці немає грядок";
        } else if (squareCount == 0) {
            return "Квадратних грядок немає. Всього грядок: " + totalRectCount;
        } else if (squareCount == totalRectCount) {
            return "Всі грядки квадратні! Кількість: " + squareCount;
        } else {
            return "Квадратних грядок: " + squareCount + " з " + totalRectCount;
        }
    }

    private boolean isValidRectangleBed(int startRow, int startCol, int bedId) {
        int minRow = startRow, maxRow = startRow;
        int minCol = startCol, maxCol = startCol;

        for (int j = startCol + 1; j < cols; j++) {
            if (garden[startRow][j] == bedId && !visited[startRow][j]) {
                maxCol = j;
            } else break;
        }

        for (int i = startRow + 1; i < rows; i++) {
            if (garden[i][startCol] == bedId && !visited[i][startCol]) {
                maxRow = i;
            } else break;
        }

        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (i >= rows || j >= cols || garden[i][j] != bedId) {
                    return false;
                }
            }
        }

        if (!hasNoAdjacentBeds(minRow, maxRow, minCol, maxCol, bedId)) {
            return false;
        }

        int height = maxRow - minRow + 1;
        int width = maxCol - minCol + 1;
        totalRectCount++;

        if (height == width) {
            squareCount++;
        }

        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                visited[i][j] = true;
            }
        }

        return true;
    }

    private boolean hasNoAdjacentBeds(int minRow, int maxRow, int minCol, int maxCol, int bedId) {
        if (minRow > 0) {
            for (int j = minCol; j <= maxCol; j++) {
                if (garden[minRow - 1][j] != 0 && garden[minRow - 1][j] != bedId) {
                    return false;
                }
            }
        }

        if (maxRow + 1 < rows) {
            for (int j = minCol; j <= maxCol; j++) {
                if (garden[maxRow + 1][j] != 0 && garden[maxRow + 1][j] != bedId) {
                    return false;
                }
            }
        }
        if (minCol > 0) {
            for (int i = minRow; i <= maxRow; i++) {
                if (garden[i][minCol - 1] != 0 && garden[i][minCol - 1] != bedId) {
                    return false;
                }
            }
        }
        if (maxCol + 1 < cols) {
            for (int i = minRow; i <= maxRow; i++) {
                if (garden[i][maxCol + 1] != 0 && garden[i][maxCol + 1] != bedId) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getSquareCount() {
        return squareCount;
    }

    public int getTotalRectCount() {
        return totalRectCount;
    }
}