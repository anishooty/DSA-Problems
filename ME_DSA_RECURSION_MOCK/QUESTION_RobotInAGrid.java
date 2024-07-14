import java.util.*;

public class RobotInAGrid {

    // Implement your Solution Here
    Vector<String> robotInAGrid(int grid[][] , int n , int m) {
        Vector<String> path = new Vector<>();
        if (grid == null || grid.length == 0) {
            return path;
        }

        boolean[][] visited = new boolean[n][m];
        if (getPath(grid, n, m, 0, 0, visited, path)) {
            return path;
        }

        path.clear();
        path.add("Not Possible");
        return path;
    }

    boolean getPath(int[][] grid, int n, int m, int row, int col, boolean[][] visited, Vector<String> path) {
        if (row < 0 || col < 0 || row >= n || col >= m || grid[row][col] == 1 || visited[row][col]) {
            return false;
        }

        visited[row][col] = true;
        path.add((row + 1) + " " + (col + 1));

        if (row == n - 1 && col == m - 1) {
            return true;
        }

        if (getPath(grid, n, m, row, col + 1, visited, path) || getPath(grid, n, m, row + 1, col, visited, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int grid[][] = new int[n][m];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        RobotInAGrid answer = new RobotInAGrid();
        Vector<String> result = answer.robotInAGrid(grid , n , m);
        for(String elem : result) {
            System.out.println(elem);
        }
    }
}
