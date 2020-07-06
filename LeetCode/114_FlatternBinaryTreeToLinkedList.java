//先序遍历的变种,按照先序遍历的顺序扩展成链表，可以用stack，先入右节点，再入左节点就好了
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
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode prev = null;  //像ListNode似的current.next=***,然后current=current.next;
        while(!stack.isEmpty()){
            TreeNode current = stack.pop();
            if(prev != null){
                prev.right = current;
                prev.left = null;
            }
            if(current.right != null){
                stack.push(current.right);
            }
            if(current.left != null){
                stack.push(current.left);
            }
            prev = current;
        }
    }
}
