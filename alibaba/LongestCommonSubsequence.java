/*LeetCode 1143题
对于两个子序列 S1 和 S2，找出它们最长的公共子序列。典型的动态规划，定义一个二维数组 dp 用来存储最长公共子序列的长度，其中 dp[i][j] 表示 S1 的前 i 个字符与 S2 的前 j 个字符
最长公共子序列的长度。分为相等和不相等的情况：
1）相等的时候：dp[i][j] = dp[i - 1][j - 1] + 1
2)不相等的时候：dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]),即得到状态转移方程
*/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() <= 0 || text2.length() <= 0){
            return 0;
        }
        int length1 = text1.length();
        int length2 = text2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];  //为了使下标与长度保持一致
        for(int i = 1; i <= length1; i++){  //因为i是从1开始的，所以要等于length1，也是因为dp数组的下标个长度是一致的
            for(int j = 1; j <= length2; j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){  //s1的第i个和s2的第j个
                    dp[i][j] = dp[i - 1][j - 1] + 1;   //dp[i][j]包括s1的前i个字符和s2的前j个字符
                }
                else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); 
                }
            }
        }
        return dp[length1][length2];  //dp[length1][length2]就是最长子序列长度
    }
}
