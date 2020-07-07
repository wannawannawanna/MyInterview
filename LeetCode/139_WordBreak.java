/*单词拆分
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true

dp[i]表示i之前的子字符串（0....i-1）可以拆分成字符串列表里的单词
dp[i]为true, 而且i~j之间又构成了一个word, 则dp[j]为true,然后dp[i~j-1]的元素都是false

我们需要枚举 s[0..i-1]中的分割点 j ，看s[0..j−1] 组成的字符串 s1（默认 j=0 时 s1为空串）和 s[j..i−1] 组成的字符串 s2
是否都合法，如果两个字符串均合法，那么按照定义 s1，s2拼接成的字符串也同样合法


*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<String>(wordDict); //判断一个字符串是否出现在给定字符串列表里，可以用哈希表
        boolean[] dp = new boolean[s.length() + 1];  //有dp[0]代表空串，所以需要字符串长度加1
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++){  //i是从1开始的，所以等于s.length()
            for(int j = 0; j < i; j++){
                if(dp[j] && set.contains(s.substring(j, i))){  //s.substring()是s的子字符串包含j,不包含i
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];  //判断到字符串中最后一个元素，返回看看是不是true
    }
}
