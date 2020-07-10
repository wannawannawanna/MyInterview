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


//归并排序,链表不用创建额外的空间，所以空间复杂度o(1)，数组的话，需要新建一个同样长度的数组，用来合并的时候用，
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
    public ListNode sortList(ListNode head) {        
        if(head == null || head.next == null){
            return head;  //这块要注意，不能返回null ...因为判断条件还考虑了只有一个节点的时候
        }
        //快慢指针找到中间节点
        ListNode first = head;
        ListNode second = head;
        while(first != null && first.next != null && first.next.next != null){
            first = first.next;
            second = second.next;          
            first = first.next;
                     
        }
        ListNode mid = second.next;  //找到中间节点之后应该要指向next,才是第二段的开始
        second.next = null; //分成两个链表
        //sort each half
        ListNode p1 = sortList(head);
        ListNode p2 = sortList(mid);
        //merge each half
        return merge(p1, p2);
    }

    public ListNode merge(ListNode begin1, ListNode begin2){
        ListNode tempHead = new ListNode(0);  //创建一个头结点
        ListNode temp = tempHead;  
        ListNode p1 = begin1;
        ListNode p2 = begin2;
        while(p1 != null && p2 != null){
            if(p1.val <= p2.val){
                temp.next = p1;
                p1 = p1.next;
            }
            else{                             
                temp.next = p2;
                p2 = p2.next;                
            }   
            temp = temp.next;
        }
        //p1没复制完
        while(p1 != null){
            temp.next = p1;
            p1 = p1.next;
            temp = temp.next;
        }
        //p2没复制完
        while(p2 != null){
            temp.next = p2;
            p2 = p2.next;
            temp = temp.next;
        }
        return tempHead.next;
    }
    
}
