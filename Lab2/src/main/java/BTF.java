import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.Queue;

public class BTF {
    public static void main(String[] args) {
        try {
            File inputFile = new File("C:\\Users\\Lubomir Luchka\\Desktop\\Lab2\\input");
            Scanner scanner = new Scanner(inputFile);

            // Зчитування m та n
            String[] mnLine = scanner.nextLine().split(",");
            int m = Integer.parseInt(mnLine[0].trim());
            int n = Integer.parseInt(mnLine[1].trim());

            // Зчитування i та j
            String[] ijLine = scanner.nextLine().split(",");
            int i = Integer.parseInt(ijLine[0].trim());
            int j = Integer.parseInt(ijLine[1].trim());

            // Зчитування нового кольору
            String newColor = scanner.nextLine().trim();

            // Зчитування матриці
            String[][] matrix = new String[n][m];
            for (int row = 0; row < n; row++) {
                String[] line = scanner.nextLine().trim().split("\\W+");
                for (int col = 0; col < m; col++) {
                    matrix[row][col] = line[col];
                }
            }
            scanner.close();

            btf(matrix, i, j, newColor, m, n);

            // Запис результату у вихідний файл
            FileWriter writer = new FileWriter("C:\\Users\\Lubomir Luchka\\Desktop\\Lab2\\output");
            for (String[] row : matrix) {
                StringBuilder sb = new StringBuilder();
                for (String cell : row) {
                    sb.append(cell).append(" ");
                }
                sb.deleteCharAt(sb.length() - 1); // Видалення останнього пробілу
                writer.write(sb.toString() + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void btf(String[][] matrix, int i, int j, String newColor, int m, int n) {
        // Опрацювання матриці - заміна кольору
        String targetColor = matrix[i][j];
        if (targetColor.equals(newColor)) {
            return;
        }
        btfHelper(matrix, i, j, targetColor, newColor, m, n);
    }

    private static void btfHelper(String[][] matrix, int row, int col, String targetColor, String newColor, int m, int n) {
        if (row < 0 || row >= n || col < 0 || col >= m || !matrix[row][col].equals(targetColor)) {
            return;
        }

        matrix[row][col] = newColor;

        btfHelper(matrix, row - 1, col, targetColor, newColor, m, n); // Верхній сусід
        btfHelper(matrix, row + 1, col, targetColor, newColor, m, n); // Нижній сусід
        btfHelper(matrix, row, col - 1, targetColor, newColor, m, n); // Лівий сусід
        btfHelper(matrix, row, col + 1, targetColor, newColor, m, n); // Правий сусід
    }
}


