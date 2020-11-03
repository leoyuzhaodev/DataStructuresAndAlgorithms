package com.yzf.ch07;

import java.util.Arrays;

/**
 * @description:插入排序
 * @author:leo_yuzhao
 * @date:2020/10/19
 */
public class InsertSort {
    public static void main(String[] args) {
        int array[] = new int[]{1, 90, 10, -1, 6, 9, -9};
        System.out.println("排序前：" + Arrays.toString(array));
        insertSortV1(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void insertSort(int[] array) {
        int arrayLength = array.length;
        int insertVal;
        int insertIndex;
        for (int i = 1; i < arrayLength; i++) {
            insertVal = array[i];
            for (insertIndex = i - 1; insertIndex >= 0; insertIndex--) {
                if (insertVal < array[insertIndex]) {
                    // 为插入点后移位置
                    array[insertIndex + 1] = array[insertIndex];
                } else {
                    break;
                }
            }
            // 在指定位置插入数据
            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertVal;
            }
        }
    }

    public static void insertSortV1(int[] array) {
        int arrayLength = array.length;
        int insertVal;
        int insertIndex;
        for (int i = 1; i < arrayLength; i++) {
            insertVal = array[i];
            for (insertIndex = i - 1;
                 (insertIndex >= 0) && (insertVal < array[insertIndex]); insertIndex--) {
                array[insertIndex + 1] = array[insertIndex];
            }
            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertVal;
            }
        }
    }

}
