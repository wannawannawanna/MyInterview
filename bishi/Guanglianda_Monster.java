/*
在一个横向2D模板游戏中，所有的怪物都是在x轴上，每个怪物有两个属性和Hp，分别代表位置和血量，玩家控制的角色有一个AOE（范围攻击）技能，玩家每次释放技能可以选择一个位置，技能会对[x-y,x+y]
范围内的所有怪物造成一点伤害，请问玩家最少需要使用多少次技能，才能杀死所有怪物，怪物血量清0即视为被杀死
输入：
3 5  //3是怪物数量，5是攻击范围
1 10  //1的位置有一个怪物，且他的血量为10
10 5
22 3
输出13  //最少使用技能的次数


*/


package bishi;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Collections;
class Monster{
	int left;   //题的关键就是用left和right
	int right;
	int blood;
	public Monster(int left, int right, int blood) {  
		this.left = left;
		this.right = right;
		this.blood = blood;
	}
}
public class GuangliandaMonster {
	public static int process(LinkedList<Monster> data) {
		Collections.sort(data, (o1, o2) -> o1.left - o2.left);  //根据left从小到大排序
		int res = 0;
		int pre = 0;
		int leftBound = data.get(0).left;
		int rightBound = data.get(0).right;
		for(Monster m : data) {
			if(m.left <= rightBound) {  //考虑两个范围有没有重叠，重叠的话，就取其中血量大的就行，没重叠就直接将血量加上去
				pre = Math.max(pre, m.blood);
			}
			else {
				res += pre;
				leftBound = m.left;
				rightBound = m.right;
				pre = m.blood;
			}
		}
		res += pre;
		return res;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int monster = input.nextInt();
		int area = input.nextInt();
		LinkedList<Monster> data = new LinkedList<Monster>();
		while(monster != 0) {
			int pos = input.nextInt();
			int blood = input.nextInt();
			Monster m = new Monster(pos - area, pos + area, blood);
			data.add(m);
			monster--;
		}
		int res = process(data);
		System.out.println(res);
	}
}
