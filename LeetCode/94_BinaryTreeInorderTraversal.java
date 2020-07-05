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
 
 //非递归，用辅助空间栈
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pNode = root;
        while(!stack.isEmpty() || pNode != null){
            while(pNode != null){
                stack.push(pNode);
                pNode = pNode.left;
            }
            if(!stack.isEmpty()){
                pNode = stack.pop();
                result.add(pNode.val);
                pNode = pNode.right;
            }
        }
        return result;
    }
}


//递归
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null){
            return result;
        }
        inorder(root, result);        
        return result;
    }
    public void inorder(TreeNode root, List<Integer> list){
        if(root == null){  
            return;  
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
