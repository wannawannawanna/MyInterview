/*
解数独编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
输入中空白格用 '.' 表示。
最后填充完这个board数组就行

*/
import java.util.*;
public class SudokuSolver {
	public void solveSudoku(char[][] board) {
        if(board == null){
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
        solvePuzzle(board, rows, cols);
        return;
    }
	public boolean solvePuzzle(char[][] board, int rows, int cols) {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(board[i][j] == '.') {
					for(char c = '1'; c <= '9'; c++) {
						if(canPlaceNum(board, i, j, c)) {
							board[i][j] = c;
							if(solvePuzzle(board, rows, cols)) {  //因为board里面元素是变得，所以递归到数组里面没有‘.’就可以了
								return true;
							}
							else {
								board[i][j] = '.';
							}
						}
					}
					return false;  //i,j位置上尝试所有的数字失败，就可以直接返回false了
				}
			}
		}
		return true; //没有‘.’了，填充完毕
	}
	public boolean canPlaceNum(char[][] board, int row, int col, char num) {
		for(int i = 0; i < 9; i++) {
			if(board[i][col] != '.' && board[i][col] == num) {
				return false;
			}
			if(board[row][i] != '.' && board[row][i] == num) {
				return false;
			}
			if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' &&  //row：6~8，col:6~8,i:0~8,可以组成最右下角的3*3的格子
					board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) {
				return false;
			}
		}
		return true;
	}
}
