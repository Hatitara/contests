package contest2;

import java.util.Scanner;

public class TaskF {
    public static void main(String[] args) {
        MaxPathSum maxPathSum = new MaxPathSum(new Scanner(System.in));
        System.out.println(maxPathSum.tryIt());
    }
}

class MaxPathSum {

    private final int matrixSize;
    private final int passLength;
    private final int[][] matrix;

    public MaxPathSum(Scanner scanner) {
        this.matrixSize = scanner.nextInt();
        this.passLength = scanner.nextInt();
        this.matrix = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public int tryIt() {
        int[][][] dp = new int[passLength + 1][matrixSize][matrixSize];
        dp[0][0][0] = matrix[0][0];

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int maxPath = Integer.MIN_VALUE;

        for (int currentStep = 1; currentStep < passLength + 1; currentStep++) {
            int iterationBound = Math.min(currentStep, matrixSize);
            for (int i = 0; i < iterationBound; i++) {
                for (int j = 0; j < iterationBound; j++) {
                    if (i + j >= currentStep) {
                        continue;
                    }
                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (inBounds(ni, nj, matrixSize)) {
                            dp[currentStep][i][j] = Math.max(dp[currentStep - 1][ni][nj] + matrix[i][j], dp[currentStep][i][j]);
                            maxPath = Math.max(maxPath, dp[currentStep][i][j]);
                        }
                    }

                }
            }
//            System.out.println("Step #" + currentStep + ":");
//            printMatrix(dp[currentStep - 1]);
        }

        return maxPath;
    }

    private static boolean inBounds(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] integers : matrix) {
            for (int anInt : integers) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
