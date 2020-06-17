//排序数组
class Solution {
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) { //通过left来判断是不是找左边界，若是，当target和nums[mid]相等的时候，
            //应该往左找，如果是在找右边界，当相等的时候应该往右找，即进入else
                hi = mid;  //hi=mid-1不是这么更新是因为找左边界的时候如果减1，可能会移出去；找左边界的时候相等的时候low停留在左边界上，之后一直是high在变，直到跟low相等；或者一直找不到target，
                //所以要判断最后找到的左边界上的值是不是target
                //找右边界的时候相等的时候high停留在第一个不相等的元素上，之后一直是low在变，直到跟high相等
            }
            else {
                lo = mid+1;  //找右边界的时候，相等也要往右找，所以会停在第一个不相等的元素上，所以要减一，只有nums[mid]>target的时候进入if,
                //所以high最后停留在第一个不相等的元素上
                //找左边界是只有nums[mid]<target的时候才进入else,所以最后low会停留在左边界上
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) { //判断leftIdx == nums.length是空数组的情况，
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;  //其实上面的代码也可能找不到右边界，但是只要能找到左边界，就有右边界，
        //除非没有左边界，没有左边界的时候会直接return {-1，-1}

        return targetRange;
    }
}
