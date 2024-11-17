package contest3;

import java.util.*;
import java.util.function.Predicate;

public class TaskB {
    public static void main(String[] args) {
        System.out.println(
                new IsPossibleToTimeTravel().test(
                        readSystemAsGraph(new Scanner(System.in))
                ) ? "possible" : "not possible"
        );
    }

    private static Map<Integer, List<Wormhole>> readSystemAsGraph(Scanner scanner) {
        Map<Integer, List<Wormhole>> graph = new HashMap<>();

        int verticesNumber = scanner.nextInt();
        for (int i = 0; i < verticesNumber; i++) {
            graph.put(i, new ArrayList<>());
        }

        int edgesNumber = scanner.nextInt();
        for (int i = 0; i < edgesNumber; i++) {
            graph.get(scanner.nextInt()).add(new Wormhole(scanner.nextInt(), scanner.nextInt()));
        }

        return graph;
    }
}

class Wormhole {

    public int destination;
    public int timeShift;

    public Wormhole(int destination, int timeShift) {
        this.destination = destination;
        this.timeShift = timeShift;
    }

}

class IsPossibleToTimeTravel implements Predicate<Map<Integer, List<Wormhole>>> {

    @Override
    public boolean test(Map<Integer, List<Wormhole>> graph) {
        int[] distances = prepareDistances(graph.size());

        for (int i = 0; i < graph.size() - 1; i++) {
            relaxEdges(graph, distances);
        }

        return hasNegativeWeightCycle(graph, distances);
    }

    private int[] prepareDistances(int numberOfVertices) {
        int[] distances = new int[numberOfVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;
        return distances;
    }

    private void relaxEdges(Map<Integer, List<Wormhole>> graph, int[] distances) {
        for (int start = 0; start < graph.size(); start++) {
            for (Wormhole wormhole : graph.get(start)) {
                int destination = wormhole.destination;
                int timeShift = wormhole.timeShift;

                if (distances[start] != Integer.MAX_VALUE && distances[start] + timeShift < distances[destination]) {
                    distances[destination] = distances[start] + timeShift;
                }
            }
        }
    }

    private boolean hasNegativeWeightCycle(Map<Integer, List<Wormhole>> graph, int[] distances) {
        for (int start = 0; start < graph.size(); start++) {
            for (Wormhole wormhole : graph.get(start)) {
                int destination = wormhole.destination;
                int timeShift = wormhole.timeShift;

                if (distances[start] != Integer.MAX_VALUE && distances[start] + timeShift < distances[destination]) {
                    return true;
                }
            }
        }
        return false;
    }
}