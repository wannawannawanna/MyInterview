/*为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则以其为起始节点开始进行深度优先搜索。在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。

最终岛屿的数量就是我们进行深度优先搜索的次数
时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。

空间复杂度：O(MN),在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到 MN
*/
class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null){
            return 0;
        }
        int rows = grid.length;
        if(rows == 0){return 0;}
        int cols = grid[0].length;
        int count = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i, j, rows, cols);
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid, int i, int j, int rows, int cols){
        if(grid == null || i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j, rows, cols);
        dfs(grid, i + 1, j, rows, cols);
        dfs(grid, i, j - 1, rows, cols);
        dfs(grid, i, j + 1, rows, cols);
   
    }
}
