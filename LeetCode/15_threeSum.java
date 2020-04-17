//时间复杂度o(N^2),空间复杂度o(1)
import java.util.ArrayList;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(nums == null || nums.length < 3) {
			  return result;
		}
		Arrays.sort(nums);  //先排序，然后用双指针
		int length = nums.length;
		for(int i = 0; i < length - 2; i++) {
        if (nums[i] > 0) {  //排序后如果第一个元素大于0就直接break
            break;
        }
        if (i != 0 && nums[i] == nums[i-1]) {  //如果i有重复的就跳过，找下一个i
            continue;
        }
            
			int left = i + 1; //找到i之后，用双指针夹逼，从后面的元素中找
			int right = length - 1;
			while(left < right) {
				int sum = nums[left] + nums[right];
				if(sum == 0 - nums[i]) {					
					result.add(Arrays.asList(nums[i],nums[left],nums[right]));  //每次添加一个list
					while (left < right && nums[left] == nums[left + 1]) {  //去重
						++left;
					}
					// skip duplicate number
					while (left < right && nums[right] == nums[right - 1]) {  //去重
						--right;
					}
					left++;  //往左往右夹逼继续找
					right--;
				}
				else if(sum < 0 - nums[i]) {
					left++;  //如果合比0小，left要找大的值
				}
				else {
					right--;  //如果三数之和大于0，right要找小的值
				}
			}
			
		}
		return result;
    }
}
