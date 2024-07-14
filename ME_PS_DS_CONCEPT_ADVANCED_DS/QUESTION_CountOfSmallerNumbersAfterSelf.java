import java.util.*;

class Solution {
    public ArrayList<Integer> countOfSmallerNumberAfterSelf(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int n = nums.length;
        int[] counts = new int[n];
        TreeNode root = null;
        
        for (int i = n - 1; i >= 0; i--) {
            root = insert(root, nums[i], counts, i);
        }
        
        for (int count : counts) {
            result.add(count);
        }
        
        return result;
    }
    
    private TreeNode insert(TreeNode root, int val, int[] counts, int index) {
        if (root == null) {
            root = new TreeNode(val);
        } else if (val <= root.val) {
            root.leftCount++;
            root.left = insert(root.left, val, counts, index);
        } else {
            counts[index] += root.leftCount + root.dupCount;
            root.rightCount++;
            root.right = insert(root.right, val, counts, index);
        }
        return root;
    }
}

class TreeNode {
    int val;
    int leftCount;
    int rightCount;
    int dupCount;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val) {
        this.val = val;
        this.leftCount = 0;
        this.rightCount = 0;
        this.dupCount = 1;
        this.left = null;
        this.right = null;
    }
}
