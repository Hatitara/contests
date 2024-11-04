package contest2;

import java.util.*;

public class TaskH {
    public static void main(String[] args) {
        MoleculeDetection.get();
    }
}

class MoleculeDetection {
    private static boolean boundsCheck(int value, int leftBound, int rightBound) {
        return value >= leftBound && value <= rightBound;
    }

    public static void get() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int lowerBound = scanner.nextInt();
        int upperBound = scanner.nextInt();
        Integer[][] indexedWeights = new Integer[size][2];

        for (int i = 0; i < size; i++) {
            indexedWeights[i][0] = scanner.nextInt();
        }

        if (size == 1) {
            if (indexedWeights[0][0] >= lowerBound && indexedWeights[0][0] <= upperBound) {
                System.out.println(1);
                System.out.println(1);
            } else {
                System.out.println(0);
            }
            return;
        }

        List<Integer> greatestMolecules = new ArrayList<>();
        List<Integer> currentMolecules = new ArrayList<>();

        int leftPointer = 0, rightPointer = 0;
        int sum = 0, greatestSum = 0;

        while (rightPointer < size) {
            sum += indexedWeights[rightPointer][0];
            currentMolecules.add(rightPointer);

            while (sum > upperBound && leftPointer <= rightPointer) {
                sum -= indexedWeights[leftPointer][0];
                currentMolecules.removeFirst();
                leftPointer++;
            }

            if ((currentMolecules.size() > greatestMolecules.size()) ||
                    (currentMolecules.size() == greatestMolecules.size() && sum > greatestSum)) {
                greatestMolecules = new ArrayList<>(currentMolecules);
                greatestSum = sum;
            }
            rightPointer++;
        }

        if (!greatestMolecules.isEmpty()) {
            System.out.println(greatestMolecules.size());
            StringBuilder result = new StringBuilder();
            for (int i = greatestMolecules.size() - 1; i >= 0; i--) {
                result.append(greatestMolecules.get(i));
                if (i != 0) result.append(" ");
            }
            System.out.println(result.toString());
            return;
        }

        System.out.println(0);
    }
}
