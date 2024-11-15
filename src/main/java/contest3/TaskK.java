package contest3;

import java.util.*;

public class TaskK {
    public static void main(String[] args) {
        FloodRefundCalculator calculator = new FloodRefundCalculator(
                new Scanner(System.in)
        );
        System.out.println(calculator.calculateRefund());
    }
}

class FloodRefundCalculator {

    private final boolean[][] floodedMatrix;
    private final int floodedAmount;
    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public FloodRefundCalculator(Scanner scanner) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        this.floodedMatrix = new boolean[n][m];
        this.floodedAmount = scanner.nextInt();

        for (int i = 0; i < floodedAmount; i++) {
            floodedMatrix[scanner.nextInt() - 1][scanner.nextInt() - 1] = true;
        }
    }

    private static String cellForSet(int x, int y) {
        return x + "," + y;
    }

    private int exploreAround(int x, int y, Set<String> visited) {
        visited.add(cellForSet(x, y));
        int area = 1;

        for (int[] direction : DIRECTIONS) {

            int newX = x + direction[0];
            int newY = y + direction[1];

            if (isInBounds(newX, newY) && floodedMatrix[newX][newY] && !visited.contains(cellForSet(newX, newY))) {
                area += exploreAround(newX, newY, visited);
            }
        }

        return area;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < floodedMatrix.length && y >= 0 && y < floodedMatrix[0].length;
    }

    public int calculateRefund() {
        int refund = 0;
        Set<String> visited = new HashSet<>();

        for (int i = 0; i < floodedMatrix.length; i++) {
            for (int j = 0; j < floodedMatrix[0].length; j++) {
                if (floodedMatrix[i][j] && !visited.contains(cellForSet(i, j))) {
                    refund = Math.max(exploreAround(i, j, visited), refund);
                }
                if (visited.size() >= floodedAmount) {
                    return refund;
                }
            }
        }

        return refund;
    }
}
