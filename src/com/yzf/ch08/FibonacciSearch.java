package com.yzf.ch08;

import com.sun.org.apache.bcel.internal.generic.IFNE;

import java.util.Arrays;

/**
 * @description:Fibonacci查找
 * @author:leo_yuzhao
 * @date:2020/11/2
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int array[] = new int[]{1, 2, 3, 4};
        int i = fibonacciSearch(array, 1);
        System.out.println(i);
    }

    /**
     * 获取指定长度的 fibonacci 数列
     *
     * @param length
     * @return
     */
    public static int[] getFibonacciArray(int length) {
        int array[] = new int[length];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < length; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array;
    }

    /**
     * 斐波那契查找算法
     *
     * @param arr
     * @param findVal
     * @return
     */
    public static int fibonacciSearch(int[] arr, int findVal) {

        int arrLength = arr.length;
        int low = 0;
        int high = arr.length - 1;
        int key = 0;
        int mid = 0;
        int[] fibonacciArray = getFibonacciArray(10);

        // 初始化 key 值
        while (fibonacciArray[key] - 1 < arr.length) {
            key++;
        }

        // 数组扩容
        arr = Arrays.copyOf(arr, fibonacciArray[key] - 1);

        // 填充数组
        if (arrLength < fibonacciArray[key] - 1) {
            for (int i = high + 1; i < fibonacciArray[key] - 1; i++) {
                arr[i] = arr[high];
            }
        }

        // 查找
        while (low <= high) {
            mid = low + fibonacciArray[key - 1] - 1;
            if (arr[mid] < findVal) {
                // {3} [4] 向右查找
                key -= 2;
                low = mid + 1;
            } else if (arr[mid] > findVal) {
                // [2] {3} 向左查找
                key -= 1;
                high = mid - 1;
            } else {
                if (mid > arrLength - 1) {
                    return arrLength - 1;
                } else {
                    return mid;
                }
            }
        }

        return -1;
    }

}
