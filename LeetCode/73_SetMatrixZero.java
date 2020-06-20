//第一次遍历的时候，如果遇到0，那么把当前行和当前列的第一个元素置0，然后第二次遍历的时候看当前行和当前列第一个元素是不是0，如果是就把当前元素置0
// 但是存在问题就是如果第0行或者第0列的元素本来就是0, 那么会在第二次遍历时导致0传播到不应该为0的位置上
// 所以两次遍历都在第1行第一列以后的位置上进行, 这样0就不会传播
// 但是需要单独将第0行和第0列进行置0
class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if(matrix == null || rows <= 0 || cols <= 0){
            return;
        }
        boolean zeroRow = false;
        boolean zeroCol = false;
        // 检查第0行和第0列是否有0
        for(int i = 0; i < cols; i++){
            if(matrix[0][i] == 0){
                zeroRow = true;
                break;
            }
        }
        for(int i = 0; i < rows; i++){
            if(matrix[i][0] == 0){
                zeroCol = true;
                break;
            }
        }
        for(int i = 1; i < rows; i++){
            for(int j = 1; j < cols; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = 1; i < rows; i++){
            for(int j = 1;j < cols; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        // 将第0行和第0列置0
        if(zeroRow){
            for(int i = 0; i < cols; i++){
                matrix[0][i] = 0;
            }
        }
        if(zeroCol){
            for(int i = 0; i < rows; i++){
                matrix[i][0] = 0;
            }
        }
    }
}
