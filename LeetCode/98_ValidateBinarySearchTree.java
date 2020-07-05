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
    public boolean isValidBST(TreeNode root) {    
        if(root == null){
            return true;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pNode = root;
        TreeNode pPrev = null;  //引入一个prev节点，用于前后节点值进行比较
        while(pNode != null || !stack.isEmpty()){
            while(pNode != null){
                stack.push(pNode);
                pNode = pNode.left;
            }
            if(!stack.isEmpty()){
                pNode = stack.pop();
                //中序遍历中这是往list添加元素的位置，添加之后如果是二叉搜索树，那么就是有序的
                if(pPrev != null && pPrev.val >= pNode.val){
                    return false;
                }
                pPrev = pNode;
                pNode = pNode.right;
            }
        }
        
        return true;
    }
}
