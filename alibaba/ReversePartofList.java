/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == 1){
            return reverseHeadM(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1); // 递归减m,n，知道找到m等于1，然后就相当于反转链表中前n个节点
        return head;
    }
    ListNode temp = null;
    public ListNode reverseHeadM(ListNode head, int m){
        if(m == 1){
            temp = head.next;  //记录m+1个节点
            return head;
        }
        ListNode last = reverseHeadM(head.next, m - 1);
        head.next.next = head;
        head.next = temp;  //反转之前的链表头结点最后指向m + 1个节点
        return last;
    }
    
}
