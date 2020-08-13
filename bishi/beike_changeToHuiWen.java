package bishi;
import java.util.*;
public class beike3 {
	public static int count(String str, int len) {
		int res = 0;
		for(int i = 0; i < len / 2; i++) {
			if(str.charAt(i) != str.charAt(len - 1 - i)) {
				res++;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);	
		while(input.hasNext()) {
			int len = input.nextInt();
			input.nextLine();
			String str = input.nextLine();
			System.out.println(count(str, len));
		}		
	}
}
//给定一个字符串，将其修改几个字符之后可以得到一个回文，问修改的最少次数是多少？
