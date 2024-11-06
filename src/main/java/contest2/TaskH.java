package contest2;

import java.util.*;

public class TaskH {
    public static void main(String[] args) {
        MoleculeDetector moleculeDetector = new MoleculeDetector(new Scanner(System.in));
        moleculeDetector.getMaxAmount();
    }
}

class MoleculeDetector {

    private final long size;
    private final long lowerBound;
    private final long upperBound;
    private final Pair[] molecules;

    public MoleculeDetector(Scanner scanner) {
        this.size = scanner.nextLong();
        this.lowerBound = scanner.nextLong();
        this.upperBound = scanner.nextLong();

        this.molecules = new Pair[(int) size];
        for (int i = 0; i < size; i++) {
            molecules[i] = new Pair(scanner.nextLong(), i);
        }
        Arrays.sort(molecules, Comparator.comparingLong(p -> p.weight));
    }

    public void getMaxAmount() {
        long sum = 0;
        int i = 0, j = 0;

        while (j < size) {
            sum += molecules[j].weight;

            while (sum > upperBound) {
                sum -= molecules[i].weight;
                i++;
            }

            if (sum >= lowerBound && sum <= upperBound) {
                System.out.println(j - i + 1);
                for (int k = i; k <= j; k++) {
                    System.out.print(molecules[k].index + " ");
                }
                System.out.println();
                return;
            }

            j++;
        }
        System.out.println(0);
    }
}

class Pair {
    long weight;
    int index;

    Pair(long weight, int index) {
        this.weight = weight;
        this.index = index;
    }
}
