/*给出一个字符串 s（仅含有小写英文字母和括号）。

请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。*/

package bishi;
import java.util.*;
public class genshuixue2 {
	private static int[] parent;
	public static String reverseParentheses(String s) {
		StringBuilder sb = new StringBuilder();
		char[] arr = s.toCharArray();
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < arr.length; i++) {
			if(s.charAt(i) == '(') {
				stack.push(i);
			}
			if(s.charAt(i) == ')') {
				reverse(arr, stack.pop() + 1, i - 1);
			}
		}
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != ')' && arr[i] != '(') {
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}
	public static void reverse(char[] arr, int left, int right) {
		while(right > left){
			char tmp = arr[left];
			arr[left] = arr[right];
			arr[right] = tmp;
			left++;
			right--;
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		System.out.println(reverseParentheses(str));
	}
}
