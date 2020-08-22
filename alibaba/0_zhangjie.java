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
package bishi;

import java.util.*;

public class reverseString {
	//方法1，使用StringBuilder的reverse（）方法，同样StringBuffer也一样
	public static String reverse1(String str) {
		System.out.println("方法一");
		return new StringBuilder(str).reverse().toString();
	}
	
	//方法2，获得字符串长度，倒序拼接
	public static String reverse2(String str) {
		System.out.println("方法二");
		String  reverse = "";
		for(int i = str.length() - 1; i >= 0; i--) {
			reverse += str.charAt(i);
		}
		return reverse;
	}
	
	//方法三，双指针
	public static String reverse3(String str) {
		System.out.println("方法三");
		char[] ch = str.toCharArray();
		int left = 0;
		int right = str.length() - 1;
		while(left <= right) {
			char temp = ch[left];
			ch[left] = ch[right];
			ch[right] = temp;
			left++;
			right--;
		}
		return new String(ch);
	}
	
	//方法四，二分+递归直到找到前后两个字符，将后面的字符和前面的字符连接起来
	public static String reverse4(String str) {
		System.out.println("方法四");
		int length = str.length();
		if(length <= 1) {
			return str;
		}
		String left  = str.substring(0, length/2);
		String right = str.substring(length/2, length);
		return reverse4(right) + reverse4(left);
	}
	
	//方法五，前序拼接，拼接在前面
	public static String reverse5(String str) {
		System.out.println("方法五");
		String  reverse = "";
		for(int i = 0; i <= str.length() - 1; i++) {
			reverse = str.charAt(i) + reverse;
		}
		return reverse;
	}
	
	//方法六，基于栈，先进后出
	public static String reverse6(String str) {
		System.out.println("方法六");
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < str.length(); i++) {
			stack.add(str.charAt(i));
		}
		String reverse = "";
		while(!stack.isEmpty()) {
			reverse += stack.pop();
		}
		return reverse;
	}
	
	//方法七，使用异或交换字符串
	public static String reverse7(String str) {
		char[] array = str.toCharArray();
		int begin = 0;
		int end = str.length() - 1;
		while(begin < end) {
			array[begin] = (char)(array[begin] ^ array[end]);  //a = a ^ b
			array[end]= (char)(array[end] ^ array[begin]);  //b = b ^ a
			array[begin] = (char)(array[end] ^ array[begin]); //a = b ^ a
			begin++;
			end--;
		}
		return new String(array);  //new String（数组）就可以把数组转成String
	}
	
	public static void main(String[] args) {
		String str = "aruna";
		System.out.println(reverse1(str));
		System.out.println(reverse2(str));
		System.out.println(reverse3(str));
		System.out.println(reverse4(str));
		System.out.println(reverse5(str));
		System.out.println(reverse6(str));
	}
}









//三个线程，打印数字从0开始，但是他不是交替打印
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





//使用两个线程交替打印从1~10的数字，有三种方法
package bishi;
import java.util.*;
import java.util.concurrent.Semaphore;

//第一，基于信号量的方式
//public class ConcurrentPrint{
//	static Semaphore printEven = new Semaphore(1);  //打印偶数，其中参数是允许同时运行的线程数目
//	static Semaphore printOdd = new Semaphore(0);  //打印奇数
//	
//	public static void main(String[] args) throws InterruptedException{
//		PrintEven p1 = new PrintEven();
//		PrintOdd p2 = new PrintOdd();
//		p1.start();
//		p2.start();
//		p1.join();
//		p2.join();
//	}
//	
//	static class PrintEven extends Thread{  //Thread的话extends, Runnable的话implements
//		public void run() {
//			for(int i = 0; i <= 10; i += 2) {
//				try {
//					printEven.acquire();  //获取信号量，没有信号量可用时，进行阻塞
//				}catch(InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(Thread.currentThread().getName() + " " + i);
//				printOdd.release();  //释放信号量
//			}
//		}
//	}
//	
//	static class PrintOdd extends Thread{
//		public void run() {
//			for(int i = 1; i <= 10; i += 2) {
//				try {
//					printOdd.acquire();
//				}catch(InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(Thread.currentThread().getName() + " " + i);
//				printEven.release();
//			}
//		}
//	}
//}


//第二种，基于Lock Condition的方式
//wait（）he notify()方法是Object的方法，而await（）和signal（）方法是接口Condition的方法，Condition这个接口把
//object的wait(0,notify(),notifyAll()分解到了不同的对象中，搭配上任意一种Lock的使用，使得一个对象拥有多个等待集，所以
//await()和signal()的添加，实际上是为我们提供了一种方便的基于同一把锁，实现多个条件的wait()和notify（）操作
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentPrint {
    static Lock lock = new ReentrantLock();
    static Condition printOdd = lock.newCondition(); //打印奇数
    static Condition printEven = lock.newCondition();  //打印偶数
    static int point = 0;  //0的时候打印偶数，1的时候打印奇数
    public static void main(String[] args) throws InterruptedException{
    	ConcurrentPrint.PrintEven p1 = new ConcurrentPrint.PrintEven();
    	ConcurrentPrint.PrintOdd p2 = new ConcurrentPrint.PrintOdd();
    	p1.start();
    	p2.start();
    	p1.join();
    	p2.join();
    }
    
    static class PrintEven extends Thread{
    	public void run() {
    		for(int i = 0; i <= 10; i += 2) {
    			lock.lock();
    			try {
    				while(point != 0) {
    					printEven.await();
    				}
    				System.out.println(Thread.currentThread().getName() + " " + i);
    				point = 1;
    				printOdd.signalAll();
    			}catch(InterruptedException e) {
    				e.printStackTrace();
    			}finally {
    				lock.unlock();
    			}
    		}
    	}
    }
    
    static class PrintOdd extends Thread{
    	public void run() {
    		for(int i = 1; i <= 10; i += 2) {
    			lock.lock();
    			try {
    				while(point != 1) {
    					printOdd.await();
    				}
    				System.out.println(Thread.currentThread().getName() + " " + i);
    				point = 0;
    				printEven.signalAll();
    			}catch(InterruptedException e) {
    				e.printStackTrace();
    			}finally {
    				lock.unlock();
    			}
    			
    		}
    	}
    }
    
}


////第三种方法，基于notify(),wait(),notifyAll(),join(),yield(),sleep()方法
//wait()和notify()需要搭配synchronized关键字使用，因为这两个操作的目的是基于某种条件，协调多个线程间的运行状态，由于涉及
//到多个线程间基于共享变量的相互通信，必须引入某种同步机制，以确保wait(),notify()操作在线程层面的原子性
//public class ConcurrentPrint implements Runnable{
//	int i = 1;
//	public void run() {
//		while(true) {
//			synchronized(this) {  //注意，这里指代的是ConcurrentPrint，使用的Runnable implements方法，
//				//若是Thread类的方法，慎用this
//				notify();	//唤醒另外一个线程，注意是this的方法，而不是Thread			
//				try {
////					Thread.currentThread();
//					Thread.sleep(10);  //使其休眠10毫秒
//				}catch(InterruptedException e) {
//					e.printStackTrace();
//				}
//				if(i <= 100) {
//					System.out.println(Thread.currentThread().getName() + ":" + i);
//					i++;
//					try {
//						wait(); //放弃资源，等待
//					}catch(InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//	}
//	public static void main(String[] args) {
//		ConcurrentPrint cp = new ConcurrentPrint();
//		Thread t1 = new Thread(cp);
//		Thread t2 = new Thread(cp);
////		t1.setName("线程1");
////		t2.setName("线程2");
//		t1.start();
//		t2.start();
//		t1.join();
//		t2.join();
//	}
//}

