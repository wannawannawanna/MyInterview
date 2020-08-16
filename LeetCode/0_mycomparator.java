package bishi;
import java.util.*;
class Point{
	int x; 
	int y;
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

//尽量不要写lambda表达式，可能会出错！！！！！！！！！

//二维数组排序
class Comparator1 implements Comparator<int[]>{ //对那种类型对象进行排序，comparator里面就写这个，
	//可以是基本类型
	public int compare(int[] o1, int[] o2) {
		//第一种写法
		if(o1[0] > o2[0]) {  //先按第一维降序排序
			return -1;
		}
		else if(o1[0] == o2[0]){
			if(o2[1] > o1[1]) {  //再按第二位升序排序
				return -1;
			}
			else if(o2[1] == o1[1]){
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
		
		//第二种写法
		//return o1[0] > o2[0] ? -1 : (o2[1] > o1[1] ? -1 : 1);
	}
}


//一维数组排序
class Comparator2 implements Comparator<Integer>{  //包装类型
	public int compare(Integer i1, Integer i2) {
		//方法1
		if(i1 > i2) {
			return -1;
		}
		else if(i1 == i2){
			return 0;
		}
		else {
			return 1;
		}
		//方法2
		//return i1 > i2 ? -1 : 1;
	}
}



public class Mycomparator {
	public static void main(String[] args) {
		
		
		//二维数组排序
		int[][] array = new int[][] {{3,3},{5,4},{5,1},{2,5},{6,3}};
		//int[][] array = new int[][] {{19,13},{15,2},{17,3},{3,8},{19,2}};
		//第一种写法
		//Arrays.sort(array,new Comparator1());
		
		//lambda表达式写法
		Arrays.sort(array, (o1, o2) -> o1[0] > o2[0] ? -1 : (o2[1] > o1[1] ? -1 : 1));
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++) {
				System.out.println("after:" + array[i][j]);
			}
		}
		
		
//		//一维数组排序,降序排序，sort默认是升序排序
//		//注意，要想改变默认的排列顺序，不能使用基本类型（int,double, char）
//		//而要使用它们对应的包装类型
//		Integer[] num = {3,6,1,8,3,2,4};
//		//方法1
////		Arrays.sort(num, new Comparator2());
//		//方法2
//		Arrays.sort(num, (o1, o2) -> o2 - o1);  //
//		for(int i = 0; i < num.length; i++) {
//			System.out.println(num[i]);
//		}
//		
//		
//		
//		
//		
//		//对某个类，按照其属性排序
//		Random rand = new Random();
//		
//		//for(int i = 0; i < 5; i++) {
//		//	point[i] = new Point(rand.nextInt(20), rand.nextInt(20)); //生成0-20范围内的整数
//		//}
		Point[] point = new Point[5];
		point[0] = new Point(19, 13);
		point[1] = new Point(15, 2);
		point[2] = new Point(17, 3);
		point[3] = new Point(3, 8);
		point[4] = new Point(19, 2);
		for(Point p : point) {
			System.out.println("before sort:" + p.x + " " + p.y);
		}
		//尽量不要用lambda表达式，容易出错
		//Arrays.sort(point, (o1,o2) -> o1.x > o2.x ? -1 : (o1.y < o2.y) ? -1 :1);
		Arrays.sort(point, new Comparator3());
		for(Point p : point) {
			System.out.println("after sort :" + p.x + " " + p.y);
		}
		
		
		
		
	}
}
class Comparator3 implements Comparator<Point>{
	public int compare(Point p1, Point p2) {
		if(p1.x > p2.x) {  //按x降序排序，然后按y升序排
			return -1;
		}
		else if(p1.x == p2.x){  //要考虑相等的情况
			if(p1.y < p2.y) {
				return -1;
			}
			else if(p1.y == p2.y) {
				return 0;
			}
			else {
				return 1;
			}
		}else {
			return 1;
		}
	}
}
