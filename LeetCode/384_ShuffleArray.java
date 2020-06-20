import java.util.Random;
import java.util.ArrayList;
class Solution {
    int[] nums;
  //  int[] temp;
    public Solution(int[] nums) {
        this.nums = nums;
       // this.temp = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
//     public int[] shuffle() {  //看起来想o(n),其实o(n^2)
//         if(nums == null || nums.length <= 0){
//             return nums;
//         }
//         ArrayList<Integer> list = new ArrayList<Integer>();
//         for(int i = 0; i < nums.length; i++){
//             list.add(nums[i]);
//         }
//         Random random = new Random();
//         int[] result = new int[nums.length];
//         for(int i = 0; i < nums.length; i++){
//             int index = random.nextInt(list.size());
//             result[i] = list.get(index);
//             list.remove(index);  //remove的时候因为后面的元素会一个个往前挪
//         }
//         return result;
//     }
    
    public int[] shuffle() {  //时间复杂度o(n)
        if(nums == null || nums.length <= 0){
            return nums;
        }
        int[] result = nums.clone();
        Random random = new Random();
        for(int i = 0; i < nums.length; i++){
            int index = random.nextInt(result.length);
            swap(result, i, index);  
        }
        return result;
    }
    public void swap(int[] res, int i, int j){  //swap最好一直这么写，参数直接是result[i],result[j]时总报错
        int temp = res[i];
        res[i] = res[j];
        res[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
