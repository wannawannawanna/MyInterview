package Ali;

public class MaximumSubArray {
	static boolean Invalid = false;
	public static int maximumSubArray(int[] array) {
		int length = array.length;
		if(array == null || length <= 0) {
			Invalid = true;
			return 0;
		} 
		int curSum = 0;   
		int greatestSum = array[0];
		for(int i = 0; i < length; i++) {
			if(curSum > 0) {  //只有当前sum大于0的时候，才能带来增益，
				curSum += array[i];
			}
			else {
				curSum = array[i];   //否则就把当前值作为curSum
			}
			greatestSum = Math.max(curSum, greatestSum); //返回curSum和greatestSum中比较大的值

		}
		return greatestSum;		
	}
	public static void main(String[] args) {
		int[] array = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maximumSubArray(array));
	}
}
