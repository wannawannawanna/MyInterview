/*Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.*/
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        if(nums == null || length <= 0){
            return 0;
        }
        int left = 0;
        int right = -1;  //这个right是根据数组本身已经是排序的情况配的初始值（right-left+1得等于0）
        int max = nums[0];
        int min = nums[length - 1];
        for(int i = 1; i < length; i++){ //正序扫描的时候维护一个max，只要当前值比max小就更新right
            if(nums[i] >= max){
                max = nums[i];
            }
            else{
                right = i; //小于的时候才更新
            }
        }
        for(int i = length - 2; i >= 0; i--){//逆序扫描，维护min，如果当前值比min大，更新left
            if(nums[i] <= min){
                min = nums[i];
            }
            else{
                left = i;
            }
        }
        return right - left + 1; //返回乱序排序的子数组长度
    }
}
