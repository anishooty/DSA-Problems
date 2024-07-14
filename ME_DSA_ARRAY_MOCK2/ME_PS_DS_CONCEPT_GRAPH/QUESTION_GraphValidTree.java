import java.util.*;

class GraphValidTree {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            edges[i][0] = a;
            edges[i][1] = b;
        }
        System.out.println(graphValidTree(n, edges));

        sc.close();
    }

    static int graphValidTree(int n, int[][] edges) {
        // Create an adjacency list to represent the graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        if (hasCycle(adjList, visited, 0, -1)) {
            return 0; 
        }

        for (boolean visit : visited) {
            if (!visit) {
                return 0; 
            }
        }

        return 1; 
    }

    static boolean hasCycle(List<List<Integer>> adjList, boolean[] visited, int node, int parent) {
        visited[node] = true;
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                if (hasCycle(adjList, visited, neighbor, node)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }
}
