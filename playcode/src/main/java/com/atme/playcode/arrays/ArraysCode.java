package com.atme.playcode.arrays;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 数组相关
 *
 * @author JClearLove
 * @date 2021/05/07 09:59
 */

public class ArraysCode {

    public int searchInsert(int[] nums, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        int index = Collections.binarySearch(list, target);
        return index < 0 ? Math.abs(index + 1) : index;
    }

    public int removeElement(int[] nums, int val) {
        // 删除元素
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;
                size--;
            }
        }
        return size;
    }

    //快慢指针

    public int removeElementByTwoIndexes(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }





    private int[] ints;

    @Before
    public void init() {
        ints = new int[]{1, 3, 5, 6};
    }

    @Test
    public void testInsert() {
        System.out.println("searchInsert(ints,3) = " + searchInsert(ints, 4));
    }

    @Test
    public void testRemoveElements() {
        System.out.println("removeElement(ints, 3) = " + removeElement(ints, 3));
    }


}

