import java.util.*;

public class Lilliput {
    public static Vector<Integer> lilliput(int n, int heights[], int num_actions, String actions[]) {
        SegmentTree st = new SegmentTree(n);
        Vector<Integer> result = new Vector<>();

        for (int i = 0; i < n; i++) {
            st.update(i, heights[i]);
        }

        for (int i = 0; i < num_actions; i++) {
            String[] action = actions[i].split(" ");
            char type = action[0].charAt(0);
            int x = Integer.parseInt(action[1]);
            int y = Integer.parseInt(action[2]);

            if (type == 'q') {
                result.add(st.query(x, y));
            } else if (type == 'u') {
                st.update(x, y);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, q;

        n = sc.nextInt();
        int heights[] = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        q = sc.nextInt(); // Num actions
        sc.nextLine();

        String actions[] = new String[q];
        for (int i = 0; i < q; i++) {
            actions[i] = sc.nextLine();
        }
        sc.close();

        Vector<Integer> answer = lilliput(n, heights, q, actions);
        for (int i = 0; i < answer.size(); i++)
            System.out.println(answer.get(i));
    }
}

class SegmentTree {
    int[] st;
    int size;

    public SegmentTree(int n) {
        size = 4 * n;
        st = new int[size];
    }

    public void update(int index, int val) {
        update(0, 0, size / 4 - 1, index, val);
    }

    private void update(int v, int tl, int tr, int pos, int newVal) {
        if (tl == tr) {
            st[v] = newVal;
        } else {
            int tm = (tl + tr) / 2;
            if (pos <= tm)
                update(v * 2 + 1, tl, tm, pos, newVal);
            else
                update(v * 2 + 2, tm + 1, tr, pos, newVal);
            st[v] = Math.min(st[v * 2 + 1], st[v * 2 + 2]);
        }
    }

    public int query(int l, int r) {
        return query(0, 0, size / 4 - 1, l, r);
    }

    private int query(int v, int tl, int tr, int l, int r) {
        if (l > r)
            return Integer.MAX_VALUE;
        if (l == tl && r == tr)
            return st[v];
        int tm = (tl + tr) / 2;
        return Math.min(query(v * 2 + 1, tl, tm, l, Math.min(r, tm)),
                query(v * 2 + 2, tm + 1, tr, Math.max(l, tm + 1), r));
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