/*操作一，把数组第一个元素放到最后，操作二，把1和2互换，3和4互换依次，*/
package bishi;
import java.util.*;
public class sanliu1 {
	public static int[] permutation(int N, int M, int[] operation) {
		int[] arrays = new int[N];
		for(int i = 0; i < N; i++) {
			arrays[i] = i + 1;  //从1到N
		}
		for(int i = 0; i < M; i++) {
			if(operation[i] == 1) {
				int temp = arrays[0];
				for(int j = 0; j < N - 1; j++) {
					arrays[j] = arrays[j + 1];
				}
				arrays[N - 1] = temp;
			}
			if(operation[i] == 2) {
				for(int j = 0; ((j < N) && (j + 1) < N); j += 2) {
					swap(arrays, j, j + 1);
				}
			}
		}
		return arrays;
	}
	public static void swap(int[] arrays, int i, int j) {
		int temp = arrays[i];
		arrays[i] = arrays[j];
		arrays[j] = temp;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int N = input.nextInt();
			int M = input.nextInt();
			int[] operation = new int[M];
			for(int i = 0; i < M; i++) {
				operation[i] = input.nextInt();
			}
			for(int i : permutation(N, M, operation)) {
				System.out.print(i + " ");
			}
			
		}
	}
}
