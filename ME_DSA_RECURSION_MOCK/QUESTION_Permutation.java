import java.util.*;

class Permutation {
    public List<List<Integer>> permutation(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, tempList, result, used);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> tempList, List<List<Integer>> result, boolean[] used) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                tempList.add(nums[i]);
                used[i] = true;
                backtrack(nums, tempList, result, used);
                tempList.remove(tempList.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int nums[] = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        List<List<Integer>> result = new Permutation().permutation(nums);

        for (int i = 0; i < result.size(); ++i) {
            for (int j = 0; j < result.get(i).size(); ++j) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
