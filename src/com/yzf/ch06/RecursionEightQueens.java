package com.yzf.ch06;

import java.util.Arrays;

/**
 * @description:递归解决八皇后问题
 * @author:leo_yuzhao
 * @date:2020/10/17
 */
public class RecursionEightQueens {

    private int max = 8;
    private int[] array = new int[max];
    private int count = 0;

    public static void main(String[] args) {
        RecursionEightQueens recursionEightQueens = new RecursionEightQueens();
        recursionEightQueens.check(0);
        System.out.println("共：" + recursionEightQueens.count + ",种摆放方式");
    }

    public void check(int n) {
        if (n == 8) {
            // n == 8：表明找到一种排列方式
            printResult();
            count++;
            return;
        } else {
            for (int i = 0; i < max; i++) {
                array[n] = i;
                if (judge(n)) {
                    check(n + 1);
                }
                // n = 0  [0] {1} {2} {3} {4} {5} {6} {7}
                // n = 1   0   1  [2] {3} {4} {5} {6} {7}
                // n = 2   0   1   2   3  [4] {5} {6} {7}
            }
        }
    }

    /**
     * n 表示第 n 个皇后
     * 判断当前落子是否符合规则
     *
     * @param n
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(i - n) == Math.abs(array[i] - array[n])) {
                return false;
            }
        }
        return true;
    }

    // 打印棋盘
    private void printResult() {
        int chessboard[][] = new int[max][max];
        for (int i = 0; i < array.length; i++) {
            chessboard[i][array[i]] = 7;
        }
        System.out.println("-----------------------------");
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                System.out.print(chessboard[i][j] + " ");
            }
            System.out.println();
        }

    }

}
