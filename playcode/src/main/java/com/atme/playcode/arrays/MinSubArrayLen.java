package com.atme.playcode.arrays;

/**
 * 滑动窗口
 *
 * @author JClearLove
 * @date 2021/05/07 11:01
 */

public class MinSubArrayLen {

    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        // 滑动窗口数值之和
        int sum = 0;
        // 滑动窗口起始位置
        int i = 0;
        //  窗口长度
        int subLength = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {
                // 2 3 1 2
                // 取子序列的长度
                // 4
                subLength = j - i + 1;
                // 4
                result = Math.min(result, subLength);
                // 3 1 2,i=1
                sum -= nums[i++];

            }
        }
        // 如果result没有被赋值的化，旧返回0，说明没有符合条件的子序列
        return result == Integer.MAX_VALUE ? 0 : result;

    }
}

