

package bishi;
import java.util.*;
public class wangyi_wastetime {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int group = input.nextInt();
			int N = input.nextInt();
			input.nextLine();
			HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
			for(int t = 0; t < group; t++) {
				for(int i = 0; i < N; i++) {
					String[] str = input.nextLine().trim().split(" ");
					ArrayList<Integer> array;
					if(map.containsKey(Integer.valueOf(str[1]))) {
						array = map.get(Integer.valueOf(str[1]));	
					}
					else {
						array = new ArrayList<Integer>();
					}
					array.add(Integer.valueOf(str[0]));
					map.put(Integer.valueOf(str[1]), array);
				}
				int max = 0;
				int itself = 0;
				int all = 0;
				int i = 0;
				int ktemp = 0;			
				int pre = 0;
				for(Integer k : map.keySet()) {
					ArrayList<Integer> arr = map.get(k);
					if(i == 0) {
						all = arr.get(1) - arr.get(0);
						i++;
						ktemp = k;
					}
					else {
						int sub = arr.get(1) - arr.get(0);
						itself = all - sub;
						if(itself > max) {
							max = itself;												
							ktemp = pre;
						}
						all = sub;
					}
					pre = k;				
				}
				System.out.println(ktemp);	
			}					
		}
	}
}


/*
 * 
 * 
1  //表示几组数据
8  //表示每组数据中有多少行记录，
1 1 0  //表示事件发生时的时间，事件的id，以及开始或结束的标志信息（0/1.0表示开始，1表示结束）
5 2 0
10 3 0
20 3 1
25 4 0
40 4 1
1000 2 1
2000 1 1
*/
//输出1，从这些事件里面找出一个自身耗时时间最多的一个，比如id为1时的自身耗时：2000-1 - （1000-5） = 1004，所有事件都是这么算的
