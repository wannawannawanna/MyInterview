package bishi;
/*给定二叉树每个节点的深度，计算总共有多少种满足条件的二叉树，根结点深度为0
 * 解题思路：首先要根据输入把二叉树每层节点个数统计好，例如a0,a1,a2,...,aN，总共N层的节点数目，
 * 对于第i层，设改成有ai个节点，则第i+1层有2*ai个位置，因此要从中选择a(i+1)个位置进行放置，即C{(2*ai)}{a(i+1)},然后
 * 把每一层的放置方法数全部乘起来就得到最后的不同形状的二叉树数量。
 * */
import java.util.*;
public class huawei2 {
	public static int Compose(int m, int n) {//公式(m*(m-1)*(m-2)*...*(m-(n-1)))/(n*(n-1)*(n-2)*...*1)
		int m0 = 1;
		int n0 = 1;
		for(int i = 0; i < n; i++) {
			m0 = m0 * (m - i);
			n0 = n0 * (n - i);
		}
		return m0 / n0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int di = 0;
			int N = input.nextInt();  //节点个数
			int[] a = new int[N]; //用来记录每一层的节点个数，每输入一个在对应的层数加1
			int depth = 0;  //depth用来记录二叉树深度（层数），N个节点的二叉树深度小于N
			for(int i = 0; i < N; i++) {
				di = input.nextInt();
				a[di]++;
				if(di > depth) {
					depth = di;
				}
			}
			long count = 1;  //记录总方法数，因为最后是二叉树各层组合数的乘积，所以初始化为1，结果可能比较大，所以long
			int cmn = 0;
			double r0 = Math.pow(10, 9) + 7;
			for(int i = 0; i < depth; i++) {
				count *= Compose(2*a[i],a[i + 1]) % r0;
			}			
			int ans = (int)(count % r0);
			System.out.println(ans);
		}
	}

}
