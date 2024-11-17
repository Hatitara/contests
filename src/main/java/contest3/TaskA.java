package contest3;

import java.util.*;

public class TaskA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MSTFinder mstFinder = new MSTFinder(scanner);
        System.out.println(mstFinder.findMST() + " " + mstFinder.findSecondMST());
    }
}

class UnionFind {
    private final int[] parent;
    private final int[] rank;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    public boolean union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);

        if (rootA != rootB) {
            if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
            return true;
        }
        return false;
    }
}

class SchoolsEdge {
    int schoolA;
    int schoolB;
    int cost;

    public SchoolsEdge(int schoolA, int schoolB, int cost) {
        this.schoolA = schoolA;
        this.schoolB = schoolB;
        this.cost = cost;
    }
}

class MSTFinder {
    private final int numSchools;
    private final List<SchoolsEdge> connections;

    public MSTFinder(Scanner scanner) {
        this.numSchools = scanner.nextInt();
        int numConnections = scanner.nextInt();

        this.connections = new ArrayList<>();
        for (int i = 0; i < numConnections; i++) {
            int schoolA = scanner.nextInt() - 1;
            int schoolB = scanner.nextInt() - 1;
            int cost = scanner.nextInt();
            this.connections.add(new SchoolsEdge(schoolA, schoolB, cost));
        }

        this.connections.sort(Comparator.comparingInt(edge -> edge.cost));
    }

    public int findMST() {
        UnionFind unionFind = new UnionFind(numSchools);
        int totalCost = 0;
        int edgesIncluded = 0;

        for (SchoolsEdge edge : connections) {
            if (unionFind.union(edge.schoolA, edge.schoolB)) {
                totalCost += edge.cost;
                edgesIncluded++;
            }

            if (edgesIncluded == numSchools - 1) {
                break;
            }
        }
        return totalCost;
    }

    public int findSecondMST() {
        int secondMSTCost = Integer.MAX_VALUE;

        List<SchoolsEdge> mstEdges = new ArrayList<>();
        UnionFind unionFind = new UnionFind(numSchools);

        for (SchoolsEdge edge : connections) {
            if (unionFind.union(edge.schoolA, edge.schoolB)) {
                mstEdges.add(edge);
            }
        }

        for (SchoolsEdge edgeToRemove : mstEdges) {
            unionFind = new UnionFind(numSchools);
            int totalCost = 0;
            int edgesIncluded = 0;

            for (SchoolsEdge edge : connections) {
                if (edge == edgeToRemove) continue;

                if (unionFind.union(edge.schoolA, edge.schoolB)) {
                    totalCost += edge.cost;
                    edgesIncluded++;
                }
            }

            if (edgesIncluded == numSchools - 1) {
                secondMSTCost = Math.min(secondMSTCost, totalCost);
            }
        }

        return secondMSTCost;
    }
}
