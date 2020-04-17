//问题的关键是用双指针，然后双指针的移动是挪小的那一个
class Solution {
    public int maxArea(int[] height) {
        if(height == null || height.length < 2) {
			return 0;
		}
		int length = height.length;
		int MaxValue = 0;
		int left = 0; 
		int right = length - 1;
		while(left < right) {
			int value = (right - left) * Math.min(height[left], height[right]);  //面积=横轴*（相对小的高），因为要盛水，不能溢出 
			if(value > MaxValue) {
				MaxValue = value;
			}
			if(height[left] > height[right]) {
				right--;
			}
			else {
				left++;
			}
		}
		return MaxValue;
    }
}
