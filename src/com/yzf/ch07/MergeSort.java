package com.yzf.ch07;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @description:归并排序
 * @author:leo_yuzhao
 * @date:2020/10/23
 */
public class MergeSort {

    public static void main(String[] args) {
        // testCorrect();
        testSpeed();
    }

    public static void testCorrect() {
        int array[] = new int[]{0, 2, 5, 7, 1, 3, 6, 4, 8, 9};
        divideAndMergeSort(array, 0, array.length - 1, new int[array.length]);
        System.out.println(Arrays.toString(array));
    }

    public static void testSpeed() {
        int array[] = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10000000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("开始时间：" + simpleDateFormat.format(new Date()));
        divideAndMergeSort(array, 0, array.length - 1, new int[array.length]);
        System.out.println("结束时间：" + simpleDateFormat.format(new Date()));

        // 测试验证
        for (int i = 2000000; i <= 2000010; i++) {
            System.out.println(array[i]);
        }
    }

    // 分
    public static void divideAndMergeSort(int[] array, int left, int right, int temp[]) {

        if (left < right) {
            // 分
            int mid = (left + right) / 2;
            // 左递归
            // System.out.println("L[" + left + "," + mid + "]");
            divideAndMergeSort(array, left, mid, temp);

            // 右递归
            // System.out.println("R[" + (mid + 1) + "," + right + "]");
            divideAndMergeSort(array, mid + 1, right, temp);

            // 执行归并
            merge(array, left, mid, right, temp);
        }

    }

    // 治
    public static void merge(int[] array, int left, int mid, int right, int temp[]) {

        // System.out.println("M[" + left + "," + right + "]");

        // 左指针
        int l = left;
        // 右指针
        int r = mid + 1;
        // 中转容器指针
        int t = 0;

        // 将两边的数据进行归并，有序的转移到 temp[] 中
        while (l <= mid && r <= right) {
            if (array[l] <= array[r]) {
                // 左边数据 <= 右边数据
                temp[t] = array[l];
                t++;
                l++;
            } else {
                // 右边数据 <= 左边数据
                temp[t] = array[r];
                t++;
                r++;
            }
        }

        // 将两边剩余的数据转移到 temp[] 中
        // 右剩余
        while (r <= right) {
            temp[t] = array[r];
            t++;
            r++;
        }
        // 左剩余
        while (l <= mid) {
            temp[t] = array[l];
            t++;
            l++;
        }

        // 将 temp[] 中的数据拷贝到 array[left,rigth] 中
        t = 0;
        for (int i = left; i <= right; i++) {
            array[i] = temp[t];
            t++;
        }

    }

}
