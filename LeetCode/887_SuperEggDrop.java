/*  时间复杂度：o(KlogN),   空间复杂度：o(NK),辅助dp数组
你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。

每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。

你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。

每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。

你的目标是确切地知道 F 的值是多少。

无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
*/
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[N+1][K+1];
        //k个鸡蛋和N个楼层
        int m = 0;
        while(dp[m][K] < N){  //m次扔鸡蛋的机会，K个鸡蛋，能检查的最大楼层数dp[m][K]
            m++;
            for(int k = 1; k <= K; k++){
                dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
                //dp[m - 1][k - 1]代表鸡蛋碎了，还剩m-1次扔鸡蛋的机会和k-1个鸡蛋，能检查的最大楼层
                //ddp[m-1][k]代表鸡蛋没碎，还剩m-1次扔鸡蛋的机会和k个鸡蛋，能检查的最大楼层
                //再加上当前这一层楼
            }
        }
        //因为题干是要找到检查N层楼的时候的移动次数（扔鸡蛋的次数），所以当dp[m][K]<N不满足条件的时候退出循环
        return m;
    }
}