import java.util.*;


class MergeKSortedArrays{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < k; i++) {
            int n = sc.nextInt();
            ArrayList<Integer> array = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                array.add(sc.nextInt());
            }
            arrays.add(array);
        }
        ArrayList<Integer> ans = mergeKSortedArrays(arrays);
        for (Integer x: ans) {
            System.out.print(x + " ");
        }
    }

    static ArrayList<Integer> mergeKSortedArrays(ArrayList<ArrayList<Integer>> arrays){
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Element> minHeap = new PriorityQueue<>();

        for (int i = 0; i < arrays.size(); i++) {
            if (!arrays.get(i).isEmpty()) {
                minHeap.offer(new Element(arrays.get(i).get(0), i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            Element elem = minHeap.poll();
            ans.add(elem.value);
            int arrayIndex = elem.arrayIndex;
            int elementIndex = elem.elementIndex + 1;
            if (elementIndex < arrays.get(arrayIndex).size()) {
                minHeap.offer(new Element(arrays.get(arrayIndex).get(elementIndex), arrayIndex, elementIndex));
            }
        }

        return ans;
    }
    static class Element implements Comparable<Element> {
        int value;
        int arrayIndex;
        int elementIndex;

        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(Element o) {
            return Integer.compare(this.value, o.value);
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