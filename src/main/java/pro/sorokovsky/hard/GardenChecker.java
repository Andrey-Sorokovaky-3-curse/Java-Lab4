package pro.sorokovsky.hard;

public class GardenChecker {

    private int[][] garden;
    private boolean[][] visited;
    private int rows, cols;

    public String checkGarden(int[][] gardenGrid) {
        if (gardenGrid == null || gardenGrid.length == 0) {
            return "ПОМИЛКА: Порожня ділянка";
        }

        this.garden = gardenGrid;
        this.rows = garden.length;
        this.cols = garden[0].length;
        this.visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cell = garden[i][j];
                if (cell != 0 && !visited[i][j]) {
                    if (!isRectangleBed(i, j, cell)) {
                        return "ПОМИЛКА: Грядка " + cell + " не має прямокутної форми!";
                    }
                }
            }
        }

        return "OK: Всі грядки мають прямокутну форму та не дотикаються";
    }

    private boolean isRectangleBed(int startRow, int startCol, int bedId) {
        int maxRow = startRow;
        int maxCol = startCol;

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

        for (int i = startRow; i <= maxRow; i++) {
            for (int j = startCol; j <= maxCol; j++) {
                if (i >= rows || j >= cols || garden[i][j] != bedId) {
                    return false;
                }
            }
        }

        if (!hasNoAdjacentBeds(startRow, maxRow, startCol, maxCol, bedId)) {
            return false;
        }

        for (int i = startRow; i <= maxRow; i++) {
            for (int j = startCol; j <= maxCol; j++) {
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
}