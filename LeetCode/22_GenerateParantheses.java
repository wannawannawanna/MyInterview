/*回溯法，
我们可以只在序列仍然保持有效时才添加 '(' or ')'，我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点，
如果左括号数量不大于 n，我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。*/
import java.util.ArrayList;
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if(n <= 0){
            return result;
        }
        StringBuilder cur = new StringBuilder();  //字符串
        core(result, cur, 0, 0, n);
        return result;
    }
    public void core(List<String> result, StringBuilder cur, int open, int close, int max){
        if(cur.length() == 2 * max){  //左右两个括号，所以乘2
            result.add(cur.toString()); //将字符串添加到list中
            return;  //终止循环
        }
        if(open < max){  //左括号比n小，n是括号对数字
            cur.append('(');
            core(result, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if(close < open){  //右括号比左括号小
            cur.append(')');
            core(result, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
