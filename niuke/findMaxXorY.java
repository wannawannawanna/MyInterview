/*链接：https://www.nowcoder.com/questionTerminal/ea209d6014c44763a41ee04a11bf2ef1
来源：牛客网

P为给定的二维平面整数点集。定义 P 中某点x，如果x满足 P 中任意点都不在 x 的右上方区域内（横纵坐标都大于x），则称其为“最大的”。求出所有“最大的”点的集合。（所有点的横坐标和纵
坐标都不重复, 坐标轴范围在[0, 1e9) 内）

如下图：实心点为满足条件的点的集合。请实现代码找到集合 P 中的所有 ”最大“ 点的集合并输出。

第一行输入点集的个数 N， 接下来 N 行，每行两个数字代表点的 X 轴和 Y 轴。
对于 50%的数据,  1 <= N <= 10000;
对于 100%的数据, 1 <= N <= 500000;
输出“最大的” 点集合， 按照 X 轴从小到大的方式输出，每行两个数字分别代表点的 X 轴和 Y轴。
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Comparator;
import java.util.Collections;
class Point{
    int x;
    int y;
}
public class Main{
    public static void main(String[] args){
        ArrayList<Point> list = new ArrayList<Point>();
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        for(int i = 0; i < length; i++){
            Point point = new Point();
            point.x = input.nextInt();
            point.y = input.nextInt();
            list.add(point);
        }
        Collections.sort(list, new Comparator<Point>(){
            public int compare(Point p1, Point p2){
                return p2.y - p1.y;  //从大到小排序，就用第二个减第一个
            }
        });
        int tmp = list.get(0).x;
        for(int i = 0; i < length; i++){
            if(tmp <= list.get(i).x){
                tmp = list.get(i).x;
                System.out.println(list.get(i).x + " " + list.get(i).y);
            }
        }
       
    }
}
//牛客只能过80%，超时了
