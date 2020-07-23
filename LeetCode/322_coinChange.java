/*
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1
*/

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];  //dp[i]表示硬币总和为i时，使用的最少数量的硬币
        for(int i = 0; i < dp.length; i++){
            dp[i] = amount + 1;  //因为要找最小值，所以要附最大值，然后最多会用到amount个硬币，所以这就相当于附最大值
        }
        dp[0] = 0;
        for(int i = 1; i < dp.length; i++){
            for(int coin : coins){
                if(i - coin < 0){  //硬币总和比现有硬币面值小，所以不能用，就跳过
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
