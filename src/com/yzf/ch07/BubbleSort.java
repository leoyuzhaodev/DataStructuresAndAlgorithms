package com.yzf.ch07;

import java.util.Arrays;

/**
 * @description:冒泡排序
 * @author:leo_yuzhao
 * @date:2020/10/19
 */
public class BubbleSort {

    public static void main(String[] args) {
        int array[] = new int[]{1, 2, 10, -1, 6, 9};
        System.out.println("排序前：" + Arrays.toString(array));
        bubbleSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    /**
     * 冒泡排序算法
     *
     * @param arr
     */
    public static void bubbleSort(int arr[]) {
        int arrLength = arr.length;
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arrLength - 1; i++) {
            for (int j = 0; j < arrLength - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

}
