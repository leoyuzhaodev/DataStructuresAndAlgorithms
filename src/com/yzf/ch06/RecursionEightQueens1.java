package com.yzf.ch06;

import java.util.concurrent.ForkJoinPool;

/**
 * @description:八皇后问题
 * @author:leo_yuzhao
 * @date:2020/10/30
 */
public class RecursionEightQueens1 {

    // 地图
    private static int[][] map = new int[8][8];

    // 成功计数
    private static int count = 0;

    public static void main(String[] args) {
        playChess(1);
        System.out.println(count);
    }

    public static void playChess(int row) {
        if (row > 8) {
            count++;
            System.out.println("--------------第" + count + "种摆法--------------");
            printMap();
        } else {
            for (int col = 0; col < 8; col++) {
                if (isRight(row, col)) {
                    map[row - 1][col] = 1;
                    playChess(row + 1);
                }
                map[row - 1][col] = 0;
            }
        }
    }

    public static boolean isRight(int row, int col) {
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == 1) {
                    // 棋子在同一列上
                    if (col == j) {
                        return false;
                    }
                    // 棋子在同一条斜线上
                    if (Math.abs(row - 1 - i) == Math.abs(col - j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void printMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%s\t", map[i][j] == 1 ? "*" : "=");
            }
            System.out.println();
        }
    }


}
