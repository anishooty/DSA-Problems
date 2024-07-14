import java.util.*;

public class CobbledStreets {
    public static int cobbledStreets(int n, ArrayList<ArrayList<Integer>> street, int p) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (ArrayList<Integer> s : street) {
            int u = s.get(0);
            int v = s.get(1);
            int cost = s.get(2);
            graph.get(u).add(new int[]{v, cost});
            graph.get(v).add(new int[]{u, cost});
        }

        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{1, 0});
        int minCost = 0;
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];
            int cost = node[1];
            if (visited[u]) continue;
            visited[u] = true;
            minCost += cost;
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0];
                int edgeCost = neighbor[1];
                if (!visited[v]) {
                    pq.offer(new int[]{v, edgeCost});
                }
            }
        }

        return minCost * p;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int p = sc.nextInt();
            int n = sc.nextInt();
            int m = sc.nextInt();
            ArrayList<ArrayList<Integer>> street = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                ArrayList<Integer> s = new ArrayList<>();
                s.add(sc.nextInt());
                s.add(sc.nextInt());
                s.add(sc.nextInt());
                street.add(s);
            }
            int ans = cobbledStreets(n, street, p);
            System.out.println(ans);
        }
        sc.close();
    }
}


/* 
Crio Methodology

Milestone 1: Understand the problem clearly
1. Ask questions & clarify the problem statement clearly.
2. Take an example or two to confirm your understanding of the input/output

Milestone 2: Finalize approach & execution plan
1. Understand what type of problem you are solving and see if you can recollect solving similar problems in the past
      a. Obvious logic (this would only test ability to convert logic to code)
      b. Figuring out logic
      c. Knowledge of specific algorithm, data structure or pattern
      d. Knowledge of specific domain or concepts
      e. Mapping real world into abstract concepts/data structures
2. Brainstorm multiple ways to solve the problem and pick one based on the TC/SC requirements
3. Get to a point where you can explain your approach to a 10 year old

Milestone 3 : Come up with an Instruction Manual for a 10 year old
1. Take a stab at the high-level logic & write it down like a detailed Instruction Manual for a 10 Year old where each line of the manual can be expanded into a logical line(s) of code.
2. Try to offload logic out of the main section as much as possible by delegating to functions.

Milestone 4: Code by expanding your 10 Year Olds "Instruction Manual
1. Run your code snippets at every logical step to test correctness (Helps avoid debugging larger pieces of code later)
2. Make sure you name the variables, functions clearly.
3. Use libraries as much as possible

Milestone 5: Prove that your code works using custom test cases
1. Make sure you check boundary conditions and other test cases you noted in Milestone 1
      a. If compiler is not available, dry run your code on a whiteboard or paper
2. Suggest optimizations if applicable during interviews
*/