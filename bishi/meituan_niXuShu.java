//给定一个数字，从里面找出逆序对个数，一个数字的逆序数是他的4倍，这样就可以叫逆序对，题目的定义
package bishi;
import java.util.*;
public class meituan1 {
	public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int count = 0;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 1; i <= num; i++){
            if(i * 4 <= num && i * 4 == reverse(i)){  //应该判断i*4小于num
                count++;
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(reverse(i));
                list.add(temp);
            }
        }
        System.out.println(count);
        for(ArrayList<Integer> a : list) {
        	System.out.println(a.get(0) + " " + a.get(1));
        }
    }
    public static int reverse(int num){      
        int ans = 0;
        while(num > 0){
            ans = 10 * ans + (num % 10);           
            num = num / 10;
        }
        return ans;
    }
}
