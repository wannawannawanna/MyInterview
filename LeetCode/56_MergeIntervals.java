//合并区间{{2,6},{1,5},{8,10},{1,3},{15,18}};返回[[1,6],[8,10],[15,18]]
package Leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Comparator;

class MyComparator implements Comparator {   //先根据x排序，如果x相等根据y排序，从小到大排序
    public int compare(Object arg0, Object arg1) {
        int[] t1=(int[])arg0;
        int[] t2=(int[])arg1;
        if(t1[0] != t2[0])
//            return t1[0]>t2[0]? 1:-1;  //Arrays.sort里面这一句话，报Timsort异常,因为没有考虑两个元素相等的情况，但是也不能手动考虑，会违背***规则，解决放法就是使用compareTo
            return Integer.valueOf(t1[0]).compareTo(Integer.valueOf(t2[0]));  //compareTo是对包装类型来说的，不能是基本类型，如t1[0].compareTo(t2[0])
        else
           // return t1[1]>t2[1]? 1:-1;
            return Integer.valueOf(t1[1]).compareTo(Integer.valueOf(t2[1]));
    }
}
public class example56 {
    public static int[][] merge(int[][] intervals) {  
    	if(intervals == null || intervals.length <= 0){
            return new int[][]{};  //新建空的二维数组
        }
        LinkedList<int[]> list = new LinkedList<int[]>();
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);  //只根据x排序，非常简洁，从小到大排序
//        Arrays.sort(intervals, new MyComparator());
        for(int[] interval : intervals) {
        	System.out.println(interval[0]);
        	System.out.println(interval[1]);
        }
        int left = intervals[0][0];
        int right = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] > right){
                list.add(new int[]{left,right});   
                left = intervals[i][0];
                right = intervals[i][1];
            }
            else{
                right = Math.max(right, intervals[i][1]);
            }
        }
        list.add(new int[]{left,right});  
//        int[][] res = new int[list.size()][2];//生成结果数组
//        int index = 0;
//        while (!list.isEmpty()) {//遍历集合
//            res[index++] = list.removeFirst();//给数组赋值并删除集合首元素
//        }
//        return res;
        return list.toArray(new int[0][]);  //将list<int[]>转换为int[][],里面的0不能没有，不然报缺少dimension错误
       
        
    }
    public static void main(String[] args) {
    	int[][] arr = {{2,6},{1,5},{8,10},{1,3},{15,18}};
    	int[][] res = merge(arr);
    	for(int i = 0; i < res.length; i++) {
    		for(int j = 0; j < res[0].length; j++) {
    			System.out.println(res[i][j]);
    		}
    	}
    }
}
