package contest2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskI {
    public static void main(String[] args) {
        PhotographerMachineProcessor processor = new PhotographerMachineProcessor(
                new Scanner(System.in)
        );
        System.out.println(processor.totalEqualRectangles);
    }

    public static void printMatrix(int[][] matrix) {
        int maxAbsValue = 0;
        for (int[] row : matrix) {
            for (int val : row) {
                maxAbsValue = Math.max(maxAbsValue, Math.abs(val));
            }
        }
        int width = String.valueOf(maxAbsValue).length() + 2;
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%" + width + "d", val);
            }
            System.out.println();
        }
    }
}

class PhotographerMachineProcessor {

    private final int height;
    private final int width;
    private final int[][] matrix;
    public final int totalEqualRectangles;

    public PhotographerMachineProcessor(Scanner scanner) {
        this.height = scanner.nextInt();
        this.width = scanner.nextInt();
        this.matrix = new int[height][width];
        initializeMatrix(scanner);
        int[][] prefixSum = calculatePrefixSum();
        this.totalEqualRectangles = countEqualRectangles(prefixSum);
    }

    private void initializeMatrix(Scanner scanner) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = (scanner.nextInt() == 1) ? 1 : -1;
            }
        }
    }

    private int[][] calculatePrefixSum() {
        int[][] prefixSum = new int[height + 1][width + 1];
        for (int x1 = 1; x1 <= height; x1++) {
            for (int y1 = 1; y1 <= width; y1++) {
                prefixSum[x1][y1] = matrix[x1 - 1][y1 - 1]
                        + prefixSum[x1 - 1][y1]
                        + prefixSum[x1][y1 - 1]
                        - prefixSum[x1 - 1][y1 - 1];
            }
        }
        return prefixSum;
    }

    private int countEqualRectangles(int[][] prefixSum) {
        int rectangleCount = 0;

        for (int x1 = 1; x1 <= height; x1++) {
            for (int x2 = x1; x2 <= height; x2++) {
                Map<Integer, Integer> cumulativeSumMap = new HashMap<>();

                for (int y2 = 1; y2 <= width; y2++) {
                    int submatrixSum = prefixSum[x2][y2]
                            - (x1 > 1 ? prefixSum[x1 - 1][y2] : 0);

                    if (submatrixSum == 0) {
                        rectangleCount++;
                    }

                    rectangleCount += cumulativeSumMap.getOrDefault(submatrixSum, 0);
                    cumulativeSumMap.put(submatrixSum, cumulativeSumMap.getOrDefault(submatrixSum, 0) + 1);
                }
            }
        }
        return rectangleCount;
    }
}
