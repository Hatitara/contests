package contest3;

import java.util.*;

public class TaskI {
    public static void main(String[] args) {
        PathBuilder pathBuilder = new PathBuilder(new Scanner(System.in));
        System.out.println(pathBuilder.getMinReversals());
    }
}

record Edge(int target, int weight) {
}

class PathBuilder {

    private final Map<Integer, List<Edge>> graph;

    public PathBuilder(Scanner scanner) {
        this.graph = new HashMap<>();

        int size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            graph.put(i + 1, new ArrayList<>());
        }

        int amount = scanner.nextInt();
        for (int i = 0; i < amount; i++) {
            int source = scanner.nextInt();
            int target = scanner.nextInt();

            graph.get(source).add(new Edge(target, 0));
            graph.get(target).add(new Edge(source, 1));
        }
    }

    private int[] computeDistances() {
        int[] distances = new int[graph.size() + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        bfs(distances);
        return distances;
    }

    private void bfs(int[] distances) {
        Deque<Integer> queue = new ArrayDeque<>();
        distances[1] = 0;
        queue.add(1);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == graph.size()) break;

            for (Edge edge : graph.get(current)) {
                if (edge.weight() + distances[current] < distances[edge.target()]) {
                    distances[edge.target()] = edge.weight() + distances[current];
                    if (edge.weight() == 0) {
                        queue.addFirst(edge.target());
                    } else {
                        queue.addLast(edge.target());
                    }
                }
            }
        }
    }

    public int getMinReversals() {
        int minReversals = computeDistances()[graph.size()];
        return minReversals == Integer.MAX_VALUE ? -1 : minReversals;
    }
}
