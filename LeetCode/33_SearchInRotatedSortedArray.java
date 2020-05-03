//先找到旋转数组中最小数字的索引（二分查找），然后根据target与nums[nums.length - 1]的大小判断是在左边还是右边的有序数组，然后可以二分查找，
//总的时间复杂度是log(N)
public class Solution {
	public static int search(int[] nums, int target) {
		if(nums == null || nums.length == 0){
            return -1;
        }
        int begin = 0;
        int end = nums.length - 1;
        System.out.println("end" + end);
        int middle = begin;  //当旋转数组本身是有序的时候，直接返回middle
        while(nums[begin] >= nums[end]){
            middle = (begin + end) / 2;
            if(end - begin == 1){
                middle = end; //最后指向最小数字的下标
                break;
            }
            if(nums[begin] == nums[end] && nums[begin] == nums[middle]) {  //针对10111,11101这种特例，只能顺序扫描
            	middle = MinInOrder(nums, begin, end);
            	break;
            }
            if(nums[begin] <= nums[middle]){            	
                begin = middle;
            }
            else if(nums[end] >= nums[middle]){           	
                end = middle;
            }
        } //直到这儿找到旋转数组中最小数字，后面看target是在哪个（左右）有序数组中      
        if (target == nums[middle]) {
            return middle;
        }    
        //middle是最小数字的下标
        int tbegin = (target <= nums[nums.length - 1])? middle: 0;  //旋转数组有序的时候会从整个数组范围内查找
        int tend = (target <= nums[nums.length - 1])? nums.length - 1: middle;
        /*  当旋转数组本身是有序的时候tbegin和tend的范围太小了
        int tbegin = (target >= nums[0])? 0: middle;  
        int tend = (target >= nums[0])? middle: nums.length - 1;
        */
        while(tbegin <= tend) {  //普通二分
    		int tmiddle = (tbegin + tend) / 2;
    		if(nums[tmiddle] == target) {
    			return tmiddle;
    		}
    		else if(nums[tmiddle] > target) {
    			tend = tmiddle - 1;
    		}
    		else {
    			tbegin = tmiddle + 1;
    		}
    	}
        	    
        return -1;
    }
	public static int MinInOrder(int[] nums, int begin, int end) {
		int temp = nums[begin];  //顺序查找，寻找最小数字的下标
		int result = 0;
		for(int i = begin + 1; i <= end; i++) {
			if(nums[i] < temp) {
				temp = nums[i];
				result = i;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		int[] nums = {1,3};
		System.out.println(search(  nums, 3));
	}
}
