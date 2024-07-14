import java.util.*;

class GameOfLife {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> b = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                b.add(sc.nextInt());
            }
            a.add(b);
        }

        ArrayList<ArrayList<Integer>> ans = gameOfLife(a);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.print("\n");

        }
    }

    static ArrayList<ArrayList<Integer>> gameOfLife(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();
        ArrayList<ArrayList<Integer>>result = new ArrayList<>();
        for(int i=0;i<m;i++){
            ArrayList<Integer> newrow = new ArrayList<>();
            for(int j=0;j<n;j++){
                int liveNeighbours = countliveNeighbora(a,i,j);
                int currentCell = a.get(i).get(j);
                if(currentCell==1){
                    if(liveNeighbours<2 || liveNeighbours>3){
                        newrow.add(0);
                    }
                    else{
                        newrow.add(1);
                    }
                }else{
                    if(liveNeighbours==3){
                        newrow.add(1);
                    }else{
                        newrow.add(0);
                    }
                }
            }
            result.add(newrow);
        }
        return result;
    }
    private static int countliveNeighbora(ArrayList<ArrayList<Integer>>a,int x,int y){
        int count =0;
        int m = a.size();
        int n=a.get(0).size();
        int[][] directions = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        for(int[] dir:directions){
            int newX = x+dir[0];
            int newY = y+dir[1];
            if(newX>=0 && newX<m && newY>=0 && newY<n && a.get(newX).get(newY)==1){
                count++;
            }
        }
        return count;
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