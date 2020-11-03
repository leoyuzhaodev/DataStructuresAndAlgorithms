package com.yzf.ch07;

import java.util.Arrays;

/**
 * @description:选择排序
 * @author:leo_yuzhao
 * @date:2020/10/19
 */
public class SelectSort {
    public static void main(String[] args) {
        int array[] = new int[]{1, 90, 10, -1, 6, 9};
        System.out.println("排序前：" + Arrays.toString(array));
        selectSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void selectSort(int[] array) {
        int arrayLength = array.length;
        int temp = 0;
        for (int i = 0; i < arrayLength - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arrayLength; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }
}
