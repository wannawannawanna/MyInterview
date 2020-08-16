/*输入，求出旅游次数，一次旅行的路线一定是闭环，一个闭环算一次旅行
6
"beijing" "nanjing"
"nanjing" "guangzhou"
"guangzhou" "shanghai"
"shanghai" "beijing"
"fuzhou" "beijing"
"beijing" "fuzhou"
*/
//输出2
package bishi;
import java.util.*;

public class meituan2 {
	public static void main(String[] args){
        Stack<String> stack = new Stack<String>();
        int res = 0;
        Scanner input = new Scanner(System.in);
        int len = input.nextInt();
        input.nextLine();
        for(int i = 0; i < len; i++){
            String[] str = input.nextLine().trim().split(" ");
            if(stack.isEmpty()){
                res++;
                stack.push(str[0]);
                if(stack.peek() .equals( str[1])){  //字符串比较是否相等
                    stack.pop();
                }
                else{
                    stack.push(str[1]);
                }
            }
            else{
                if(stack.peek() .equals( str[0])){
                    stack.pop();
                }
                else{
                    stack.push(str[0]);
                }
                if(stack.peek() .equals( str[1])){
                    stack.pop();
                }
                else{
                    stack.push(str[1]);
                }
            }
        }
        System.out.println(res);
    }
}
