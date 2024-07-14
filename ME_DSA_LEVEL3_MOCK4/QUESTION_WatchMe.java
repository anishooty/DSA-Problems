import java.util.*;

class WatchMe {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int numSeasons = sc.nextInt();
        int n = sc.nextInt();
        int[][] pre = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                pre[i][j] = sc.nextInt();
            }
        }

        int[] nums = watchMe(numSeasons, n, pre);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    static int[] watchMe(int numSeasons, int n, int[][] pre) {
        List<Integer>[] adjacencyList = new ArrayList[numSeasons];
        int[] inDegrees = new int[numSeasons];

        for (int i = 0; i < numSeasons; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int[] prerequisite : pre) {
            int ai = prerequisite[0];
            int bi = prerequisite[1];
            adjacencyList[bi].add(ai);
            inDegrees[ai]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numSeasons; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int currentSeason = queue.poll();
            result.add(currentSeason);
            for (int neighbor : adjacencyList[currentSeason]) {
                inDegrees[neighbor]--;
                if (inDegrees[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (result.size() != numSeasons) {
            return new int[0];
        }

        int[] resultArray = new int[numSeasons];
        for (int i = 0; i < numSeasons; i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
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