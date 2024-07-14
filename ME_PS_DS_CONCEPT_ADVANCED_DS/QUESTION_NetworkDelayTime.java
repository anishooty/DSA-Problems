import java.util.*;

class NetworkDelayTime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> b = new ArrayList<>();

            b.add(sc.nextInt());
            b.add(sc.nextInt());
            b.add(sc.nextInt());
            edges.add(b);
        }

        int k = sc.nextInt();

        int ans = networkDelayTime(n, edges, k);

        System.out.println(ans);
    }

    public static int networkDelayTime(int N, ArrayList<ArrayList<Integer>> edges, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);
            graph.computeIfAbsent(u, x -> new ArrayList<>()).add(new int[]{v, w});
        }

        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{K, 0});
        distances[K] = 0;

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];
            int dist = node[1];
            if (dist > distances[u]) continue;

            if (graph.containsKey(u)) {
                for (int[] neighbor : graph.get(u)) {
                    int v = neighbor[0];
                    int w = neighbor[1];
                    if (dist + w < distances[v]) {
                        distances[v] = dist + w;
                        pq.offer(new int[]{v, dist + w});
                    }
                }
            }
        }

        int maxDelay = 0;
        for (int i = 1; i <= N; i++) {
            if (distances[i] == Integer.MAX_VALUE) return -1;
            maxDelay = Math.max(maxDelay, distances[i]);
        }

        return maxDelay;
    }
}
