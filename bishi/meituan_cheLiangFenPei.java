package bishi;
/*某个时刻A和B两地都向该公司提交了租车的订单，分别需要a和b辆汽车，此时，公司的所有车辆都在外运营，小团分别计算了每辆车前往A地和B地完成订单的利润，计算，使得利润最大化，
每辆车最多只能完成一地的任务
输入：
5 2 2
4 2
3 3
5 4
5 3
1 5
输出：
18
*/

import java.util.*;
class MyComparator implements Comparator<int[]>{
	public int compare(int[] i1, int[] i2) {
		if(i1[0] > i2[0]) {
			return -1;
		}
		else if(i1[0] == i2[0]) {
			if(i2[1] > i1[1]) {
				return -1;
			}
			else if(i2[1] == i1[1]) {
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}
}

class MyComparator1 implements Comparator<int[]>{
	public int compare(int[] i1, int[] i2) {
		if(i1[0]-i1[1] > i2[0]-i2[1]) {
			return -1;
		}
		else if(i1[0]-i1[1] == i2[0]-i2[1]) {
			return 0;
		}
		else {
			return 1;
		}
	}
}

public class meituan3{
	
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int a = input.nextInt();
        int b = input.nextInt();
        int[][] array = new int[n][2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                array[i][j] = input.nextInt();
            }
        }
        Arrays.sort(array,new MyComparator());
        //按照x降序排序，再按照y升序排，然后从两边取
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < 2; j++){
//            	System.out.println(array[i][j]);
//            }
//        }
        int res = 0; 
        for(int i = 0; i < a; i++) {
        	res += array[i][0];
        }
        for(int i = 0; i < b; i++) {
        	res += array[n - 1 - i][1];
        }
        System.out.println(res);
    }
}
