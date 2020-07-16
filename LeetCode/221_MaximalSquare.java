//题干：在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
// 动态规划
// dp[i][j]表示以i,j为右下角的最大的正方形的边长是多少
// dp[i][j]的左侧、上侧、左上侧的最小值为a, 则i,j处构成的正方形边长为a+1
// dp[i][j] = min(dp[左侧], dp[上侧], dp[左上侧]) + 1
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0){
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSide = 0;
        int[][] dp = new int[rows + 1][cols + 1];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] == '1'){
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i+1][j], dp[i][j+1]), dp[i][j]) + 1;
                    maxSide = Math.max(maxSide, dp[i + 1][j + 1]);
                }
            }
        }   
        return maxSide * maxSide;
    }
}
