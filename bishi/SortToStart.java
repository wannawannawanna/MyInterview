/*有一种排序算法定义如下，该排序算法每次只能把一个元素提到序列的开头，，现在给一个乱序的1-n的排列，请你计算出最少需要多少次操作才可以使得原序列从小到大有序。
如，输入：2 1 3 4，输出1
*/
package bishi;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
public class SortToStart {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int len = input.nextInt();
		Integer[] nums = new Integer[len];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < len; i++) {
			nums[i] = input.nextInt();
			map.put(nums[i], i);
		}
		Arrays.sort(nums, (o1, o2) -> (o2 - o1)); //因为要把小的数提到前面保证从小到大排序，所以应该先降序排序，o2-o1,
		        //假如要把大的数据提到后面，来保证从小到大排序的话，应该先升序排序，o1 -o2
				//Arrays.sort(),如果想自己写比较器或者lambda表达式，且数组本身是一维数组那么它必须是包装类型的，二维数组就不需要；比较器的话，重写public int compare（）函数，
		       //lambda表达式的如果只有一句，不用大括号，不用return，多句的话，必须用大括号括起来，还得有return *******
		int t = -1;  //放开头
		int count = 0;
		for(int i = 0; i < len - 1; i++) {
			if(map.get(nums[i]) < map.get(nums[i + 1])) {  //找下标是前一个比后一个小的
				map.put(nums[i + 1], t--);
				count++;
			}
			 
		}
		System.out.println(count);
	}
}



//百度题，有一种排序算法定义如下，该排序算法每次只能把一个元素提到序列的末尾，，现在给一个乱序的1-n的排列，请你计算出最少需要多少次操作才可以使得原序列从小到大有序。
//跟上一题不太不一样,就是一个往前提，一个往后提，所以排序不一样
package bishi;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
public class SortToStart {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int len = input.nextInt();
		Integer[] nums = new Integer[len];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < len; i++) {
			nums[i] = input.nextInt();
			map.put(nums[i], i);
		}
		Arrays.sort(nums, (o1, o2) -> (o1 - o2)); //因为要把小的数提到前面保证从小到大排序，所以应该先降序排序，o2-o1,
		        //假如要把大的数据提到后面，来保证从小到大排序的话，应该先升序排序，o1 -o2
				//Arrays.sort(),如果想自己写比较器或者lambda表达式，且数组本身是一维数组那么它必须是包装类型的，二维数组就不需要；比较器的话，重写public int compare（）函数，
		       //lambda表达式的如果只有一句，不用大括号，不用return，多句的话，必须用大括号括起来，还得有return *******
		int t = len;  //放末尾
		int count = 0;
		for(int i = 0; i < len - 1; i++) {
			if(map.get(nums[i]) > map.get(nums[i + 1])) {  //找下标是前一个比后一个大的
				map.put(nums[i + 1], t++);
				count++;
			}
			 
		}
		System.out.println(count);
	}
}
