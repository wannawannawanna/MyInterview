/*
输入
5  //代表有5行记录
2 10 10  //第一个数代表所浏览的页数  后面的数字是在每一页停留的时长
4 10 15 20 30
5 10 10 10 10 10
6 10 20 20 10 10 10
8 10 120 10 10 10 10 10 10
*/

package bishi;
import java.util.Scanner;
import java.util.ArrayList;

public class HuaweiStudent {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int len = input.nextInt();
		ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < len; i++) {
			int page = input.nextInt();
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int j = 0; j < page; j++) {
				temp.add(input.nextInt());
			}
			array.add(temp);
			
		}
		int[] result = new int[len];
		for(int i = 0; i < len; i++) {
			int sum = 0;
			int cntPage= 0;
			for(int j = 0; j < array.get(i).size(); j++) {
				sum += array.get(i).get(j);
				if(sum <= 60) {
					cntPage++;
				}
				if(sum <= 60 && cntPage > 4) {
					result[i] = 0;
					break;
					
				}
				else if(sum > 60) {
					sum = sum % 60;
					cntPage = 1;
				}
				if(j == array.get(i).size() - 1) {
					result[i] = 1;
				}
			}
		}
		for(int i : result) {
			System.out.println(i);
		}
	}
}

//对每一行的数据加起来然后整除60，就可以知道每一分钟之内看完的页数，如果超过4，那么就返回0，没有超过4，就返回1
