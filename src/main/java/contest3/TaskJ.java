package contest3;

import java.util.*;

public class TaskJ {
    public static void main(String[] args) {
        PasturesProcessor processor = new PasturesProcessor(new Scanner(System.in));
        System.out.println(processor.getBinaryAnswer());
    }
}

class PasturesProcessor {
    private final Map<Integer, List<int[]>> graph;
    private final int graphSize;

    public PasturesProcessor(Scanner scanner) {
        String[] firstLine = scanner.nextLine().split(" ");
        this.graphSize = Integer.parseInt(firstLine[0]);
        int edgesAmount = Integer.parseInt(firstLine[1]);

        this.graph = new HashMap<>();
        for (int i = 0; i < graphSize; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edgesAmount; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            char type = parts[0].charAt(0);
            int node1 = Integer.parseInt(parts[1]) - 1;
            int node2 = Integer.parseInt(parts[2]) - 1;

            int relation = (type == 'S') ? 0 : 1;
            graph.get(node1).add(new int[]{node2, relation});
            graph.get(node2).add(new int[]{node1, relation});
        }
    }

    private int countComponentsAndCheckPossibility() {
        int[] color = new int[graphSize];
        Arrays.fill(color, -1);
        int components = 0;

        for (int i = 0; i < graphSize; i++) {
            if (color[i] == -1) {
                components++;
                if (!bfsColorAndCheck(i, color)) {
                    return -1;
                }
            }
        }

        return components;
    }

    private boolean bfsColorAndCheck(int start, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int[] neighbor : graph.get(node)) {
                int neighborNode = neighbor[0];
                int edgeType = neighbor[1];

                int expectedColor = (edgeType == 0) ? color[node] : 1 - color[node];

                if (color[neighborNode] == -1) {
                    color[neighborNode] = expectedColor;
                    queue.add(neighborNode);
                } else if (color[neighborNode] != expectedColor) {
                    return false;
                }
            }
        }

        return true;
    }

    public String getBinaryAnswer() {
        int components = countComponentsAndCheckPossibility();
        return (components == -1) ? "0" : "1" + "0".repeat(Math.max(0, components));
    }
}
