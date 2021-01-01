package com.yzf.ch11.review;

import org.junit.Test;

import java.util.Arrays;

/**
 * @description: 堆排序复习
 * @author:leo_yuzhao
 * @date:2021/1/1
 */
public class HeapSortReview {

    /**
     * 测试大顶堆调整算法
     */
    @Test
    public void test1() {
        int array[] = {1, 5, 6, 4, 2, 3};
        adjustHeapMin(array, 0, array.length);
    }

    @Test
    public void test2() {
        int array[] = {1, 5, 6, 4, 2, 3};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    public void heapSort(int array[]) {

        // 将数组初始化为大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeapMin(array, i, array.length);
        }

        // 排序：将堆顶的数据依次放在数组的最后
        for (int i = array.length - 1; i > 0; i--) {
            // 堆顶元素和数组末端元素交换
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            // 调整大顶堆
            adjustHeapMin(array, 0, i);
        }

    }

    /**
     * @param array
     * @param i      指向堆顶的索引
     * @param length
     */
    public static void adjustHeapMax(int array[], int i, int length) {

        // 保存堆顶的数据
        int temp = array[i];

        // 正式调整
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 找出当前堆顶的子节点中的较大的子节点
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            // 将堆顶数据和较大的子节点数据进行比较
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    /**
     * @param array
     * @param i      指向堆顶的索引
     * @param length
     */
    public static void adjustHeapMin(int array[], int i, int length) {

        // 保存堆顶的数据
        int temp = array[i];

        // 正式调整
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 找出当前堆顶的子节点中的较小的子节点
            if (k + 1 < length && array[k] > array[k + 1]) {
                k++;
            }
            // 将堆顶数据和较小的子节点数据进行比较
            if (array[k] < temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

}
