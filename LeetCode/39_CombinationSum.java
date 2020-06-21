/*  回溯法+剪枝
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.Arrays;
import java.util.ArrayDeque;
class Solution {
     public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length <= 0){
            return result;
        }
         // 排序是为了提前终止搜索
        Arrays.sort(candidates);
        int len = candidates.length;
        int index = 0;
        dfs(candidates, len, target, index, result, new ArrayDeque<>());
        return result;
    }

    /**
     * @param candidates 数组输入
     * @param len        输入数组的长度，冗余变量
     * @param target    剩余数值
     * @param index      本轮搜索的起点下标
     * @param path       从根结点到任意结点的路径
     * @param result        结果集变量
     */
    public void dfs(int[] candidates, int len, int target, int index, 
                       List<List<Integer>> result, Deque<Integer> path){
        if(target == 0){
            //result.add(path);报错，path是deque类型的不能直接存到List里
            result.add(new ArrayList<Integer>(path));
            // 由于 path 全局只使用一份，到叶子结点的时候需要做一个拷贝
            return;
        }
        for(int i = index; i < len; i++){
            if(candidates[i] > target){
                break;              
            }
            path.addLast(candidates[i]);
            dfs(candidates, len, target - candidates[i], i, result, path);
            path.removeLast(); //回溯法，所以把最后一个元素删掉
        }
    }

}
