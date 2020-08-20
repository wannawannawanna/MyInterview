//回文链表，用双指针
package bishi;
import java.util.*;
class ListNode{
	int val;
	ListNode next;
	ListNode(int x){
		val = x;
	}
}

public class youzan2 {
	public boolean isPalindrome(ListNode head) {
		List<Integer> list = new ArrayList<>();
		ListNode current = head;
		while(current != null) {
			list.add(current.val);
			current = current.next;
		}
		int start = 0;
		int end = list.size() - 1;
		while(start < end) {
			if(!list.get(start).equals(list.get(end))) {
				return false;
			}
		}
		return true;
	}
}
