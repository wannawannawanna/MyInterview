//电话号码，2~9代表的字母的组合，如字符串是“23”，返回“ad,ae,af,bd,be,bf,cd,ce,cf”
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if(digits == null || digits.length() <= 0) {
        	return result;
        }
        result.add("");  //为了防止最开始的时候获取result.get(0)不出错
        String[] mapping = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"}; //mapping的第一二个元素是什么都行，不会访问第一二元素
        for(int i = 0; i < digits.length(); i++) { //针对digits中的每一个字符获取他代表的字符串，然后与result中的元素进行组合
        	List<String> temp = new ArrayList<String>();
        	String character = mapping[digits.charAt(i) - '0'];
        	for(int j = 0; j < character.length(); j++) {
        		for(int k = 0; k < result.size(); k++) {
        			temp.add(result.get(k) + character.charAt(j));
        		}
        	}
        	result = temp;
        }
        return result;
    }
}


