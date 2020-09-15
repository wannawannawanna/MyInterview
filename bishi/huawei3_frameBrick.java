/*给定两个字符串 frame 是底下基座，brick 是落下的方块，判断brick落下后，消除完无空行的行后，剩下的最小行数。俄罗斯方块只能左右移动，不能旋转
解题思路：首先计算两个字符串的长度 lenF 和 lenB，然后把brick字符串与frame字符串通过一个for循环进行错位相加（错位的位数从0到lenF-lenB），分别计算每种情况
下的剩余行数的最大值，然后求全部最大值中的最小值*/
package bishi;
import java.util.*;
public class huawei3 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String frame = input.nextLine();
		String brick = input.nextLine();
		int lenF = frame.length();
		int lenB = brick.length();
		int add0 = lenF - lenB;
		int res = Integer.MAX_VALUE;
		for(int i = add0; i >= 0; i--) { //从第一个字符对齐开始，每次向后移动一位，进行错位相加
			String temp = brick;
			int[] arr = new int[lenF];
			for(int j = 0; j < i; j++) {
				temp += "0"; //对brick后面补0,0的个数等于错位相加时两个字符串后面差几个位置
			}
			for(int k = lenF - 1; k >= 0; k--) {  //对应位置相加，保存在arr数组中，
				arr[k] += frame.charAt(k) - '0';
				if(k - (add0 - i) >= 0) { //主要是为了brick从左往右走，然后对齐frame做的，
					arr[k] += temp.charAt(k - (add0 - i)) - '0'; //就是temp的长度是变长的，所以要防止数组下标越界
				}
			}
			int maxTemp = Integer.MIN_VALUE;
			int minTemp = Integer.MAX_VALUE;
			int maxH = Integer.MIN_VALUE;
			for(int t = 0; t < arr.length; t++) {  //求每次错位相加消除后，剩下的最高的一列的高度
				maxTemp = Math.max(maxTemp, arr[t]);
				minTemp = Math.min(minTemp, arr[t]);
				maxH = Math.max(maxH, maxTemp - minTemp);
			}
			res = Math.min(res, maxH);//求每次错位相加消除后最小高度
		}
		System.out.println(res);
	}
}
