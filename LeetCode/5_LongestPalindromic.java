//解法1，中心扩散法，时间复杂度o(N^2),空间复杂度是o(1)
/* 从每一个位置出发，向两边扩散即可。遇到不是回文的时候结束。举个例子，str = acdbbdaastr=acdbbdaa 我们需要寻找从第一个 b（位置为 33）
出发最长回文串为多少。怎么寻找？
首先往左寻找与当期位置相同的字符，直到遇到不相等为止。
然后往右寻找与当期位置相同的字符，直到遇到不相等为止。
最后左右双向扩散，直到左和右不相等*/
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2){
            return s;
        }
        int length = s.length();
        int maxLen = 0;
        int start = 0;
        int left = 0;
        int right = 0;
        int len = 1;
        for(int i = 0; i < length; i++){
            left = i - 1;
            right = i + 1;
            while(left >= 0 && s.charAt(left) == s.charAt(i)){
                len++;
                left--;
            }
            while(right < length && s.charAt(right) == s.charAt(i)){
                len++;
                right++;
            }
            while(left >= 0 && right < length && s.charAt(left) == s.charAt(right)){
                len = len + 2;
                left--;
                right++;
            }
            if(len > maxLen){
                maxLen = len;
                start = left;
            }
            len = 1;
        }
        
        return s.substring(start + 1, start + maxLen + 1);  //end+1之后才能打印出end下标的元素
    }
}


//记住解法2
//解法2，动态规划，时间复杂度o(N^2),空间复杂度o(1)
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2){
            return s;
        }
        int length = s.length();
        int maxLen = 0;
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[length][length];   //默认会初始化成false
        for(int i = length - 1; i >= 0; i--){  //降序的，因为如果s[i] == s[j],我们还要进一步考虑s[i + 1]和s[j - 1]是否相等，只有相等
        //的情况下才能给当前dp[i][j]赋值true，所以要在算下标i之前，i+1确保被计算过
            for(int j = i; j < length; j++){   //升序，同理
                if(s.charAt(i) == s.charAt(j)){
                	if(j - i < 2 || dp[i + 1][j - 1] == true) {  //如果j-i<2,那么s[i]和s[j]是回转数组中最里面的两个元素
                		dp[i][j] = true;
                	}
                }
                if(dp[i][j] == true && j - i + 1 > maxLen){  //是回转字符串的前提下，看两个之间的距离是否比当前最大回转字符串长度长，长的话替换
                    maxLen = j - i + 1;
                    start = i;  //记录回转字符串的起始位置
                    end = j;  //记录终止位置
                }
            }
        }
        return s.substring(start, end + 1);  //end+1之后才能打印出end下标的元素
    }
}
