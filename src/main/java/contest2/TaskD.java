package contest2;

import java.util.Scanner;

public class TaskD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlatformJumpsProcessor processor = new PlatformJumpsProcessor(scanner);
        processor.calculateMinimumEnergy();
    }
}

class PlatformJumpsProcessor {

    int[] platformHeights;

    public PlatformJumpsProcessor(Scanner scanner) {
        int numberOfPlatforms = scanner.nextInt();
        this.platformHeights = new int[numberOfPlatforms];

        for (int i = 0; i < numberOfPlatforms; i++) {
            platformHeights[i] = scanner.nextInt();
        }
    }

    public void calculateMinimumEnergy() {
        long[] energies1 = new long[platformHeights.length];
        long[] energies2 = new long[platformHeights.length];

        energies1[0] = 0;
        energies2[0] = 0;

        for (int i = 1; i < platformHeights.length; i++) {
            calculateVersion1Energy(energies1, i);
            calculateVersion2Energy(energies2, i);
        }

        System.out.println(energies1[platformHeights.length - 1] + " " + energies2[platformHeights.length - 1]);
    }

    private void calculateVersion1Energy(long[] energies, int i) {
        long jumpCost = calculateJumpCost(i);
        energies[i] = energies[i - 1] + jumpCost;

        if (i > 1) {
            long skipCost = calculateSkipCost(i);
            energies[i] = Math.min(energies[i], energies[i - 2] + skipCost);
        }
    }

    private void calculateVersion2Energy(long[] energies, int i) {
        long jumpCost = calculateJumpCostSquared(i);
        energies[i] = energies[i - 1] + jumpCost;

        if (i > 1) {
            long skipCost = calculateSkipCostSquared(i);
            energies[i] = Math.min(energies[i], energies[i - 2] + skipCost);
        }
    }

    private long calculateJumpCost(int i) {
        return Math.abs(platformHeights[i] - platformHeights[i - 1]);
    }

    private long calculateSkipCost(int i) {
        return 3L * Math.abs(platformHeights[i] - platformHeights[i - 2]);
    }

    private long calculateJumpCostSquared(int i) {
        long difference = platformHeights[i] - platformHeights[i - 1];
        return difference * difference;
    }

    private long calculateSkipCostSquared(int i) {
        long difference = platformHeights[i] - platformHeights[i - 2];
        return 3L * difference * difference;
    }
}
