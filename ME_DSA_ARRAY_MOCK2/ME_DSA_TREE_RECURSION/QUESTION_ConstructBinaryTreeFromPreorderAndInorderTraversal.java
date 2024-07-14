import java.util.Map;
import java.util.HashMap;

class TreeNode {
    public long val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(long x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Solution {

    public TreeNode constructBinaryTreeFromPreorderAndInorderTraversal(int[] preorder, int[] inorder) {
       
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return constructBinaryTreeFromPreorderAndInorderTraversal(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderIndexMap);
    }

    private TreeNode constructBinaryTreeFromPreorderAndInorderTraversal(int[] preorder, int preorderStart, int preorderEnd,
                                                                     int[] inorder, int inorderStart, int inorderEnd,
                                                                     Map<Integer, Integer> inorderIndexMap) {
        if (preorderStart > preorderEnd || inorderStart > inorderEnd) {
            return null;
        }

        
        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);

        
        int rootIndexInorder = inorderIndexMap.get(rootVal);

        
        int leftSubtreeSize = rootIndexInorder - inorderStart;

        root.left = constructBinaryTreeFromPreorderAndInorderTraversal(preorder, preorderStart + 1, preorderStart + leftSubtreeSize,
                                                                    inorder, inorderStart, rootIndexInorder - 1, inorderIndexMap);
        root.right = constructBinaryTreeFromPreorderAndInorderTraversal(preorder, preorderStart + leftSubtreeSize + 1, preorderEnd,
                                                                     inorder, rootIndexInorder + 1, inorderEnd, inorderIndexMap);

        return root;
    }
}
