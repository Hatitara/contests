package contest3;

import java.util.*;

public class TaskF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            ParkingProcessor parking = new ParkingProcessor(scanner);
            System.out.println(parking.getMinTime());
        }
    }
}

record CoordsPair(int x, int y) {
}

class ParkingProcessor {

    private final char[][] parkingMatrix;
    private final List<CoordsPair> cars;
    private final List<CoordsPair> parkingSpots;

    public ParkingProcessor(Scanner scanner) {
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        this.parkingMatrix = new char[rows][cols];
        this.cars = new ArrayList<>();
        this.parkingSpots = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            parkingMatrix[i] = scanner.nextLine().toCharArray();
            for (int j = 0; j < cols; j++) {
                if (parkingMatrix[i][j] == 'C') cars.add(new CoordsPair(i, j));
                if (parkingMatrix[i][j] == 'P') parkingSpots.add(new CoordsPair(i, j));
            }
        }
    }

    public int getMinTime() {
        if (cars.isEmpty()) return 0;
        if (parkingSpots.size() < cars.size()) return -1;

        int[][] distances = calculateDistances();
        int left = 0, right = parkingMatrix.length * parkingMatrix[0].length, result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossibleToMatch(distances, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private int[][] calculateDistances() {
        int carCount = cars.size();
        int spotCount = parkingSpots.size();
        int[][] distances = new int[carCount][spotCount];

        for (int i = 0; i < carCount; i++) {
            int[][] dist = bfs(cars.get(i));
            for (int j = 0; j < spotCount; j++) {
                distances[i][j] = dist[parkingSpots.get(j).x()][parkingSpots.get(j).y()];
            }
        }

        return distances;
    }

    private int[][] bfs(CoordsPair start) {
        int rows = parkingMatrix.length;
        int cols = parkingMatrix[0].length;
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<CoordsPair> queue = new LinkedList<>();
        queue.offer(start);
        dist[start.x()][start.y()] = 0;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            CoordsPair current = queue.poll();

            for (int[] direction : directions) {

                int newX = current.x() + direction[0];
                int newY = current.y() + direction[1];

                if (
                        newX >= 0 && newX < rows &&
                        newY >= 0 && newY < cols &&
                        parkingMatrix[newX][newY] != 'X' &&
                        dist[newX][newY] == Integer.MAX_VALUE
                ) {
                    dist[newX][newY] = dist[current.x()][current.y()] + 1;
                    queue.offer(new CoordsPair(newX, newY));
                }
            }
        }

        return dist;
    }

    private boolean isPossibleToMatch(int[][] distances, int maxTime) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < cars.size(); i++) graph.put(i, new ArrayList<>());

        for (int i = 0; i < cars.size(); i++) {
            for (int j = 0; j < parkingSpots.size(); j++) {
                if (distances[i][j] <= maxTime) {
                    graph.get(i).add(j);
                }
            }
        }

        Map<Integer, Integer> matchCarToSpot = new HashMap<>();

        for (int car = 0; car < cars.size(); car++) {
            Set<Integer> visited = new HashSet<>();
            if (!dfs(car, graph, visited, matchCarToSpot)) return false;
        }

        return true;
    }

    private boolean dfs(
            int car, Map<Integer, List<Integer>> graph,
            Set<Integer> visited, Map<Integer, Integer> matchCarToSpot
    ) {
        for (int spot : graph.getOrDefault(car, Collections.emptyList())) {
            if (visited.contains(spot)) continue;
            visited.add(spot);

            if (!matchCarToSpot.containsKey(spot) || dfs(
                    matchCarToSpot.get(spot), graph,
                    visited, matchCarToSpot)
            ) {
                matchCarToSpot.put(spot, car);
                return true;
            }
        }
        return false;
    }
}
