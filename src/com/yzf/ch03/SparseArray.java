package com.yzf.ch03;

/**
 * @description:稀疏数组测试
 * @author:leo_yuzhao
 * @date:2020/9/23
 */
public class SparseArray {
    public static void main(String[] args) {
        int row = 10;
        int col = 10;
        int myArray[][] = new int[row][col];
        myArray[2][3] = 1;
        myArray[3][4] = 2;
        System.out.println("棋盘：");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("%d\t", myArray[i][j]);
            }
            System.out.println();
        }
        System.out.println("存盘：");
        int[][] sparseArray = getSparseArray(myArray, row, col);
        for (int tempArray[] : sparseArray) {
            for (int temp : tempArray) {
                System.out.printf("%d\t", temp);
            }
            System.out.println();
        }
        System.out.println("复盘：");
        int[][] arrayFromSparse = getArrayFromSparse(sparseArray);
        for (int tempArray[] : arrayFromSparse) {
            for (int temp : tempArray) {
                System.out.printf("%d\t", temp);
            }
            System.out.println();
        }
    }

    // 1,将棋盘转化为稀疏数组
    public static int[][] getSparseArray(int array[][], int row, int col) {

        // 1,遍历数组获取非0值的个数
        int sum = 0;
        for (int tempArray[] : array) {
            for (int temp : tempArray) {
                if (temp != 0) {
                    sum++;
                }
            }
        }
        // 2,初始化稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = sum;

        // 3,保存非0值的详细信息
        int count = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (array[i][j] != 0) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                    count++;
                }
            }
        }

        return sparseArray;
    }

    // 2,将稀疏数组转化为棋盘
    public static int[][] getArrayFromSparse(int sparseArray[][]) {

        // 1,根据稀疏数组的第一行数据初始化棋盘
        int array[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        // 2,遍历稀疏数组复盘
        for (int i = 1; i < sparseArray.length; i++) {
            array[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        return array;
    }

}
