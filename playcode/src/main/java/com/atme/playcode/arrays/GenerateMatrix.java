package com.atme.playcode.arrays;

/**
 * TODO
 *
 * @author JClearLove
 * @date 2021/05/07 11:37
 */

public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        // 左闭右开
        int up = 0, down = n - 1, left = 0, right = n - 1, index = 1;
        while (index <= n * n) {
            // 填充上行从左到右
            for (int i = left; i <= right; i++) {
                res[up][i] = index++;
            }
            up++;
            for (int i = up; i <= down; i++) {
                res[i][right] = index++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                res[down][i] = index++;
            }
            down--;
            for (int i = down; i >= up; i--) {
                res[i][left] = index++;
            }
            left++;
        }
        return res;

    }


}

