//反转链表
package Ali;
class ListNode{
	int val;
	ListNode next;
	public ListNode(int val) {
		this.val = val;
	}
}
public class ReverseList {
	static ListNode head;
	static ListNode current;
	public static void add(int data) {
		if(head == null) {
			head = new ListNode(data);
			current = head;
		}
		else {
			current.next = new ListNode(data);
			current = current.next;
		}
	}
	public static ListNode reverseList(ListNode head) {
		ListNode reverseListHead = null;
		if(head == null) {
			return reverseListHead;
		}
		ListNode prev = null;
		ListNode pNode = head;
		while(pNode != null) {
			ListNode pNext = pNode.next;
			if(pNext == null) {  //如果下一个节点是null,那么当前节点就应该是反转链表的头结点
				reverseListHead = pNode;
			}
			pNode.next = prev;  //反转
			prev = pNode;  //更新下一个节点的前一个节点
			pNode = pNext;	//往后扫		
		}
		return reverseListHead;
	}
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			add(i);
		}
		ListNode pNode = head;
		while(pNode != null) {
			System.out.println(pNode.val);
			pNode = pNode.next;
		}
		
		System.out.println(reverseList(head).val);
	}
}
