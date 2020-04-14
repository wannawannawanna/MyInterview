package Leetcode;

class ListNode {
	int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class example2 {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        int temp = 0;
        ListNode result = new ListNode(0);  //因为需要返回链表中第二个节点，return result.next
        //所以需要一个临时的链表pCurrent,否则最后返回result.next的时候则返回空，因为result的下一个节点还没有被赋值
        ListNode pCurrent = result;
        while(l1 != null || l2 != null){
            int x = (l1 != null)?l1.val:0;  //重点看是否为空
            int y = (l2 != null)?l2.val:0;
            temp = x + y + temp;
            pCurrent.next = new ListNode(temp % 10); //先给下一个节点赋值，才能指向下一个节点，否则为空
            temp = temp / 10;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }          
            pCurrent = pCurrent.next;
        }
        if(temp > 0){  //考虑最高位进位
        	pCurrent.next = new ListNode(temp);
        }
        return result.next;
    }
	public ListNode head;
	public ListNode current;
	public void add(int i) {
		if(head == null) {
			head = new ListNode(i);
			current = head;
		}
		else {
			current.next = new ListNode(i);
			current = current.next;
		}
	}
	public static void print(ListNode pNode) {
		if(pNode == null) {
			return;
		}
		while(pNode != null) {
			System.out.println(pNode.val);
			pNode = pNode.next;
		}
	}
	public static void main(String[] args) {
		example2 ex = new example2();
		ex.add(2);
		ex.add(4);
		ex.add(3);
		example2 ex1 = new example2();
		ex1.add(5);
		ex1.add(6);
		ex1.add(4);
		print(addTwoNumbers(ex.head, ex1.head));
		
	}
}
	    
