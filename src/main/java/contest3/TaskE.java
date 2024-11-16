package contest3;

import java.util.*;

public class TaskE {
    public static void main(String[] args) {
        PortalPathBuilder pathBuilder = new PortalPathBuilder(new Scanner(System.in));
        System.out.println(pathBuilder.getMinNetworkCost());
    }
}

class PortalPathBuilder {

    private final Map<Integer, List<Integer>> graph;
    private final Map<Integer, Integer> prices;

    public PortalPathBuilder(Scanner scanner) {
        this.graph = new HashMap<>();
        this.prices = new HashMap<>();

        int nodesNumber = scanner.nextInt();
        int edgesNumber = scanner.nextInt();

        for (int i = 1; i <= nodesNumber; i++) {
            graph.put(i, new ArrayList<>());
            prices.put(i, scanner.nextInt());
        }

        for (int i = 1; i <= edgesNumber; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }

    private List<Integer> calculateMinimumOverComponents() {
        List<Integer> componentsMins = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (int planet : graph.keySet()) {
            if (!visited.contains(planet)) {
                int minCost = dfsAndMinPortalCost(planet, visited);
                componentsMins.add(minCost);
            }
        }

        return componentsMins;
    }

    private int dfsAndMinPortalCost(int startingPlanet, Set<Integer> visited) {
        visited.add(startingPlanet);
        int minCost = prices.get(startingPlanet);

        for (int neighbor : graph.get(startingPlanet)) {
            if (!visited.contains(neighbor)) {
                minCost = Math.min(minCost, dfsAndMinPortalCost(neighbor, visited));
            }
        }
        return minCost;
    }

    public int getMinNetworkCost() {
        List<Integer> componentsMins = calculateMinimumOverComponents();
        int sumOfMins = componentsMins.stream().mapToInt(Integer::intValue).sum();
        int temp = componentsMins.stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
        return sumOfMins + temp * (componentsMins.size() - 2);
    }
}
