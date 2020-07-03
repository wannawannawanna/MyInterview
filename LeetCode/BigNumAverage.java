//可能会溢出，但是不能用double，也不能用long

//全是正整数的情况
public int getAvg(int[] nums){
    if(nums == null || nums.length <= 0){
        return 0;  //要有特殊的标志位，否则可能平均数也梦求出来0，没办法区分
    }
    int avg = 0;
    int rest = 0;
    int N = nums.length;  //记录个数
    for(int i = 0; i < N; i++){
        avg += nums[i] / N;
        int temp = nums[i] % N; //新的余数
        if(rest >= N - temp){  //其实就是想看两个余数加起来还能不能被N整除,但是怕溢出所以写了减法
            avg += 1;
            rest -= N - temp;  //其实就是两个余数加起来减N，也是防止溢出写的减法
        }
        else{
            rest += temp;
        }
    }
    return avg;    
}


//有负数的情况
public int getAvg(int[] nums){
    if(nums == null || nums.length <= 0){
        return 0;
    }
    int avg = 0;
    int rest = 0;
    int N = nums.length;
    for(int i = 0; i < N; i++){
        avg += nums[i] / N;
        int temp = nums[i] % N;
        if(temp < 0){  //特殊处理一下，从avg借一位，使得余数成为正数，如果余数是负数，用上面的方法会出错
            avg -= 1;
            temp += N;
        }
        //往下就没变化
        if(rest >= N - temp){
            avg += 1;
            rest -= N - temp;
        }
        else{
            rest += temp;
        }
    }
    return (avg>=0) ? avg : avg+1;  //如果是负数的话，向上取整，因为还有余数，所以就向上取整了
}
