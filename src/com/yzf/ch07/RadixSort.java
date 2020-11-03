package com.yzf.ch07;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @description:基数排序
 * @author:leo_yuzhao
 * @date:2020/10/24
 */
public class RadixSort {
    public static void main(String[] args) {
        // testCorrect();
        // testSpeed();
        testCorrect1();
    }

    public static void testCorrect1() {
        int array[] = new int[]{-53, 3, 542, -1001, 14, -214};
        System.out.println("排序前：" + Arrays.toString(array));
        RadixSortV1(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }


    public static void testCorrect() {
        int array[] = new int[]{53, 3, 542, 748, 14, 214};
        System.out.println("排序前：" + Arrays.toString(array));
        RadixSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void testSpeed() {
        int array[] = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100000000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("开始时间：" + simpleDateFormat.format(new Date()));
        RadixSort(array);
        System.out.println("结束时间：" + simpleDateFormat.format(new Date()));
        // 测试验证
        for (int i = 30000; i <= 30010; i++) {
            System.out.println(array[i]);
        }
    }

    public static void RadixSort(int[] array) {

        // 1，找出数组中的最大数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        // 2，初始化相关变量
        int maxNumberLength = (max + "").length();
        int bucket[][] = new int[10][array.length];
        int bucketIndex[] = new int[10];
        int arrayLength = array.length;
        int tempIndex;

        // 3，将数据按照规则放入桶中
        for (int i = 0, n = 1; i < maxNumberLength; i++, n *= 10) {
            // 3.1，取出数据的某一位（个位，十位，百位），根据该位的值，将数据放入桶中
            for (int j = 0; j < arrayLength; j++) {
                int positionVal = array[j] / n % 10;
                bucket[positionVal][bucketIndex[positionVal]] = array[j];
                bucketIndex[positionVal]++;
            }
            // 3.2，将桶中的数据依次取出放入原来的数组中
            for (int j = (tempIndex = 0); j < bucket.length; j++) {
                for (int k = 0; k < bucketIndex[j]; k++) {
                    array[tempIndex] = bucket[j][k];
                    tempIndex++;
                }
                // 将桶计数器置空，方便下一轮使用
                bucketIndex[j] = 0;
            }
        }
    }

    public static void RadixSortV1(int[] array) {

        // 1，找出数组中最长的数
        int max = Math.abs(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (max < Math.abs(array[i])) {
                max = Math.abs(array[i]);
            }
        }
        // 2，初始化相关变量
        int maxNumberLength = (max + "").length();
        int bucket[][] = new int[10][array.length];
        int bucketIndex[] = new int[10];
        int arrayLength = array.length;
        int tempIndex;

        // 3，将数据按照规则放入桶中
        for (int i = 0, n = 1; i < maxNumberLength; i++, n *= 10) {
            // 3.1，取出数据的某一位（个位，十位，百位），根据该位的值，将数据放入桶中
            for (int j = 0; j < arrayLength; j++) {
                int positionVal = (int) Math.abs(array[j] / n % 10);
                bucket[positionVal][bucketIndex[positionVal]] = array[j];
                bucketIndex[positionVal]++;
            }
            // 3.2，将桶中的数据依次取出放入原来的数组中
            for (int j = (tempIndex = 0); j < bucket.length; j++) {
                for (int k = 0; k < bucketIndex[j]; k++) {
                    array[tempIndex] = bucket[j][k];
                    tempIndex++;
                }
                // 将桶计数器置空，方便下一轮使用
                bucketIndex[j] = 0;
            }
        }

        handelNegativeV1(array);

    }

    /**
     * 处理基数排序中的负数
     *
     * @param array
     */
    public static void handelNegative(int array[]) {
        int arrayLength = array.length;
        int positiveArray[] = new int[arrayLength];
        int positiveIndex = 0;
        int negativeArray[] = new int[arrayLength];
        int negativeIndex = 0;

        // 将正负数按次序分开放入到两个数组中
        for (int i = 0; i < arrayLength; i++) {
            if (array[i] >= 0) {
                positiveArray[positiveIndex++] = array[i];
            } else {
                negativeArray[negativeIndex++] = array[i];
            }
        }
        // 先将【负数数组】中的数据【倒序】放入原数组中，再将【正数数组】中的数据【顺序】放入原数组中
        int tempIndex = 0;
        for (int i = negativeIndex - 1; i >= 0; i--) {
            array[tempIndex++] = negativeArray[i];
        }
        for (int i = 0; i < positiveIndex; i++) {
            array[tempIndex++] = positiveArray[i];
        }
    }

    /**
     * 处理基数排序中的负数
     *
     * @param array
     */
    public static void handelNegativeV1(int array[]) {
        int arrayLength = array.length;
        for (int i = 0; i < arrayLength; i++) {
            if (array[i] < 0) {
                int temp = array[i];
                for (int j = i-1; j >= 0; j--) {
                    array[j+1] = array[j];
                }
                array[0] = temp;
            }
        }
    }

}
