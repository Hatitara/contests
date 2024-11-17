package contest3;

import java.text.DecimalFormat;
import java.util.*;

public class TaskC {
    public static void main(String[] args) {
        RoadToChicago road = new RoadToChicago(new Scanner(System.in));
        DecimalFormat df = new DecimalFormat("#.000000");
        System.out.println(df.format(road.getHighestChance()).replaceAll(",", ".") + " percent");
    }
}

class PathEdge {

    public int target;
    public double probability;

    public PathEdge(int target, double probability) {
        this.target = target;
        this.probability = probability / 100;
    }

}

class RoadToChicago {

    private final Map<Integer, List<PathEdge>> mapAsGraph;

    public RoadToChicago(Scanner scanner) {
        this.mapAsGraph = new HashMap<>();

        int numVertices = scanner.nextInt();
        for (int i = 1; i <= numVertices; i++) {
            mapAsGraph.put(i, new ArrayList<>());
        }

        int numEdges = scanner.nextInt();
        for (int i = 0; i < numEdges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int probability = scanner.nextInt();
            mapAsGraph.get(from).add(new PathEdge(to, probability));
            mapAsGraph.get(to).add(new PathEdge(from, probability));
        }
    }

    public double getHighestChance() {
        double[] maxProb = new double[mapAsGraph.size() + 1];
        maxProb[1] = 1.0;

        Comparator<PathEdge> comparator = (o1, o2) -> Double.compare(o2.probability, o1.probability);
        PriorityQueue<PathEdge> maxHeap = new PriorityQueue<>(comparator);
        maxHeap.add(new PathEdge(1, 100));

        Set<Integer> visited = new HashSet<>();

        while (!maxHeap.isEmpty()) {
            PathEdge current = maxHeap.poll();
            int currentNode = current.target;
            double currentProb = current.probability;

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);

                for (PathEdge neighbor : mapAsGraph.get(currentNode)) {
                    double newProb = currentProb * neighbor.probability;

                    if (newProb > maxProb[neighbor.target]) {
                        maxProb[neighbor.target] = newProb;
                        maxHeap.add(new PathEdge(neighbor.target, newProb * 100));
                    }
                }
            }
        }

        return maxProb[mapAsGraph.size()] * 100;
    }
}