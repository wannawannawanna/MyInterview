/*给定一个数组[1, 3, 5, 2], 每个数字代表连续的一排房子中的金币, 强盗不能连续抢劫两个连续的房子, 问如何才能拿到最多的金币*/

package bishi;

public class jinbi {
	public static int maxJinbi(int[] nums) {
		int length = nums.length;
		if(nums == null || length <= 0) {
			return 0;
		}
		int[] dp = new int[length + 1];
		dp[0] = 0;
		dp[1] = nums[0];
		for(int i = 2; i <= length; i++) {
			dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
		}
		return dp[length];
	}
	public static void main(String[] args) {
		int[] nums = new int[] {1, 3, 5, 2};
		System.out.println(maxJinbi(nums));
	}
}
