package bishi;
/*已知有一堆人排成M行N列，（M，N均大于等于10小于等于1000），现在从这群人中挑选一些人出来。在这个M行N列的队伍中，
 * 每个人对应一个坐标，从最左上角的（0,0）到右下角的（M-1，N-1）。这些人还会进行一次报数，报数顺序是按照类似蜗牛壳形状的顺时针方向由外圈像内圈报数，
 * 当报数的个位数为7且十位数为奇数的人出列，按报数顺序输出这些人的坐标
 
 思路：用四个指针，顺时针旋转，报数，个位数为7且十位数为奇数时添加到结果集里面
 */
import java.util.*;
public class huawei1 {
	public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        int col = input.nextInt();
        int[][] matrix = new int[row][col];
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
        System.out.println(result.toString().replace(" ", ""));  //集合可以用***.toString()
//        int[] array = {1,2,3,4};
//        System.out.println(Arrays.toString(array));  //数组用Arrays.toString()
    }
}
