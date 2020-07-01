class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();//记住只有一个ArrayList才能转成List<List>
       // List<List<Integer>> result = new ArrayList<ArrayList<Integer>>();  //不能转型成功
        if(nums == null || nums.length <= 0){
            return result;
        }        
        permuteCore(nums, result, 0);  //从位置0开始
        return result;
    }
    public void permuteCore(int[] nums, List<List<Integer>> result, int begin){
        if(nums == null || nums.length <= 0 || begin < 0 || begin > nums.length - 1){
            return;
        }
        if(begin == nums.length - 1){  
            //将数组中的元素添加到list里面
            List<Integer> temp = new ArrayList<Integer>(nums.length); //初始化链表的长度
            for(int num : nums) {
            	temp.add(num);
            }
            result.add(temp);	
            //result.add(Arrays.asList(nums));   Arrays.asList()函数返回的是List<int[]>,在这里我们要的是List<Integer> 
             
        }
        else{
            for(int i = begin; i < nums.length; i++){
                swap(nums, i, begin);
                permuteCore(nums, result, begin + 1);  //重要：begin+1
                swap(nums, i, begin);
            }
        }
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
