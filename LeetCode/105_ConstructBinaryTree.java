//重建二叉树，从前序遍历和中序遍历，重建二叉树
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int prelength = preorder.length;
        int inlength = inorder.length;
        if(preorder == null || inorder == null || prelength <= 0 || inlength <= 0 || prelength != inlength){
            return null;
        }
        int preStart = preorder[0];
        TreeNode root = new TreeNode(preStart);
        if(prelength == 1){
            return root;
        }
        int index = 0;
        while(inorder[index] != preStart){
            index++;
        }
        int leftLength = index;
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1 + leftLength), Arrays.copyOfRange(inorder, 0, leftLength));  //参数是数组，下线和上线，上线不包括
        root.right = buildTree(Arrays.copyOfRange(preorder, 1+leftLength, prelength), Arrays.copyOfRange(inorder, leftLength + 1, inlength));
        return root;
    }
}
