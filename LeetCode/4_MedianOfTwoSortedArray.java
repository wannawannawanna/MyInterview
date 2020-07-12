
// 将数组 A, B分成左右两部分:  
// A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1], A的前i个元素  
// B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]， B的前j个元素  
// 1) len(left_part) == len(right_part)  =>  i + j == m - i + n - j (或者: m - i + n - j + 1)  => when n > m, i = 0 ~ m, j = (m + n + 1)/2 - i  
// (兼容 m + n = 5或者4， 因为整型(5+1)/2=3, 4/2=2, 都是正确答案)
// 2) max(left_part) <= min(right_part)  =>  B[j-1] <= A[i] 并且 A[i-1] <= B[j]  
// 则median = (max(left_part) + min(right_part))/2  


// 在搜索循环中，我们只会遇到三种情况：  

// <a> (j == 0 or i == m or B[j-1] <= A[i]) and  
//     (i == 0 or j = n or A[i-1] <= B[j])  
//     说明 i 的值满足要求，停止循环  

// <b> j > 0 and i < m and B[j - 1] > A[i]  
//     说明 i 的值太小， 增加它  

// <c> i > 0 and j < n and A[i - 1] > B[j]  
//     说明 i 的值过大， 减小它

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;       
        if(m > n){  //当i=m时，j=(m+n+1)/2-i>=0,得出n>m,所以如果不满足这个条件需要互换一下
            return findMedianSortedArrays(nums2, nums1);
        }
        int imin = 0; //根据i进行二分搜索，然后由上面的公式j可以推倒出来，所以只需对i二分搜索，imin是下限
        int imax = m; //二分搜索的上限
        int i = 0; //i是nums1数组分成两瓣之后左边的元素个数
        int j = 0; //j是nums2数组分成两瓣之后左边元素个数
        int halflen = (m + n + 1) / 2;
        while(imin <= imax){  //开始二分搜索
            i = (imin + imax) / 2;
            j = halflen - i;
            if(0 < i && j < n && nums1[i - 1] > nums2[j]){
                imax = i - 1; //i-1大了，所以i-1要变小，要往左找
            }
            else if(0 < j && i < m && nums2[j - 1] > nums1[i]){
                imin = i + 1; //j-1大了，所以j要变小，i的变化趋势跟j相反
            }
            else{ //二分查找找到了或者是触发了边界条件
                int maxLeft = 0;
                int minRight = 0;
                if(i == 0){
                    maxLeft = nums2[j - 1];
                }
                else if(j == 0){
                    maxLeft = nums1[i - 1];
                }
                else{
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if((m + n + 1) % 2 == 0){  //m+n是奇数，奇数的时候把多的那一个元素放到了左边，所以返回maxLeft就行
                    return maxLeft;
                }
                
                if(i == m){
                    minRight = nums2[j];
                }
                else if(j == n){
                    minRight = nums1[i];
                }
                else{
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxLeft + minRight) / 2.0;
                
            }
        }
        return -1;
    }
}
