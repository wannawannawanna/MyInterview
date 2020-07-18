//给一个山形数组，一维数组，数组元素从左到右逐渐增大，达到一定值之后开始逐渐下降。问排序输出（不重复），时间复杂度o(n),空间复杂度o(1)

import java.util.ArrayList;

public class mountain {
	public static ArrayList<Integer> sort(int[] array) {
		int length = array.length;
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(array == null || length <= 0) {
			return result;
		}
		int left = 0;
		int right = length - 1;
		int prev = Integer.MAX_VALUE;
		while(left <= right) {
			while(left <= right && prev == array[left]) {
				left++;
			}
			while(left <= right && prev == array[right]) {
				right--;
			}
			if(left <= right && array[left] <= array[right]) {
				result.add(array[left]);
				prev = array[left];
				left++;
			}
			else {
				if(left <= right){
					result.add(array[right]);
					prev = array[right];
					right--;
				}
				
			}
		}
		return result;
	}
	public static void main(String[] args) {
		int[] array = {1,2,2,5,10,9,8,2,1,1};
		ArrayList<Integer> result = sort(array);
		for(Integer i : result) {
			System.out.println(i);
		}
	}
}
