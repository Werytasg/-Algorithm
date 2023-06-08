import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Path {
    public static int getPathNumber(char[][] course, int height, int width) {
        int[] solutionsInLine = new int[height];
        Set<Character> endpoints = new HashSet<>();
        Map<Character, Integer> squaresVisitedCount = new HashMap<>();

        for (int i = 0; i < height; i++) {
            if(i==0 || i==height-1){
                char[] row = course[i];
                char endpointSquare = row[width - 1];
                solutionsInLine[i] = 1;
                endpoints.add(endpointSquare);
                squaresVisitedCount.put(endpointSquare, squaresVisitedCount.getOrDefault(endpointSquare, 0) + 1);}
        }

        for (int j = width - 2; j >= 0; j--) {
            Map<Character, Integer> currentColumnPathsCount = new HashMap<>();

            for (int i = 0; i < height; i++) {
                char square = course[i][j];
                if (!endpoints.contains(square) && solutionsInLine[i] == 0) {
                    continue;
                }
                int squareSolutionCount = squaresVisitedCount.getOrDefault(square, 0);

                if (square != course[i][j + 1]) {
                    squareSolutionCount += solutionsInLine[i];
                }

                solutionsInLine[i] = squareSolutionCount;
                currentColumnPathsCount.put(square, currentColumnPathsCount.getOrDefault(square, 0) + squareSolutionCount);
            }

            for (char squareKey : currentColumnPathsCount.keySet()) {
                endpoints.add(squareKey);
                squaresVisitedCount.put(squareKey, squaresVisitedCount.getOrDefault(squareKey, 0) + currentColumnPathsCount.get(squareKey));
            }
        }

        int sum = 0;
        for (int count : solutionsInLine) {
            sum += count;
        }
        return sum;
    }
}