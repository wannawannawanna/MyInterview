package bishi;
/*输入一个数组 [p1, p2, ..., knows, pn], 其中pi代表平民, knows代表名人, 所有的百姓的认识名人, 但是名人不认识百姓, 百姓之间有可能互相认识,
 *  func(p1, p2)返回true表示p1认识p2, 
用最好的时间复杂度找到名人*/

//暴力法，找一个元素，看认不认识其他所有元素

public class famous {
	public static int findFamous(int[] nums) {
		int length = nums.length;
		if(length <= 0 && nums == null) {
			return -1;
		}
		int may = 0;  //记录可能是名人的下标
		for(int i = 0; i < length; i++) {
			if(maybeFamous(nums[may], nums[i])) {  //可能的名人认识后面的人，那么后面的人更有可能是名人
				may = i;
			}
		}
		for(int i = 0; i < length; i++) { //判断名人是否真在这个数组里
			if(!maybeFamous(nums[i], nums[may])) {
				return -1;
			}
			if(maybeFomous(nums[may],nums[i])) {
				return -1;
			}
		}
		return may;
		
	}
	public static boolean maybeFamous() {
		
	}
	public static void main(String[] args) {
		int[] nums = new int[]{   };
		System.out.println(findFamous(nums));
	}
}
