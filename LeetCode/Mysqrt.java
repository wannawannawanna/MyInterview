//自己实现一个开根号
class Solution {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int middle = 0;
        int ans = 0;
        while(left <= right){
            middle = left + (right - left) / 2;
            if((long)middle * middle > x  ){             
                right = middle - 1;
            }
            else{
                ans = middle;
                left = middle + 1;
            }
        }
        return ans;
    }
}


//牛顿法,比二分效率高
泰勒公式：f(x) = f(x0) + f'(x0)(x - x0),另等式等于0，计算出x = x0 - f(x0)/f'(x0)
开根号等于x^2 - a = 0 ,由上面公式得出，x = (x0 + a/x0)/2

public int Sqrt(int x)
