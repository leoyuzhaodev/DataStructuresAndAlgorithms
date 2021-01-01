package com.yzf.ch11;

import org.junit.Test;

import java.util.Arrays;

/**
 * @description:堆排序
 * @author:leo_yuzhao
 * @date:2020/11/12
 */
public class HeapSort {
    public static void main(String[] args) {
        test1();
    }

    /**
     * 测试排序是算法是否有效
     */
    public static void test1() {
        int array[] = new int[]{4, 6, 8, 5, 9, 3, 7};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 测试大顶堆调整算法
     */
    @Test
    public void test2() {
        int array[] = {1, 5, 6, 4, 2, 3};
        adjustHeap(array, 0, array.length);
    }

    public static void heapSort(int array[]) {
        // 1，将数组初始化为大顶堆
        // 注：array.length / 2 - 1 计算最后一个非叶子节点的公式
        // 按照：【从下到上】的顺序将无序数组调整为大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        int temp = 0;
        // 2，将进行排序：当堆顶放在数组后方，然后重新将数组调整为大顶堆。
        for (int i = array.length - 1; i >= 1; i--) {
            // 交换栈顶元素和最后一个元素
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // 调整数组为大顶堆
            adjustHeap(array, 0, i);
        }
    }

    /**
     * @param arr    数组
     * @param i      指向的是堆顶
     * @param length 需要调整为大顶堆的数组中的部分的长度，例如 [1,3,4,5,2,8] 中 [1,3,4,5,2]需要调整为大顶堆，其长度为 5 ，
     *               length 会逐渐减小，当length为0时说明堆排序已经完成
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 找出子节点中较大的一个
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                // 将大数移到堆顶
                arr[i] = arr[k];
                // i 指向的是堆顶
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}

