import java.util.*;
class PossibleBipartition {
	public static String possibleBipartition(int n, Vector<Vector<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (Vector<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            graph.get(u - 1).add(v - 1);
            graph.get(v - 1).add(u - 1);
        }

        int[] colors = new int[n];
        Arrays.fill(colors, -1);

        for (int i = 0; i < n; i++) {
            if (colors[i] == -1 && !dfs(graph, colors, i, 0)) {
                return "Not Possible";
            }
        }

        return "Possible";
    }

    private static boolean dfs(List<List<Integer>> graph, int[] colors, int node, int color) {
        colors[node] = color;

        for (int neighbor : graph.get(node)) {
            if (colors[neighbor] == -1) {
                if (!dfs(graph, colors, neighbor, 1 - color)) {
                    return false;
                }
            } else if (colors[neighbor] == color) {
                return false;
            }
        }

        return true;
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tests = 0; tests < t; tests++) {
			int n = sc.nextInt();
			int m = sc.nextInt();

			Vector<Vector<Integer> > edges = new Vector<Vector<Integer> >();
			for (int i = 0; i < m; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				Vector<Integer> edge = new Vector<Integer>();
				edge.add(u);
				edge.add(v);
				edges.add(edge);
			}
			System.out.println(possibleBipartition(n, edges));
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