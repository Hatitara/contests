package contest2;

import java.util.*;

public class TaskE {
    public static void main(String[] args) {
        TeamRatingCalculator calculator = new TeamRatingCalculator(new Scanner(System.in));
        calculator.calculateAndDisplayRanks();
    }
}

class TeamRatingCalculator {
    private final int amanRating;
    private final int[] otherParticipantRatings;
    private final int allParticipants;

    public TeamRatingCalculator(Scanner scanner) {
        int numberOfPairs = scanner.nextInt();
        this.allParticipants = 2 * numberOfPairs;
        this.amanRating = scanner.nextInt();
        this.otherParticipantRatings = new int[allParticipants - 1];

        for (int i = 0; i < allParticipants - 1; i++) {
            otherParticipantRatings[i] = scanner.nextInt();
        }
        Arrays.sort(otherParticipantRatings);
    }

    public void calculateAndDisplayRanks() {
        int maxRank = calculateMaxRank();
        int minRank = calculateMinRank();
        System.out.printf("%d %d%n", maxRank, minRank);
    }

    private int calculateMaxRank() {
        int amansPair = amanRating + otherParticipantRatings[allParticipants - 2];
        TreeMap<Integer, Integer> possibleEnemies = new TreeMap<>();
        for (int i = 0; i < allParticipants - 2; i++) {
            possibleEnemies.put(otherParticipantRatings[i],
                    possibleEnemies.getOrDefault(otherParticipantRatings[i], 0) + 1);
        }

        int ranking = 1;
        while (!possibleEnemies.isEmpty()) {
            int definiteEnemy = possibleEnemies.lastKey();
            decreaseCount(possibleEnemies, definiteEnemy);

            int playerBound = amansPair - definiteEnemy;
            Integer enemyTeammate = possibleEnemies.floorKey(playerBound);

            if (enemyTeammate == null) {
                ranking++;
                decreaseCount(possibleEnemies, possibleEnemies.lastKey());
            } else {
                decreaseCount(possibleEnemies, enemyTeammate);
            }
        }
        return ranking;
    }

    private int calculateMinRank() {
        int amansPair = amanRating + otherParticipantRatings[0];
        TreeMap<Integer, Integer> possibleEnemies = new TreeMap<>();
        for (int i = 1; i < allParticipants - 1; i++) {
            possibleEnemies.put(otherParticipantRatings[i],
                    possibleEnemies.getOrDefault(otherParticipantRatings[i], 0) + 1);
        }

        int ranking = 1;
        while (!possibleEnemies.isEmpty()) {
            int definiteEnemy = possibleEnemies.firstKey();
            decreaseCount(possibleEnemies, definiteEnemy);

            int playerBound = amansPair - definiteEnemy;
            Integer enemyTeammate = possibleEnemies.higherKey(playerBound);

            if (enemyTeammate == null) {
                decreaseCount(possibleEnemies, possibleEnemies.firstKey());
            } else {
                ranking++;
                decreaseCount(possibleEnemies, enemyTeammate);
            }
        }
        return ranking;
    }

    private static void decreaseCount(TreeMap<Integer, Integer> map, int key) {
        if (map.get(key) == 1) {
            map.remove(key);
        } else {
            map.put(key, map.get(key) - 1);
        }
    }
}
