package com.yzf.ch06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:递归：解决迷宫问题
 * @author:leo_yuzhao
 * @date:2020/10/16
 */
public class RecursionDemoMiGong {

    private static int[] strategy;
    private static List<String> path;

    public static void main(String[] args) {
        // resolveMiGongProblem();
        selectShortestPath();
    }

    /**
     * 解决迷宫问题
     */
    private static void resolveMiGongProblem() {
        // 1，初始化地图
        int row = 8;
        int col = 7;
        int map[][] = new int[row][col];

        // 2，建墙
        for (int i = 0; i < col; i++) {
            // 上墙
            map[0][i] = 1;
            // 下墙
            map[row - 1][i] = 1;
        }
        for (int i = 1; i < row - 1; i++) {
            // 左墙
            map[i][0] = 1;
            // 右墙
            map[i][col - 1] = 1;
        }
        map[2][1] = 1;
        map[2][2] = 1;
        map[2][3] = 1;
        System.out.println("---迷宫地图---");
        showMap(map);
        // 3，递归解决迷宫问题
        boolean flag = explore(1, 1, map);

        // 4，打印结果
        if (flag) {
            System.out.println("找到了出路:");
            showMap(map);
        } else {
            System.out.println("未找到出路:");
            showMap(map);
        }
    }

    /**
     * 选择最短路径
     */
    public static void selectShortestPath() {

        // 初始化策略
        int array[][] = new int[][]
                {
                        {1, 2, 3, 4},
                        {1, 2, 4, 3},
                        {1, 3, 2, 4},
                        {1, 3, 4, 2},
                        {1, 4, 2, 3},
                        {1, 4, 3, 2}
                };

        for (int i = 0; i < 6; i++) {
            System.out.println("---------------------------------");
            // 选定策略
            strategy = array[i];
            printStrategy(strategy);

            // 初始化地图
            int map[][] = initMap(8, 7);
            System.out.println("---迷宫地图---");
            System.out.println("row：" + 8);
            System.out.println("col：" + 7);
            showMap(map);

            // 初始化路径记录器
            path = new ArrayList<>();

            // 寻找路径
            boolean flag = explore1(1, 1, map);
            System.out.println("---显示路径---");
            System.out.println("是否找到路径：" + (flag ? "是" : "否"));
            showMap(map);

            // 打印路径
            System.out.println("路径大小：" + path.size() + ",路径详情：" + path.toString());
        }

    }

    public static void printStrategy(int[] strategy) {
        String temp[] = new String[4];
        for (int i = 0; i < strategy.length; i++) {

            switch (strategy[i]) {
                // 上
                case 1:
                    temp[i] = "上";
                    break;
                // 下
                case 2:
                    temp[i] = "下";
                    break;
                //左
                case 3:
                    temp[i] = "左";
                    break;
                //右
                default:
                    temp[i] = "右";
                    break;
            }
        }
        System.out.println("策略：" + Arrays.toString(temp));

    }


    /**
     * 1 标识墙
     * 2 标识通路
     * 3 标识死路
     * 递归的结束条件：当终点被标识为 2 时说明已经找到走出迷宫的方法
     * 找路定义策略：上 -> 下 -> 左 -> 右
     *
     * @param i   起点横坐标
     * @param j   起点纵坐标
     * @param map 地图
     * @return
     */
    public static boolean explore(int i, int j, int map[][]) {

        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {

                // 假设此路可通
                map[i][j] = 2;
                // 按照策略找路
                if (explore(i - 1, j, map)) {
                    // 上
                    return true;
                } else if (explore(i + 1, j, map)) {
                    // 下
                    return true;
                } else if (explore(i, j - 1, map)) {
                    // 左
                    return true;
                } else if (explore(i, j + 1, map)) {
                    // 右
                    return true;
                } else {
                    // 此路不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // map[i][j]=1(墙)/2(已经走过)/3(此路不通)
                return false;
            }
        }
    }


    /**
     * 1 标识墙
     * 2 标识通路
     * 3 标识死路
     * 递归的结束条件：当终点被标识为 2 时说明已经找到走出迷宫的方法
     * 找路定义策略：上 -> 下 -> 左 -> 右
     *
     * @param i   起点横坐标
     * @param j   起点纵坐标
     * @param map 地图
     * @return
     */
    public static boolean explore1(int i, int j, int map[][]) {

        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                // 假设此路可通
                map[i][j] = 2;
                // 路径记录器
                path.add("(" + i + "," + j + ")");
                // 按照策略找路
                if (selectStrategy(strategy[0], i, j, map)) {
                    return true;
                } else if (selectStrategy(strategy[1], i, j, map)) {
                    return true;
                } else if (selectStrategy(strategy[2], i, j, map)) {
                    return true;
                } else if (selectStrategy(strategy[3], i, j, map)) {
                    return true;
                } else {
                    // 此路不通
                    path.remove(path.size() - 1);
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // map[i][j]=1(墙)/2(已经走过)/3(此路不通)
                return false;
            }
        }
    }

    /**
     * 选择策略执行
     *
     * @param select
     * @param i
     * @param j
     * @param map
     * @return
     */
    public static boolean selectStrategy(int select, int i, int j, int map[][]) {
        switch (select) {
            // 上
            case 1:
                return explore1(i - 1, j, map);
            // 下
            case 2:
                return explore1(i + 1, j, map);
            //左
            case 3:
                return explore1(i, j - 1, map);
            //右
            default:
                return explore1(i, j + 1, map);
        }
    }

    /**
     * 初始化地图
     *
     * @param row
     * @param col
     * @return
     */
    public static int[][] initMap(int row, int col) {
        // 1，初始化地图
        int map[][] = new int[row][col];

        // 2，建墙
        for (int i = 0; i < col; i++) {
            // 上墙
            map[0][i] = 1;
            // 下墙
            map[row - 1][i] = 1;
        }
        for (int i = 1; i < row - 1; i++) {
            // 左墙
            map[i][0] = 1;
            // 右墙
            map[i][col - 1] = 1;
        }
        map[2][1] = 1;
        map[2][2] = 1;
        map[2][3] = 1;
        return map;
    }

    /**
     * 展示地图
     */
    public static void showMap(int[][] map) {
        for (int[] row : map) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

}
