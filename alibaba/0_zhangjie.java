/*
Dijkstra算法，最短路径算法的实现实际上是将图上所有点分为两个集合，一个是未加入的点的集合A，一个是已加入的点的集合B，一开始只有起点s在集合中，每次遍历所有点都会选出路径最短的点加入集合B中，
并更新最短路径，当集合B中找到目标点或者集合A中没有点时，将跳出循环。
时间复杂度o(n^2)

*/
package bishi;
import java.util.*;
public class Dijkstra {
	public static void Dijkstra(int[][] matrix, int start, int terminal) {  //matrix是邻接矩阵，
		boolean[] isVisited = new boolean[matrix.length];
		int[] d = new int[matrix.length];
		for(int i = 0; i < matrix.length; i++) {
			isVisited[i] = false; //该点是否被计入，可以理解为判断该点是否已经加入集合B
			d[i] = Integer.MAX_VALUE;
		}
		isVisited[start] = true;
		d[start] = 0;
		int unVisitNode = matrix.length;
		int index = start;
		while(unVisitNode > 0 && !isVisited[terminal]) {
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < d.length; i++) {
				if(min > d[i] && !isVisited[i]) {
					index = i;
					min = d[i];
				}
			}
			for(int i = 0; i < matrix.length; i++) {
				if(matrix[index][i] != Integer.MAX_VALUE && d[index] + matrix[index][i] < d[i]) {
					d[i] = d[index] + matrix[index][i];
				}
			}
			unVisitNode--;
			isVisited[index] = true;
		}
		System.out.println(d[terminal] == Integer.MAX_VALUE ? "none" : d[terminal]);  //有最短路径返回路径长度，无路径返回none
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		int edge = input.nextInt();
		input.nextLine();
		while(input.hasNext()) {
			int[][] matrix = new int[num][num];
			for(int i = 0; i < num; i++) {
				for(int j = 0; j < num; j++) {
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
			for(int i = 0; i < edge; i++) {
				String[] str = input.nextLine().trim().split(" ");
				matrix[Integer.valueOf(str[0]) - 1][Integer.valueOf(str[1]) - 1] = Integer.valueOf(str[2]);
			}
			for(int[] i : matrix) {
				System.out.println(Arrays.toString(i));
			}
			Dijkstra(matrix, 0, 4);  //start为0，代表顶点1， terminal为5，代表顶点6
		}
	}
}


/*
//输入
6 8
1 3 10
1 5 30
1 6 100
2 3 5
3 4 50
4 6 10
5 6 60
5 4 20
输出：
1~1:0
1~2：none
1~3:10
1~4:50
1~5:30
1~6:60
*/



//第二题，两个数组，非常大，比较两个数组是不是相等，要用多线程来实现




//第三题，并发卖票

//第一种解法，用synchronized锁，效率不高
package bishi;
/*
 * 创建线程的两种方式，1.继承Thread类。2.实现Runnable接口（较为常用的方法）实现Runnable的好处，
 * 将线程的任务从线程的子类中分离出来，进行了单独的封装，按照面向对象的思想将任务封装成对象，避免了java单继承的局限性。*/

public class saleTicketBingFa implements Runnable {
	private int ticket = 60;
	long start = 0L;
	public void run() {
		while(true) {
			synchronized(this) { //将多条操作共享数据的线程代码封装起来，当有线程在执行这些代码的时候，其他线程是不可以参与
				//运算的，必须要当前线程把这些代码都执行完后，其他线程才可以参与运算，用同步代码块可以解决这个问题。
				if(ticket == 0) {
					break;
				}
				try {
					Thread.sleep(10);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "*******" + ticket--);
			}
			
		}
		long end = System.currentTimeMillis();

        	System.out.println(end - start + "ms");
	}
	public static void main(String[] args) {		
		saleTicketBingFa st = new saleTicketBingFa();
		
		new Thread(st).start();  //两个线程
		new Thread(st).start();
		
		
	}
}





//第四题，尽可能多的方法反转字符串
