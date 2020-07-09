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
 
 
 //快速排序 时间复杂度o(NlogN),空间复杂度o(1)
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null){
            return null;
        }
        QuickSort(head, null); //最后一个元素后面的null,这样才能把最后一个元素排序上
        return head;
    }
    public void QuickSort(ListNode begin, ListNode end){
        if(begin == null || begin == end){
            return;
        }
        ListNode mid = partition(begin, end);
        QuickSort(begin, mid);
        QuickSort(mid.next, end);
    }
    public ListNode partition(ListNode begin, ListNode end){        
        ListNode base = begin;
        ListNode slow = begin;
        ListNode fast = slow.next;
        while(fast != null){
            if(fast.val < base.val){
                slow = slow.next;
                int temp = slow.val;  //交换元素不能用swap
                slow.val = fast.val;
                fast.val = temp;                
            }
            fast = fast.next;
        }
        int temp = slow.val;
        slow.val = base.val;
        base.val = temp;
        return slow;
    }
    public void swap(int i, int j){
        int temp = i;
        i = j;
        j = temp;
    }
}


//归并排序
