//将图片顺时针旋转90度
class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length <= 0){
            return;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        //转至
        for(int i = 0; i < row; i++){
            for(int j = i; j < col; j++){  //转至要注意j的下标，从0开始的话要转回去了
                int temp = matrix[i][j];
                matrix[i][j] =  matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //每一行都翻转
        for(int[] m : matrix){
            for(int i = 0; i < m.length / 2; i++){  //i最后不能等于m.length/2
                swap(m, i, m.length - 1 - i);
            }
        }
        
    }
    public void swap(int[] matrix, int i, int j){
        int temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }
    
}
