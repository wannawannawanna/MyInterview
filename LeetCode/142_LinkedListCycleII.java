//解法一，环的入口节点
/**  
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode HasCycle(ListNode pHead){
		if(pHead == null || pHead.next ==null) {
		  	return null;
		}
		ListNode slow = pHead;
		ListNode fast = pHead;
		 
		while(fast != null && fast.next != null && fast.next.next != null) {
		  	slow = slow.next;
		  	fast = fast.next.next;  //这块就要用fast.next.next;不能用slow.next
		  	if(slow == fast) {      		
		          return slow;
		  	}
		}
		return null;
	}
    public int GetCycleLength(ListNode listnode) {
	    if(listnode == null) {
	  	return 0;
	    }
	    ListNode current = listnode;
	    int nodenum = 0;
	    while(current != null){
		current = current.next;
		nodenum++;
		if(current == listnode)
		     return nodenum;
	    }
        return nodenum;
	  
	}
	public ListNode detectCycle(ListNode head)
	{
        if(head == null) {
            return null;
        }
        ListNode meet = HasCycle(head);
        if(meet == null) {
             return null;
        }
          
        int length = GetCycleLength(meet);
	    ListNode slow = head;
	    ListNode fast = head;
	    for(int i = 0; i < length; i++) {
		    fast = fast.next;
	    }
	    while(fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}	  
		return fast;			

    }

}


//解法二，Floyed算法
public class Solution {
    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
}
public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an e***ance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the e***ance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }
}    

