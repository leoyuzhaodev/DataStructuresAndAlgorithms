package com.yzf.ch07;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @description:希尔排序
 * @author:leo_yuzhao
 * @date:2020/10/20
 */
public class ShellSort {
    public static void main(String[] args) {
        //testCorrect();
        testSpeed();
    }

    public static void testCorrect() {
        int array[] = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前：" + Arrays.toString(array));
        shellSortByChange(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void testSpeed() {
        int array[] = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("开始时间：" + simpleDateFormat.format(new Date()));
        shellSortByMove(array);
        System.out.println("结束时间：" + simpleDateFormat.format(new Date()));

    }

    /**
     * 希尔排序-交换法
     *
     * @param array
     */
    public static void shellSortByChange(int array[]) {
        int arrayLength = array.length;
        int temp = 0;
        // 增量 for
        for (int gap = arrayLength / 2; gap > 0; gap /= 2) {
            // 遍历分组并交换排序
            for (int i = gap; i < arrayLength; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序-移动法
     *
     * @param array
     */
    public static void shellSortByMove(int array[]) {
        int arrayLength = array.length;
        int temp = 0;
        // 增量 for
        for (int gap = arrayLength / 2; gap > 0; gap /= 2) {
            // 遍历分组并交换排序
            for (int i = gap; i < arrayLength; i++) {

                int insertVal = array[i];
                int insertIndex = i - gap; // [0,1/2*(arrayLength)-1]

                while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                    array[insertIndex + gap] = array[insertIndex];
                    insertIndex -= gap;
                }
                // 退出循环找到插入位置
                array[insertIndex + gap] = insertVal;
            }
        }
    }
}

