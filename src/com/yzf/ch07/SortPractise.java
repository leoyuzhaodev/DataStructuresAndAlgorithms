package com.yzf.ch07;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @description: 排序练习
 * @author:leo_yuzhao
 * @date:2020/10/22
 */
public class SortPractise {
    public static void main(String[] args) {
        int array[] = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前：" + Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        // shellSortChange(array);
        // shellSortMove(array);
        // mergeSort(array, 0, array.length - 1, new int[array.length]);
        // bucketSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
        // testBucketSort();
    }

    public static void testBucketSort() {
        int array[] = new int[]{1, 3, -8, -98, 2, 5, 6, 4};
        System.out.println("排序前：" + Arrays.toString(array));
        bucketSortMinus(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    // 冒泡排序
    public static void bubbleSort(int[] array) {
        int length = array.length;
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // 选择排序
    public static void selectSort(int[] array) {
        int temp = 0;
        int minIndex = 0;
        // 确定最小值位置
        for (int i = 0; i < array.length - 1; i++) {
            // 查找最小值索引
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            // 执行交换
            if (minIndex != i) {
                temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    // 插入排序
    public static void insetSort(int[] array) {
        int length = array.length;
        int j;
        int insertVal;
        // 指定插入值
        for (int i = 1; i < length; i++) {

            // 寻找插入位置
            j = i - 1;
            insertVal = array[i];
            for (; j >= 0 && insertVal < array[j]; j--) {
                array[j + 1] = array[j];
            }

            // 执行插入
            if (j + 1 != i) {
                array[j + 1] = insertVal;
            }

        }
    }

    // 希尔排序：交换法
    public static void shellSortChange(int[] array) {
        int arrayLength = array.length;
        int temp = 0;
        // 增量
        for (int i = arrayLength / 2; i > 0; i /= 2) {
            // 根据增量进行插入排序
            for (int j = i; j < arrayLength; j++) {
                for (int k = j - i; k >= 0; k -= i) {
                    if (array[k + i] < array[k]) {
                        temp = array[k + i];
                        array[k + i] = array[k];
                        array[k] = temp;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    // 希尔排序：移动法
    public static void shellSortMove(int[] array) {
        int arrayLength = array.length;
        int temp = 0;
        int k;
        int insertVal;
        // 增量
        for (int i = arrayLength / 2; i > 0; i /= 2) {
            // 根据增量进行插入排序
            for (int j = i; j < arrayLength; j++) {
                insertVal = array[j];
                k = j - i;
                for (; k >= 0; k -= i) {
                    if (insertVal < array[k]) {
                        array[k + i] = array[k];
                    } else {
                        break;
                    }
                }
                array[k + i] = insertVal;
            }
        }
    }

    /**
     * 快速排序
     *
     * @param array
     * @param left
     * @param right
     */
    public static void quickSort(int[] array, int left, int right) {
        // 1，递出条件
        if (left >= right) {
            return;
        }

        // 2，初始化相关变量
        int l = left;
        int r = right;
        int temp = 0;
        int baseVal = array[left];

        // 3，将大数放在基数右边，将小数放在基数左边
        while (l < r) {
            // 3.1，从右向左找小数
            while (array[r] >= baseVal && r > l) {
                r--;
            }
            // 3.2，从左向右找大数
            while (array[l] <= baseVal && r > l) {
                l++;
            }
            // 3.3，执行交换
            if (r > l) {
                temp = array[r];
                array[r] = array[l];
                array[l] = temp;
            }
        }

        // 4，确定基数位置
        if (l != left) {
            temp = array[left];
            array[left] = array[l];
            array[l] = temp;
        }
        // 5，执行左右递归
        quickSort(array, left, l);
        quickSort(array, l + 1, right);
    }

    /**
     * 分
     *
     * @param array
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int array[], int left, int right, int temp[]) {

        if (left < right) {
            int mid = (left + right) / 2;

            // 左分
            mergeSort(array, left, mid, temp);

            // 右分
            mergeSort(array, mid + 1, right, temp);

            // 归并
            merge(array, left, mid, right, temp);
        } else {
            return;
        }

    }

    /**
     * 并 且 排序
     *
     * @param array
     * @param left
     * @param right
     * @param temp
     */
    public static void merge(int array[], int left, int mid, int right, int temp[]) {
        // 1,初始化相关变量
        int l = left;
        int r = mid + 1;
        int tempIndex = 0;
        // 2,执行归并，将两边的有序数列依次放入 中转容器 中
        while (l <= mid && r <= right) {
            if (array[l] < array[r]) {
                temp[tempIndex++] = array[l++];
            } else {
                // array[r] <= array[l]
                temp[tempIndex++] = array[r++];
            }
        }
        // 3,将两边剩余的数据放入 中转容器 中
        while (l <= mid) {
            temp[tempIndex++] = array[l++];
        }
        while (r <= right) {
            temp[tempIndex++] = array[r++];
        }
        // 4,将中转容器中的数据依次放入 原数组 中
        tempIndex = 0;
        for (int i = left; i <= right; i++) {
            array[i] = temp[tempIndex++];
        }
    }

    /**
     * 桶排序
     *
     * @param array
     */
    public static void bucketSort(int array[]) {

        // 1，找出数组中最大的元素
        int maxVal = array[0];
        for (int i = 1; i < array.length; i++) {
            if (maxVal < array[i]) {
                maxVal = array[i];
            }
        }
        // 2，初始化相关变量
        int maxLength = (maxVal + "").length();
        int arrayLength = array.length;
        // 初始化桶
        int bucket[][] = new int[10][arrayLength];
        // 初始化桶指针
        int bucketIndexes[] = new int[10];

        // 3，执行桶排序
        for (int i = 1, n = 1; i <= maxLength; n *= 10, i++) {

            // 3.1，将数组中的数据放入桶中
            for (int j = 0; j < arrayLength; j++) {
                int temp = array[j] / n % 10;
                bucket[temp][bucketIndexes[temp]++] = array[j];
            }

            // 3.2，将桶中的数据依次放入原数组中
            for (int j = 0, tempIndex = 0; j < bucket.length; j++) {
                if (bucketIndexes[j] > 0) {
                    for (int k = 0; k < bucketIndexes[j]; k++) {
                        array[tempIndex++] = bucket[j][k];
                    }
                }
            }
        }

    }

    /**
     * 桶排序：解决负数排序问题
     *
     * @param array
     */
    public static void bucketSortMinus(int array[]) {

        // 1，找出数组中最大的元素
        int maxVal = Math.abs(array[0]);
        for (int i = 1; i < array.length; i++) {
            int tempVal = Math.abs(array[i]);
            if (maxVal < tempVal) {
                maxVal = tempVal;
            }
        }
        // 2，初始化相关变量
        int maxLength = (maxVal + "").length();
        int arrayLength = array.length;
        // 初始化桶
        int bucket[][] = new int[10][arrayLength];
        // 初始化桶指针
        int bucketIndexes[] = new int[10];

        // 3，执行桶排序
        for (int i = 1, n = 1; i <= maxLength; n *= 10, i++) {

            // 3.1，将数组中的数据放入桶中
            for (int j = 0; j < arrayLength; j++) {
                int temp = Math.abs(array[j]) / n % 10;
                bucket[temp][bucketIndexes[temp]++] = array[j];
            }

            // 3.2，将桶中的数据依次放入原数组中
            for (int j = 0, tempIndex = 0; j < bucket.length; j++) {
                if (bucketIndexes[j] > 0) {
                    for (int k = 0; k < bucketIndexes[j]; k++) {
                        array[tempIndex++] = bucket[j][k];
                    }
                }
                // 注意重置该变量
                bucketIndexes[j] = 0;
            }
        }

        // 两种处理负数的方式
        if (false) {
            // 4，将数组中的 正，负数字分别放入两个数组中
            int positiveArray[] = new int[arrayLength];
            int negativeArray[] = new int[arrayLength];
            int positiveIndex = 0;
            int negativeIndex = 0;
            for (int i = 0; i < arrayLength; i++) {
                if (array[i] >= 0) {
                    positiveArray[positiveIndex++] = array[i];
                } else {
                    negativeArray[negativeIndex++] = array[i];
                }
            }
            // 5，将负数数组中的数据倒序放入原数组中
            int tempIndex = 0;
            for (int i = negativeIndex - 1; i >= 0; i--) {
                array[tempIndex++] = negativeArray[i];
            }
            // 5，将正数数组中的数据正序放入原数组中
            for (int i = 0; i < positiveIndex; i++) {
                array[tempIndex++] = positiveArray[i];
            }
        } else {
            for (int i = 0; i < arrayLength; i++) {
                int tempVal = array[i];
                if (tempVal < 0) {
                    for (int j = i - 1; j >= 0; j--) {
                        array[j + 1] = array[j];
                    }
                    array[0] = tempVal;
                }
            }
        }


    }


}