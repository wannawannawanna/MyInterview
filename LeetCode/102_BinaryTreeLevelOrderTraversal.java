//二叉树，按行打印，分多行打印
/*input:
    3
   / \
  9  20
    /  \
   15   7
output:   
[
  [3],
  [9,20],
  [15,7]
]   
*/
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>();
        if(root == null){
            return result;
        }
        int TobePrint = 1;  //记录当前行还没有被打印的节点数
        int NextLength = 0;  //记录下一行有多少个节点
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode pCurrent = queue.pop();
            list.add(pCurrent.val);
            if(pCurrent.left != null){
                queue.add(pCurrent.left);
                NextLength++;
            }
            if(pCurrent.right != null){
                queue.add(pCurrent.right);
                NextLength++;
            }
            TobePrint--;
            if(TobePrint == 0){  //当前行的打印完之后，要更新TobePrint和NextLength
                TobePrint = NextLength;
                NextLength = 0;
                result.add(list);
                list = new ArrayList<Integer>();
                
            }
        }
        return result;
    }
}
