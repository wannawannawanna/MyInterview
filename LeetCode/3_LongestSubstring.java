//跟剑指offer不同的是，输入的字符串可能不在‘a’~'z'的范围，如空格
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int curLength = 0;
        int maxLength = 0;
        int[] position = new int[128];  //有符号的时候总共128个ASCII码，无符号是256个，char有8个字节，1个字节16位
        for(int i = 0; i < 128; i++){
            position[i] = -1;  //用-1表示这个字符串没有出现过
        }
        for(int i = 0; i < s.length(); i++){
           // int prevIndex = position[s.charAt(i) - 'a'];//这是牛客网的解法，这时候字符串中出现的字符是'a'~'z'
            int prevIndex = position[s.charAt(i)];  //不用相对位置作为下标记录字符是否出现，直接用ascii码作为下标
            if(prevIndex < 0 || i - prevIndex > curLength){   //表示当前字符没出现过或者当前出现跟上次出现的距离比f(i - 1)大，即算f(i - 1)的
            //时候，没有考虑str.charAt(i)这个字符
                curLength++;  //考虑当前出现的位置
            }else{
                curLength = i - prevIndex;  //表示当前出现跟上次出现的距离比f(i - 1)小，所以不考虑当前出现的位置，两次的距离即最长子字符串长度
            }
            position[s.charAt(i)] = i;  //记录当前出现的位置
            if(curLength > maxLength){  //更新maxLength
                maxLength = curLength;
            }
        }
        
        return maxLength;
    }
}
