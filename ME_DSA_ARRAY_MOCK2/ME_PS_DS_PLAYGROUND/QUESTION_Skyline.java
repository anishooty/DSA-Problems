import java.util.*;
class Skyline {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<ArrayList<Integer> > arr = new ArrayList<ArrayList<Integer>> ();
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer> ();
			temp.add(sc.nextInt());
			temp.add(sc.nextInt());
			temp.add(sc.nextInt());
			arr.add(temp);
		}
		List<List<Integer>> ans = skyline( arr);
		for ( int i = 0; i < ans.size(); i++) {
			System.out.print(ans.get(i).get(0) + " " + ans.get(i).get(1));
			System.out.println();
		}
	}
	public static  List<List<Integer>> skyline(  ArrayList<ArrayList<Integer>> buildings) {
		List<List<Integer>> result = new ArrayList<>();

        List<int[]> criticalPoints = new ArrayList<>();

        for (List<Integer> building : buildings) {
            int left = building.get(0);
            int right = building.get(1);
            int height = building.get(2);

            criticalPoints.add(new int[]{left, -height});
            criticalPoints.add(new int[]{right, height});
        }

        Collections.sort(criticalPoints, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(0); 

        int prevMaxHeight = 0;

        for (int[] point : criticalPoints) {
            int x = point[0];
            int y = Math.abs(point[1]); 

            if (point[1] < 0) { 
                maxHeap.offer(y); 
            } else { 
                maxHeap.remove(y); 
            }

            int currentMaxHeight = maxHeap.peek();

            if (currentMaxHeight != prevMaxHeight) {
                List<Integer> keyPoint = new ArrayList<>();
                keyPoint.add(x);
                keyPoint.add(currentMaxHeight);
                result.add(keyPoint);
                prevMaxHeight = currentMaxHeight;
            }
        }

        return result;
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