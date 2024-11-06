package contest2;

import java.util.*;

public class TaskD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlatformJumpsProcessor jumpsProcessor = new PlatformJumpsProcessor(scanner);
        jumpsProcessor.findMinimumEnergy();
    }
}

class PlatformJumpsProcessor {
    private final long platformCount;
    private final long[] platformHeights;
    private final long[] minEnergyAbsMode;
    private final long[] minEnergySquareMode;

    public PlatformJumpsProcessor(Scanner scanner) {
        this.platformCount = scanner.nextLong();
        this.platformHeights = new long[(int) platformCount];
        for (int i = 0; i < platformCount; i++) {
            platformHeights[i] = scanner.nextLong();
        }
        this.minEnergyAbsMode = new long[(int) platformCount];
        this.minEnergySquareMode = new long[(int) platformCount];
        Arrays.fill(minEnergyAbsMode, Long.MAX_VALUE);
        Arrays.fill(minEnergySquareMode, Long.MAX_VALUE);
    }

    public void findMinimumEnergy() {
        minEnergyAbsMode[0] = 0;
        minEnergySquareMode[0] = 0;

        PriorityQueue<PlatformNode> priorityQueue = new PriorityQueue<>();

        priorityQueue.offer(new PlatformNode(0, 0, true));
        priorityQueue.offer(new PlatformNode(0, 0, false));

        while (!priorityQueue.isEmpty()) {
            PlatformNode currentNode = priorityQueue.poll();
            long currentPlatform = currentNode.platformIndex;
            long currentEnergy = currentNode.energyCost;
            boolean isAbsMode = currentNode.isAbsMode;

            long[] minEnergy = isAbsMode ? minEnergyAbsMode : minEnergySquareMode;

            if (currentEnergy > minEnergy[(int) currentPlatform]) {
                continue;
            }

            if (currentPlatform + 1 < platformCount) {
                long jumpEnergyCost = calculateJumpEnergy(currentPlatform, currentPlatform + 1, isAbsMode);

                if (currentEnergy + jumpEnergyCost < minEnergy[(int) (currentPlatform + 1)]) {
                    minEnergy[(int) (currentPlatform + 1)] = currentEnergy + jumpEnergyCost;
                    priorityQueue.offer(new PlatformNode(currentPlatform + 1, minEnergy[(int) (currentPlatform + 1)], isAbsMode));
                }
            }

            if (currentPlatform + 2 < platformCount) {
                long skipEnergyCost = calculateSkipEnergy(currentPlatform, currentPlatform + 2, isAbsMode);

                if (currentEnergy + skipEnergyCost < minEnergy[(int) (currentPlatform + 2)]) {
                    minEnergy[(int) (currentPlatform + 2)] = currentEnergy + skipEnergyCost;
                    priorityQueue.offer(new PlatformNode(currentPlatform + 2, minEnergy[(int) (currentPlatform + 2)], isAbsMode));
                }
            }

            if (currentPlatform - 1 >= 0) {
                long jumpEnergyCost = calculateJumpEnergy(currentPlatform, currentPlatform - 1, isAbsMode);

                if (currentEnergy + jumpEnergyCost < minEnergy[(int) (currentPlatform - 1)]) {
                    minEnergy[(int) (currentPlatform - 1)] = currentEnergy + jumpEnergyCost;
                    priorityQueue.offer(new PlatformNode(currentPlatform - 1, minEnergy[(int) (currentPlatform - 1)], isAbsMode));
                }
            }

            if (currentPlatform - 2 >= 0) {
                long skipEnergyCost = calculateSkipEnergy(currentPlatform, currentPlatform - 2, isAbsMode);

                if (currentEnergy + skipEnergyCost < minEnergy[(int) (currentPlatform - 2)]) {
                    minEnergy[(int) (currentPlatform - 2)] = currentEnergy + skipEnergyCost;
                    priorityQueue.offer(new PlatformNode(currentPlatform - 2, minEnergy[(int) (currentPlatform - 2)], isAbsMode));
                }
            }
        }

        System.out.println(minEnergyAbsMode[(int) (platformCount - 1)] + " " + minEnergySquareMode[(int) (platformCount - 1)]);
    }

    private long calculateJumpEnergy(long from, long to, boolean isAbsMode) {
        long heightDiff = platformHeights[(int) to] - platformHeights[(int) from];
        return isAbsMode ? Math.abs(heightDiff) : heightDiff * heightDiff;
    }

    private long calculateSkipEnergy(long from, long to, boolean isAbsMode) {
        long heightDiff = platformHeights[(int) to] - platformHeights[(int) from];
        return isAbsMode ? 3 * Math.abs(heightDiff) : 3 * heightDiff * heightDiff;
    }
}

class PlatformNode implements Comparable<PlatformNode> {
    long platformIndex;
    long energyCost;
    boolean isAbsMode;

    public PlatformNode(long platformIndex, long energyCost, boolean isAbsMode) {
        this.platformIndex = platformIndex;
        this.energyCost = energyCost;
        this.isAbsMode = isAbsMode;
    }

    @Override
    public int compareTo(PlatformNode other) {
        return Long.compare(this.energyCost, other.energyCost);
    }
}
