/*给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。*/
//贪心算法，从前往后
class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length <= 0){
            return false;
        }
        int farest = 0;
        for(int i = 0; i < nums.length; i++){
            if(farest < i){          //最远能跳的步数都到不了这个位置，所以返回false   
                return false;
            }
            farest = Math.max(i + nums[i], farest);
        }
        return true;
    }
}

//贪心算法，从后往前
class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length <= 0){
            return false;
        }
        int length = nums.length;
        int lastIdx = length - 1;  //记录可以走到的位置
        for(int i = length - 2; i >= 0; i--){
            if(i + nums[i] >= lastIdx){
                lastIdx = i;
            }
        }
        return lastIdx == 0;
    }
}
