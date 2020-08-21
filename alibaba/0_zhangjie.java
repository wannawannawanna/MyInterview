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

//第一种解法，用synchronized锁，效率不高，但不是按线程顺序交替卖票
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
		for(int i = 0; i < 2; i++){   //两个线程
			new Thread(st).start();  
		}		
	}
}

//第二种方法，用链表加CountDownLatch做，也不是按线程顺序交替卖票
package bishi;
/*ConcurrentLinkedQueue:在并发编程中，我们需要使用线程安全的队列，如果我们要实现一个线程安全的队列有两种方式，1.使用阻塞算法，可以用
 * 一个锁（入队或出队用同一把锁），两把锁（入队一个锁，出队一个锁）2.使用非阻塞算法，可以使用循环CAS的方式来实现。ConcurrentLinkedQueue
 * 就是非阻塞的方式实现的线程安全队列。ConcurrentLinkedQueue是一个基于链接节点的无界线程安全队列，采用先进先出的规则对节点进行排序，当我们
 * 添加一个元素的时候会添加到队列的尾部，获取一个元素的时候是返回队头，内部有两个节点，head头结点，tail尾结点，使用item存储入列元素，next指向
 * 下一个元素，offer入列，poll出列，peek返回队头元素，不出队。入队需要完成两件事情，第一是定位出尾结点，第二是使用CAS算法将入队节点设置成
 * 尾结点的next节点，如不成功重试。
 * */
/*CountDownLatch:这个类使一个线程等待其他线程格子执行完毕后再执行，是通过一个计数器count来实现的，计数器的初始值就是线程的数量，每当一个
 * 线程执行完毕后，计数器的值就减1，当计数器的值为0时，表示所有线程都执行完毕，然后再等待的线程就可以恢复了。调用await（）方法的线程会被
 * 挂起，他会等待直到count值为0才继续执行，await（long timeout,TimeUnit unit）是要等待一定时间后count值还没变为0的话就
 * 会继续执行，countDown()将count值减1*/
/*CountDownLatch和CyclicBarrier区别：CountDownLatch是一个计数器，线程完成一个记录一个，计数器递减，只能用一次
 * CyclicBarrier的计数器更像一个阀门，需要所有线程都到达，然后继续执行，计数器递增，提供reset功能，可以多次使用*/
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;

public class saleTicket3 implements Runnable{  //继承Runnable，并重写run（）方法，就可以创建线程了
	private Queue<String> tickets = new ConcurrentLinkedQueue<>();
	saleTicket3(){
		for(int i = 0; i < 100000; i++) {  //票数
			tickets.add("票编号" + i);
		}
	}
	private static StringBuffer sb = new StringBuffer(); //线程安全的
	CountDownLatch latch = new CountDownLatch(10);
	public void run() {
		try {
			latch.await();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		while(true) {
			String s = tickets.poll();
			if(s == null) {
				break;
			}
			else {
				sb.append(Thread.currentThread().getName() + "销售了" + s + "\n");
			}
		}
	}
	public static void main(String[] args) {
		saleTicket3 st = new saleTicket3();
		long start = 0L;
		ArrayList<Thread> list = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Thread th = new Thread(st);
			th.start();
			list.add(th);
			if(i == 9) {
				start = System.currentTimeMillis();
			}
			st.latch.countDown();  //等待所有线程一起开始
		}
		
		//等待所有线程一起结束
		list.forEach(o -> {
			try {
				o.join();  //确定线程何时结束
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		long end = System.currentTimeMillis();
		System.out.println(sb.toString());
		System.out.println(end - start + "ms");
	}
}




//第四题，尽可能多的方法反转字符串




//三个线程，打印数字从0开始
public class ConcurrentPrint implements Runnable{
    private volatile int count = 0;
    public void run() {
    	while(true) {
    		synchronized (this) {
            	try {
            		Thread.sleep(10);
            	}catch(InterruptedException e) {
            		e.printStackTrace();                
                } 
            	System.out.println(Thread.currentThread().getName()+ " " + count);
                count++;
            }
    	}    
    }
 
    public static void main(String[] args) {
    	ConcurrentPrint demo = new ConcurrentPrint();
        for (int i = 1; i <=3; i++) {  //三个线程
            new Thread(demo).start();
        }
    }
}
