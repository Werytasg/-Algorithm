import java.util.LinkedList;
import java.util.Queue;

public class BF {
    public static void main(String[] args) {
        // Приклад виклику функції btf
        int m = 5; // Ширина графу
        int n = 5; // Висота графу

        // Приклад вхідного графу
        char[][] graph = {
                {'Y', 'Y', 'Y', 'G', 'G'},
                {'Y', 'Y', 'Y', 'Y', 'G'},
                {'Y', 'Y', 'W', 'W', 'W'},
                {'Y', 'R', 'R', 'W', 'W'},
                {'B', 'B', 'R', 'R', 'R'}
        };

        int i = 2; // Початковий рядок
        int j = 2; // Початковий стовпець
        char newColor = 'C'; // Новий колір

        btf(graph, i, j, newColor, m, n);

        // Вивід зміненого графу
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                System.out.print(graph[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void btf(char[][] graph, int i, int j, char newColor, int m, int n) {
        char oldColor = graph[i][j];
        if (oldColor == newColor) {
            return;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row < 0 || row >= n || col < 0 || col >= m || graph[row][col] != oldColor) {
                continue;
            } else {
                graph[row][col] = newColor;
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
}
