//寻找二叉搜索树中第k个节点，中序遍历
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
import java.util.Stack;
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if(root == null || k < 0){
            return -1;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
       // stack.push(root);
        int count = 0;
        TreeNode pNode = root;
        while(pNode != null || !stack.isEmpty()){
            while(pNode != null){                            
                stack.push(pNode);
                pNode = pNode.left;                
            }
            if(!stack.isEmpty()){
                pNode = stack.pop();
                count++;
                if(count == k){
                    return pNode.val;
                }
                pNode = pNode.right;
                
            }
        }
        return -1;
    }
}
