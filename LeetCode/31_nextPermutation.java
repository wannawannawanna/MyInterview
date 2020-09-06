/*
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。
*/
class Solution {
    public void nextPermutation(int[] nums) {
        if(nums.length <= 0 || nums == null){
            return;
        }
        //从后往前找，找到第一个非递增的数
        int i = nums.length - 2;
        while(i >= 0 && nums[i + 1] <= nums[i]){
            i--;
        }
        // 如果存在比当前数字更大的数字, 由于i的右侧为一个递减数组, 所以比其大的下一个数字只能是将i换为其右侧中比它大一点的数字
        if(i >= 0){
            //从后往前找，找到第一个比i位置上的数大的数字
            int j = nums.length - 1;
            while(i < j && nums[i] >= nums[j]){
                j--;
            }
            swap(nums, i, j);
        }     
        reverse(nums, i + 1);
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void reverse(int[] nums, int start){
        int i = start;
        int j = nums.length - 1;
        while(i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
