//解法1，暴力法，时间复杂度o(N^2)

//解法2，针对有序的数组，可以用双指针,这个解不适用于本题，因为本题要求的是返回原数组下标，排序完之后下标就变了，如果返回两个数字本身的话，这解法就可以
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length < 2){
            return null;
        }
        int[] result = new int[2];
        Arrays.sort(nums);
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while(left < right){
            if(target == nums[left] + nums[right]){
                result[0] = left;
                result[1] = right;
                break;
            }
            else if(target < nums[left] + nums[right]){
                right--;
            }
            else{
                left++;
            }
        }
        return result;
    }
}


//解法3，用哈希表，空间换时间，空间复杂度o(N),时间复杂度o(N)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length < 2) {
        	return null;
        }
        int length = nums.length;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < length; i++) {
        	map.put(nums[i], i);
        }
        for(int i = 0; i < length; i++) {
        	if(map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
        		return new int[] { i, map.get(target - nums[i])};
        	}
        }
        return null;
    }
}
