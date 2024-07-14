import java.util.ArrayList;
import java.util.Scanner;

public class BalancedBraces {
    public ArrayList<String> balancedBraces(int n) {
        ArrayList<String> result = new ArrayList<>();
        generateParentheses("", 0, 0, n, result);
        return result;
    }

    private void generateParentheses(String current, int open, int close, int n, ArrayList<String> result) {
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        if (open < n) {
            generateParentheses(current + "(", open + 1, close, n, result);
        }
        if (close < open) {
            generateParentheses(current + ")", open, close + 1, n, result);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<String> answer = new BalancedBraces().balancedBraces(n);
        for (String s : answer) {
            System.out.println(s);
        }
    }
}
