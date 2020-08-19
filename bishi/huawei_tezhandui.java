/*
一个公司团建，有M行N列的队形，左上角编号为（0,0），右下角的同事编号为（M - 1， N - 1），从队列左上角开始从1开始报数，最外围的同事按顺时针报数，最外围的同事报完数之后，内圈的同事按顺时针报数。
个位数为7且十位数为奇数的同事被挑选出来作为特战队员，入参是两个大于等于10且小于等于1000的整数，M和N，请按报数顺序输出特战队员的编号列表，非法输入请返回内容为空的数组，
输入： 10 10
输出：[[7,9],[1,1],[8,2],[7,5],[4,4]]
*/
package bishi;
import java.util.*;
public class huawei1 {
	public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        int col = input.nextInt();  
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int count = 0;
        int up = 0;
        int down = row - 1;
        int left = 0;
        int right = col - 1;
        while(true) {
        	for(int i = left; i <= right; i++) {
        		count++;
        		if(count % 10 == 7 && (count/10%10) % 2 == 1) {
        			ArrayList<Integer> temp = new ArrayList<>();
        			temp.add(up);
        			temp.add(i);
        			result.add(temp);
        		}
        	}
        	up++;
        	if(up > down) {
        		break;
        	}
        	for(int i = up; i <= down; i++) {
        		count++;
        		if(count % 10 == 7 && (count/10) % 2 == 1) {
        			ArrayList<Integer> temp = new ArrayList<>();
        			temp.add(i);
        			temp.add(right);
        			result.add(temp);
        		}
        	}
        	right--;
        	if(right < left) {
        		break;
        	}
        	for(int i = right; i >= left; i--) {
        		count++;
        		if(count % 10 == 7 && (count/10) % 2 == 1) {
        			ArrayList<Integer> temp = new ArrayList<>();
        			temp.add(down);
        			temp.add(i);
        			result.add(temp);
        		}
        	}
        	down--;
        	if(up > down) {
        		break;
        	}
        	for(int i = down; i >= up; i--) {
        		count++;
        		if(count % 10 == 7 && (count/10) % 2 == 1) {
        			ArrayList<Integer> temp = new ArrayList<>();
        			temp.add(i);
        			temp.add(left);
        			result.add(temp);
        		}
        	}
        	left++;
        	if(left > right) {
        		break;
        	}
        }     
        System.out.println(result.toString().replace(" ", ""));  //集合可以用***.toString(),这个是因为输出没有空格，但是toString（）这个默认有空格，所以把空格替换了一下
        int[] array = {1,2,3,4};
        System.out.println(Arrays.toString(array));  //数组用Arrays.toString()
    }
}
