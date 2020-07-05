/*Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
];组合加一个空的数组就可以了*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length <= 0){
            return result;
        }
        int length = nums.length;
        int n = 1 << length;  //2^n  组合总共有2^n-1个
        result.add(new ArrayList<Integer>());
        for(int i = 1; i < n; i++){  //所以只算1~2^n-1
            List<Integer> temp = new ArrayList<Integer>();
            for(int j = 0; j < length; j++){
                if((i & (1 << j)) != 0){ //如果j位上是1，就把所有j找出来就可以了
                    temp.add(nums[j]);  //把j位上的添加到list里面就行
                }
            }
            result.add(temp);
        }
        return result;
    }
}
