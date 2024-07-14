import java.util.Scanner;

public class NQueens {
    void solveNQueens(int n, int row, boolean[] cols, boolean[] diagonals1, boolean[] diagonals2, String[] board, StringBuilder sb) {
        if (row == n) {
            for (String line : board) {
                sb.append(line).append('\n');
            }
            sb.append('\n');
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!cols[col] && !diagonals1[row + col] && !diagonals2[row - col + n - 1]) {
                cols[col] = true;
                diagonals1[row + col] = true;
                diagonals2[row - col + n - 1] = true;
                board[row] = buildRow(n, col);
                solveNQueens(n, row + 1, cols, diagonals1, diagonals2, board, sb);
                cols[col] = false;
                diagonals1[row + col] = false;
                diagonals2[row - col + n - 1] = false;
            }
        }
    }

    String[] buildBoard(int n) {
        String[] board = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('0');
            }
            board[i] = sb.toString();
        }
        return board;
    }

    String buildRow(int n, int col) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i == col ? '1' : '0');
        }
        return sb.toString();
    }

    String nQueens(int n) {
        StringBuilder sb = new StringBuilder();
        String[] board = buildBoard(n);
        boolean[] cols = new boolean[n];
        boolean[] diagonals1 = new boolean[2 * n - 1];
        boolean[] diagonals2 = new boolean[2 * n - 1];
        solveNQueens(n, 0, cols, diagonals1, diagonals2, board, sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        NQueens result = new NQueens();
        String board = result.nQueens(n);
        if (board.isEmpty()) {
            System.out.println("No Solution Exists");
        } else {
            System.out.println(board);
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