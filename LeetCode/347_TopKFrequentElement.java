//输入nums=[1,1,1,2,2,3],k=2 输出[1,2]
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        if(nums == null || nums.length <= 0 || k <= 0){
            return result;
        }
        //统计每个数字出现的频次
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i]) + 1);
            }
            else{
                map.put(nums[i], 1);
            }           
        }
        List<Integer>[] tmp = new List[nums.length + 1]; //数组中存放list，因为不同数字的频次可能相同，因此，tmp同一个位置可能放的数字个数不同
        for (int key : map.keySet()) {  //键的集合
            // 定义 i 来接收每个元素的频率值
            int i = map.get(key);//在下标为频率值的位置存入key
            if (tmp[i] == null) {
                tmp[i] = new ArrayList();
            }
            // 将对应频率的元素放入以频率为下标的数组中
            tmp[i].add(key);
        }

        // 逆向找出前 k 高频率的元素
        List<Integer> list = new ArrayList<>();
        int size = tmp.length;
        for(int i = size - 1; i >= 0 && list.size() < k; i--){  //时刻判断是否存进去k个元素
            if (tmp[i] == null) {
                continue;
            }
            // 将当前频率下的元素放入结果集 ans 中
            list.addAll(tmp[i]);      
        }
        for (int i = 0; i < list.size(); i++){  //list to array
            result[i] = list.get(i);
        }
        return result;
    }
}
