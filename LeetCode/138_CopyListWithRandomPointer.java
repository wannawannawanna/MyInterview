/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        cloneNode(head); //复制每个节点
        connectRandomNode(head);  //random节点指向
        return ReConstructNode(head);  //奇偶分开
    }
    public void cloneNode(Node head){
        if(head == null){
            return;
        }
        Node current = head;
        while(current != null){
            Node Cloned = new Node(current.val);    //根据节点值，创建新的节点，当做当前节点的下一个节点
            Cloned.next = current.next;
            Cloned.random = null;
            current.next = Cloned;
            current = current.next.next;
        }
    }
    public void connectRandomNode(Node head){
        if(head == null){
            return;
        }
        Node current = head;
        while(current != null){
            if(current.random != null){
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }
    }
    public Node ReConstructNode(Node head){
        if(head == null || head.next == null){
            return null;
        }
        Node current = head;
        Node ClonedHead = current.next;
        Node ClonedNode = current.next;
        current.next = ClonedNode.next;
        current = current.next;
        while(current != null){
            ClonedNode.next = current.next;
            ClonedNode = ClonedNode.next;
            current.next = ClonedNode.next;
            current = current.next;
        }
        return ClonedHead;
    }
}
