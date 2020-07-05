/*board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.跟剑指的12题：从数组中找字符串路径一样*/
class Solution {
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length <= 0 || word == null || word.length() <= 0){
            return false;
        }
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                visited[i][j] = false;
            }
        }
        int IdxStr = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(existCore(board, word, rows, cols, i, j, visited, IdxStr)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean existCore(char[][] board, String word, int rows, int cols, int row, int col, boolean[][] visited, int IdxStr){
        boolean exist = false;
        if(0 <= row && row < rows && 0 <= col && col < cols && visited[row][col] != true && board[row][col] == word.charAt(IdxStr)){
            visited[row][col] = true;
            IdxStr++;
            if(IdxStr == word.length()){
                return true;
            }
            exist = existCore(board, word, rows, cols, row - 1, col, visited, IdxStr) ||  //找到当前一个字符的情况下，才能进入下一层递归，继续找，没饭到就false，回退继续找，找到就往下找
                existCore(board, word, rows, cols, row + 1, col, visited, IdxStr) ||
                existCore(board, word, rows, cols, row, col - 1, visited, IdxStr) ||
                existCore(board, word, rows, cols, row, col + 1, visited, IdxStr);
            if(!exist){
                visited[row][col] = false;
                IdxStr--;
            }
        }        
        return exist;
    }
}
