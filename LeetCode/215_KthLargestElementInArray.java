//partition + 二分，从小到大排序的快排好写，从打到小不是简单把符号换了就行，所以这里找第k大的元素还是找第k小的，然后len-k就是我们要找的第k大的元素下标
class Solution {
    public int partition(int[] input, int start, int end){
        if(input == null || input.length <= 0){
            return 0;
        }
        if(start == end){  //注意相等的情况
            return start;
        }
        int base = start;
        int slow = start;
        int fast = slow + 1;
        while(fast <= end){
            if(input[fast] < input[base]){
                slow++;
                swap(input, slow, fast);
            }
            fast++;
        }
        swap(input, base, slow);
        return slow;
    }
    public void swap(int[] input, int i, int j){
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    boolean Invalid = false;
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || k < 0 || k > nums.length){
            Invalid = true;
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int index = partition(nums, start, end);
        int target = nums.length - k;
        while(target != index){
            if(index > target){
                end = index - 1;
            }
            else{
                start = index + 1;
            }
            index = partition(nums, start, end);
        }
        return nums[index];
    }
}
