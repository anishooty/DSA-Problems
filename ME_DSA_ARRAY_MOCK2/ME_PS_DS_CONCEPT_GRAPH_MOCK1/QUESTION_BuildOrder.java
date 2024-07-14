import java.util.*;

public class BuildOrder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numProjects = sc.nextInt();
        int numDependencies = sc.nextInt();

        sc.nextLine(); 

        List<String> projects = new ArrayList<>(numProjects);
        for (String name : sc.nextLine().split(" ")) {
            projects.add(name);
        }

        List<List<String>> dependencies = new ArrayList<>(numDependencies);
        for (int i = 0; i < numDependencies; i++) {
            List<String> dependency = new ArrayList<>();
            for (String dep : sc.nextLine().split(" ")) {
                dependency.add(dep);
            }
            dependencies.add(dependency);
        }

        List<String> order = buildOrder(projects, dependencies);

        if (order.isEmpty()) {
            System.out.println("-1"); 
        } else {
            for (String project : order) {
                System.out.print(project + " ");
            }
            System.out.println();
        }

        sc.close();
    }

    public static List<String> buildOrder(List<String> projects, List<List<String>> dependencies) {
        
        Map<String, List<String>> graph = new HashMap<>();
        for (String project : projects) {
            graph.put(project, new ArrayList<>());
        }

        for (List<String> dependency : dependencies) {
            String dependent = dependency.get(1); 
            String prerequisite = dependency.get(0);
            graph.get(prerequisite).add(dependent);
        }

        List<String> order = topologicalSort(graph);
        if (order.size() != projects.size()) {
            return new ArrayList<>();
        }

        return order;
    }

    private static List<String> topologicalSort(Map<String, List<String>> graph) {
        Map<String, Integer> inDegree = new HashMap<>();
        for (String project : graph.keySet()) {
            inDegree.put(project, 0);
        }

        for (String project : graph.keySet()) {
            for (String dependent : graph.get(project)) {
                inDegree.put(dependent, inDegree.get(dependent) + 1);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for (String project : inDegree.keySet()) {
            if (inDegree.get(project) == 0) {
                queue.add(project);
            }
        }

        List<String> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            String project = queue.poll();
            order.add(project);

            for (String dependent : graph.get(project)) {
                int newInDegree = inDegree.get(dependent) - 1;
                inDegree.put(dependent, newInDegree);
                if (newInDegree == 0) {
                    queue.add(dependent);
                }
            }
        }

        return order;
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