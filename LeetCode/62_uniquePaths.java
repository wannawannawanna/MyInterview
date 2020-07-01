//有m*n的矩阵，机器人从0,0位置开始走，走到m-1,n-1位置上，总共有几种走法，
//空间复杂度o(m*n)
//动态规划，在第一行和第一列都是在边上，所以到每个位置上的走法都只有一个；其他位置上,令 dp[i][j] 是到达 i, j 最多路径,dp[i][j] = dp[i-1][j] + dp[i][j-1]
class Solution {
    public int uniquePaths(int m, int n) {
        if(m <= 0 || n <= 0){
            return 0;
        }
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++){
            dp[0][j] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    
}
