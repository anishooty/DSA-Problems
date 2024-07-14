import java.util.*;

class DiameterOfTree{

	static Vector<Vector<Integer>> tree;
    static int[] dist;
    static int maxD, maxNode;

    private static void dfs(int node, int parent, int depth) {
        dist[node] = depth;
        if (dist[node] > maxD) {
            maxD = dist[node];
            maxNode = node;
        }
        for (int child : tree.get(node)) {
            if (child != parent) {
                dfs(child, node, depth + 1);
            }
        }
    }

    private static int diameterOfTree(int n, Vector<Vector<Integer>> edges) {
        tree = new Vector<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new Vector<>());
        }
        for (Vector<Integer> edge : edges) {
            tree.get(edge.get(0)).add(edge.get(1));
            tree.get(edge.get(1)).add(edge.get(0));
        }
        dist = new int[n + 1];
        maxD = -1;
        dfs(1, 0, 0);
        maxD = -1;
        dfs(maxNode, 0, 0);
        return maxD;
    }
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int t= sc.nextInt();
		for(int test=0;test<t;test++)
		{
			int n=sc.nextInt();
			int m=sc.nextInt();
			Vector<Vector<Integer> > edges = new Vector<Vector<Integer> >();
			for(int i=0;i<m;i++)
			{
				int u=sc.nextInt();
				int v=sc.nextInt();
				Vector<Integer> edge = new Vector<Integer>();
				edge.add(u);
				edge.add(v);
				edges.add(edge);
			}
			System.out.println(diameterOfTree(n,edges));
		}
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