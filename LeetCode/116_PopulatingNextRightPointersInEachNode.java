/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
import java.util.Queue;
import java.util.LinkedList;  //实现队列，添加add,peek,poll等，也可以是双向队列，可以从头尾都进行操作
class Solution {
    public Node connect(Node root) {
        Queue<Node> deque = new LinkedList<Node>(); 
        if(root == null){
            return null;
        }
        Node pNode = root;
        deque.add(pNode);      
        int count = 0;
        int i = 0;
        while(!deque.isEmpty()){
            pNode = deque.poll();
            count++;
            if(count == Math.pow(2, i)){  //完全二叉树，所以可以用2的幂次，答案是queue长度的for循环
                count = 0;
                i++;
            }
            else{
                pNode.next = deque.peek();
            }
            if(pNode.left != null){
                deque.add(pNode.left);
            }
            if(pNode.right != null){
                deque.add(pNode.right);
            }       
           
        }
        return root;
    }
}
