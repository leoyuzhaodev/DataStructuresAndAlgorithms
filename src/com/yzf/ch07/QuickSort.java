package com.yzf.ch07;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @description:快速排序
 * @author:leo_yuzhao
 * @date:2020/10/22
 */
public class QuickSort {

    public static void main(String[] args) {

        // 5,1,3,7,8,2,3,4,9 => 2,1,3,4,3,5,8,7,9
//        int array[] = new int[]{5, 1, 3, 7, 8, 6, 2, 4, 9};
//        quickSort(0, array.length - 1, array);
//        System.out.println(Arrays.toString(array));

//        testCorrect();
        testSpeed();
    }

    public static void testCorrect() {
        int array[] = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前：" + Arrays.toString(array));
        quickSort(0, array.length - 1,array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void testSpeed() {
        int array[] = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10000000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("开始时间：" + simpleDateFormat.format(new Date()));
        quickSort(0, array.length - 1,array);
        System.out.println("结束时间：" + simpleDateFormat.format(new Date()));

        // 测试验证
        for (int i=2000000;i<=2000010;i++){
            System.out.println(array[i]);
        }
    }

    public static void quickSort(int left, int right, int array[]) {

        // 递出条件
        if (left >= right) return;

        // 初始化指针
        int l = left;
        int r = right;

        // 确定基数位置
        int baseValue = array[l];
        int temp = 0;

        while (l < r) {
            // 注意先从右向左找小数
            while (array[r] >= baseValue && r > l) {
                r--;
            }
            // 从左向右找大数
            while (array[l] <= baseValue && r > l) {
                l++;
            }
            // 执行交换
            if (l < r) {
                temp = array[r];
                array[r] = array[l];
                array[l] = temp;
            }
        }

        // 将基数摆放到合适的位置
        if (l != left) {
            temp = array[l];
            array[l] = array[left];
            array[left] = temp;
        }

        // 左递归
        quickSort(left, r - 1, array);

        // 右递归
        quickSort(l + 1, right, array);

    }


}
