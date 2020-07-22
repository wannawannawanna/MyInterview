/*给你n条长度不一的边，请你选出四条边，组成一个最大的平行四边形，输出最大面积
如，3,3,4,4,5，输出12*/


package bishi;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class MaxSquareOfRectangle {
	public static int maxSquare(int[] nums, int len) {
		if(nums.length != len) {
			return -1;
		}
		ArrayList<Integer> edge = new ArrayList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int num : nums) {
			if(map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			}
			else {
				map.put(num, 1);
			}
			if(map.get(num) > 0 && map.get(num) % 2 == 0) {
				map.put(num, map.get(num) - 2);  //如果一个数是成对出现的，那么就拿走，放进edge里
				edge.add(num);  //相同的num也可以重复放进去
			}		
		}
		if(edge.size() < 2) {  //这个是重点，放进去之后还要判断是不是长度小于2，这样才能保证下面不越界
			return -1;
		}
		Collections.sort(edge, (o1, o2) -> o2 - o1);  //lambda表达式，降序排序
		return (edge.get(0) * edge.get(1));
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int len = input.nextInt();
		int[] nums = new int[len];
		for(int i = 0; i < len; i++) {
			nums[i] = input.nextInt();
		}
		int result = maxSquare(nums, len);
		System.out.println(result);
	}
}
