//有三个颜色，用0,1,2表示，给定一个由着三个数字构成的数组，排序
//双指针
class Solution {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length <= 0){
            return;
        }
        int left = 0;   //记录0的位置
        int right = nums.length - 1;  //记录2的位置
        for(int i = 0; i <= right; i++){
            if(nums[i] == 0){
                swap(nums, i, left++);   //
            }
            else if(nums[i] == 2){
                swap(nums, i--, right--);  //当前元素如果是2，要换到right位置，但是换到当前位置的不知道是什么呢，所以要i--重新判断一次
            }
            else{
                continue;
            }
        }
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
