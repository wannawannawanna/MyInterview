package bishi;
import java.util.*;
class Log{
	int timestamp;
	int id;
	int status;
	Log(int timestamp, int id, int status){
		this.timestamp = timestamp;
		this.id = id;
		this.status = status;
	}
}
public class wangyi_wastetime {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int group = input.nextInt();
			int N = input.nextInt();
			input.nextLine();			
			for(int t = 0; t < group; t++) {
				Stack<Log> stack = new Stack<Log>();
				int[] times = new int[N];
				for(int i = 0; i < N; i++) {
					String[] str = input.nextLine().trim().split(" ");
					Log log = new Log(Integer.valueOf(str[0]), Integer.valueOf(str[1]), Integer.valueOf(str[2]));
					if(Integer.valueOf(str[2]) == 0) {
						stack.add(log);
					}
					else {
						int timeAdded = Integer.valueOf(str[0]) - stack.peek().timestamp;
						times[Integer.valueOf(str[1])] += timeAdded;
						stack.pop();
						if(!stack.isEmpty()) {
							times[stack.peek().id] -= timeAdded;
						}
					}
				}
				int max = Integer.MIN_VALUE;
				int temp = 0;
				for(int i = 0; i < N; i++) {
					if(times[i] > max) {
						max = times[i];
						temp = i;
					}
				}
				System.out.println(temp);	
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
