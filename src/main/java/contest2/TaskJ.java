package contest2;

import java.util.Arrays;
import java.util.Scanner;

public class TaskJ {
    public static void main(String[] args) {
        SportCowProcessor processor = new SportCowProcessor(new Scanner(System.in));
        System.out.println(processor.getMaxResult());
    }
}

class SportCowProcessor {

    private final int MAX_FATIGUE;
    private final int[] distances;

    public SportCowProcessor(Scanner scanner) {
        int totalMinutes = scanner.nextInt();
        MAX_FATIGUE = scanner.nextInt();
        this.distances = new int[totalMinutes];
        for (int minute = 0; minute < totalMinutes; minute++) {
            distances[minute] = scanner.nextInt();
        }
    }

    private int[][] buildDP() {
        int[][] dp = new int[distances.length + 1][MAX_FATIGUE + 1];

        for (int minute = 1; minute <= distances.length; minute++) {
            for (int fatigueLevel = 0; fatigueLevel <= Math.min(minute, MAX_FATIGUE); fatigueLevel++) {
                dp[minute][0] = Math.max(dp[minute][0], dp[minute - fatigueLevel][fatigueLevel]);
            }

//            System.out.println(Arrays.toString(dp[minute]));

            dp[minute][0] = Math.max(dp[minute][0], dp[minute - 1][0]);

//            System.out.println(Arrays.toString(dp[minute]));

            for (int fatigueLevel = 1; fatigueLevel <= MAX_FATIGUE; fatigueLevel++) {
                dp[minute][fatigueLevel] = dp[minute - 1][fatigueLevel - 1] + distances[minute - 1];
            }
        }

//        printMatrix(dp);

        return dp;
    }

    public int getMaxResult() {
        return buildDP()[distances.length][0];
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
