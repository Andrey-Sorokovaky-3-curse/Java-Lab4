package pro.sorokovsky.hard;

public class GardenCounter {

    private boolean[][] visited;

    public String countBeds(int[][] garden) {
        if (garden == null || garden.length == 0) {
            return "ПОМИЛКА: Порожня ділянка";
        }

        int rows = garden.length;
        int cols = garden[0].length;
        visited = new boolean[rows][cols];
        int bedCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (garden[i][j] != 0 && !visited[i][j]) {
                    floodFill(i, j, rows, cols, garden);
                    bedCount++;
                }
            }
        }

        if (bedCount == 0) {
            return "Кількість грядок: 0";
        } else if (bedCount == 1) {
            return "Кількість грядок: 1";
        } else {
            return "Кількість грядок: " + bedCount;
        }
    }

    private void floodFill(int row, int col, int rows, int cols, int[][] garden) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) return;
        if (visited[row][col] || garden[row][col] == 0) return;

        visited[row][col] = true;

        floodFill(row + 1, col, rows, cols, garden);
        floodFill(row - 1, col, rows, cols, garden);
        floodFill(row, col + 1, rows, cols, garden);
        floodFill(row, col - 1, rows, cols, garden);
    }
}