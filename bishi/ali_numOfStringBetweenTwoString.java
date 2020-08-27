/* * 
输入：
4，表示几组
1 z a，  第一个数表示后面的字符串包含几个字符，后面就是两个字符串
1 a z
2 az bb
3 bbb bbb
输出：
如果第一个字符串比第二个字符串大，返回0；如果小返回中间包含几个字符串，按字典序顺序
*/
package bishi;
import java.util.*;
public class ali2 {
	public static long dp(String first, String second, int n) {
		if(first.compareTo(second) >= 0) {  //first.compareTo(second)是按字典序顺序对比
			return 0;
		}
        char[] str1 = first.toCharArray();
        char[] str2 = second.toCharArray();
        long res = 0;
		long suma = 0;
		long sumb = 0;
		for(int j = 0; j < n; j++) { //从第0位开始到最后一位，使得first的字符跟second的字符一样
			suma = suma + (str1[j] - 'a') * (long)Math.pow(26, n - 1 - j);
            sumb = sumb + (str2[j] - 'a') * (long)Math.pow(26, n - 1 - j);			
		}
		res = res + sumb - suma;
		res--;  //这个是因为最后first会变的跟second一模一样，因为要取中间有多少个，所以要减1
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int number = input.nextInt();
			input.nextLine();
			for(int i = 0; i < number; i++) {
				String[] nextLine = input.nextLine().trim().split(" ");
				int lengthOfWord = Integer.valueOf(nextLine[0]);
				String A = nextLine[1];
				String B = nextLine[2];
                long res = dp(A,B,lengthOfWord);
				System.out.println(res);
			}

		}
	}

}


