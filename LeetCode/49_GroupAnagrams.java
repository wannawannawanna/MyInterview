//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串
/*输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if(strs == null || strs.length <= 0){
            return result;
        }
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for(String s : strs){
            char[] array = s.toCharArray();        //将字符串转换成char array之后排序，然后字母异位词就可以找到了，然后存入同一个key里    
            Arrays.sort(array);
            if(map.containsKey(new String(array))){
                List<String> list = map.get(new String(array));
                list.add(s);
                map.put(new String(array), list);
            }
            else{
                List<String> list = new ArrayList<String>();
                list.add(s);
                map.put(new String(array), list);
            }
        }
        //for(List list : map.values())  //map.values()包含所有的值，对应的还有map.keys()
        //    result.add(list);
        //return result;
        return new ArrayList<>(map.values());
    }
}
