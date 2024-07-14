import java.util.*;

public class CheapestFlights {

    public static int cheapestFlights(int n, ArrayList<ArrayList<Integer>> flight, int source, int des, int k)  {
        int[][] dp = new int[n][k + 2];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[source][0] = 0;
        for (int i = 1; i <= k + 1; i++) {
            for (ArrayList<Integer> f : flight) {
                int from = f.get(0) - 1;
                int to = f.get(1) - 1;
                int cost = f.get(2);
                if (dp[from][i - 1] != Integer.MAX_VALUE) {
                    dp[to][i] = Math.min(dp[to][i], dp[from][i - 1] + cost);
                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i <= k + 1; i++) {
            minCost = Math.min(minCost, dp[des][i]);
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<ArrayList<Integer>> flight = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < e; i++) {
            flight.add(new ArrayList<Integer>());
            flight.get(i).add(sc.nextInt());
            flight.get(i).add(sc.nextInt());
            flight.get(i).add(sc.nextInt());
        }
        int source = sc.nextInt() - 1;
        int des = sc.nextInt() - 1;
        int k = sc.nextInt();
        int ans = cheapestFlights(n, flight, source, des, k);
        System.out.print(ans);
        sc.close();
    }
}
