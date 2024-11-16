package contest3;

import java.util.*;
import java.util.function.Predicate;

public class TaskH {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = getGraphFromInput(new Scanner(System.in));
        System.out.println(new isMatchable().test(graph) ? "YES" : "NO");
    }

    private static Map<Integer, List<Integer>> getGraphFromInput(Scanner scanner) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        int numMembers = scanner.nextInt();
        for (int i = 0; i < numMembers; i++) {
            graph.put(i, new ArrayList<>());
        }

        int numInterestDeclarations = scanner.nextInt();
        for (int i = 0; i < numInterestDeclarations; i++) {
            int member = scanner.nextInt();
            int book = scanner.nextInt();
            graph.get(member).add(book);
        }

        return graph;
    }
}

class isMatchable implements Predicate<Map<Integer, List<Integer>>> {
    @Override
    public boolean test(Map<Integer, List<Integer>> graph) {
        int numMembers = graph.size();

        Map<Integer, Integer> bookAssignments = new HashMap<>();
        for (int member = 0; member < numMembers; member++) {
            Set<Integer> visitedBooks = new HashSet<>();
            if (!findMatchingByDFS(member, graph, bookAssignments, visitedBooks)) {
                return false;
            }
        }

        return true;
    }

    private boolean findMatchingByDFS(
            int member,
            Map<Integer, List<Integer>> graph,
            Map<Integer, Integer> bookAssignments,
            Set<Integer> visitedBooks
    ) {
        for (int likedBook : graph.get(member)) {
            if (!visitedBooks.contains(likedBook)) {
                visitedBooks.add(likedBook);

                if (!bookAssignments.containsKey(likedBook) || findMatchingByDFS(
                        bookAssignments.get(likedBook),
                        graph, bookAssignments,
                        visitedBooks
                )) {
                    bookAssignments.put(likedBook, member);
                    return true;
                }
            }
        }
        return false;
    }
}
