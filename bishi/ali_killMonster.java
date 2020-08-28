/*
输入：
2  两组数据
3 5  第一是怪物数量，第二个是总的耐久度
4 1  第一个是杀这个怪物的时候需要消耗的耐久度，第二个是杀完这个怪物之后的奖励击杀怪物数
5 1
7 7
2 1  从这儿开始是第二组数据
2 2
4 0
输出：
3 4  第一个是击杀怪物数的最大数，第二个是消耗的最低耐久度
0 0

找使用最少的耐久度击杀最多怪物数量
*/
package bishi;
import java.util.*;
class myComparator implements Comparator<ArrayList>{
	public int compare(ArrayList o1, ArrayList o2) {
		if((int)o1.get(0) < (int)o2.get(0)) {
			return -1;
		}
		else if((int)o1.get(0) == (int)o2.get(0)) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
public class ali1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			ArrayList<ArrayList<Integer>> list = new ArrayList<>();
			int t = input.nextInt();
			while(t >= 0) {
				int cost = 0;
				int numOfMonsterKilled = 0;
				int n = input.nextInt();  //怪物数量
				int m = input.nextInt();  //耐久度
				for(int i = 0; i < n; i++) {
					ArrayList<Integer> temp = new ArrayList<>();
					temp.add(input.nextInt());
					temp.add(input.nextInt());
					list.add(temp);
				}
				Collections.sort(list, new myComparator() );
				int first = -1;
				for(int i = 0; i < n; i++) {
					if(list.get(i).get(1) >= 1) {  //找到第一个花费耐久度最少，且可杀怪物数大于等于1
						first = i;
						break;
					}
				}
				if(list.get(first).get(0) <= m) {
					cost += list.get(first).get(0);
					numOfMonsterKilled++; //如果花费的耐久度比总耐久度小，那么这个怪物就可以被击杀了，所以加1
					for(int i = 0; i < n; i++) {
						numOfMonsterKilled += list.get(i).get(1); //第一刀之后产生的串式击杀的最大数目，
						//所以都加起来，（因为第一刀之后有奖励）,然后这时候还不会使用额外的耐久度,这块是白送的
					}
				}
				if(numOfMonsterKilled >= n) {
					System.out.println(n + " " + cost);
					continue;  //退出本次循环t，
				}
				for(int i = 0; i < n && list.get(i).get(0) + cost <= m && numOfMonsterKilled != n;i++) {
					//第一刀花费的耐久度和当前遍历到的耐久度和必须小于总的耐久度，且击杀的怪物数量不等于总的怪物数量，
					if(i != first) {  //避免重复考虑第一刀
						cost += list.get(i).get(0); //砍完一刀之后，看耐久度还够杀几个怪物，这样算出来的怪物是最多的
						numOfMonsterKilled++;
					}
				}
				System.out.println(numOfMonsterKilled + " " + cost);
			}
		}
	}

}
