package com.yzf.ch08;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:二分查找
 * @author:leo_yuzhao
 * @date:2020/10/31
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 5, 5, 6, 7, 8};
        List<Integer> list = binarySearchRepeat(array, 0, array.length - 1, 5);
        if (list == null) {
            System.out.println("未查找到数据");
        } else {
            System.out.println("数据下标为：" + list);
        }
    }

    /**
     * 递归实现二分查找
     *
     * @param array
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int binarySearch(int array[], int left, int right, int findVal) {

        // 递出条件
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (array[mid] > findVal) {
            // [1] {5} 向左递归
            return binarySearch(array, left, mid - 1, findVal);
        } else if (array[mid] < findVal) {
            // {5} [6] 向右递归
            return binarySearch(array, mid + 1, right, findVal);
        } else {
            return mid;
        }

    }

    /**
     * 递归实现二分查找，在数组中查找重复值
     *
     * @param array
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearchRepeat(int array[], int left, int right, int findVal) {

        // 递出条件
        if (left > right || findVal < array[left] || findVal > array[right]) {
            return null;
        }

        int mid = (left + right) / 2;
        if (array[mid] > findVal) {
            // [1] {5} 向左递归
            return binarySearchRepeat(array, left, mid - 1, findVal);
        } else if (array[mid] < findVal) {
            // {5} [6] 向右递归
            return binarySearchRepeat(array, mid + 1, right, findVal);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(mid);

            // 向右遍历查找
            for (int i = mid + 1; array[i] == findVal && i < array.length; i++) {
                list.add(i);
            }
            // 向左遍历查找
            for (int i = mid - 1; array[i] == findVal && i >= 0; i--) {
                list.add(i);
            }
            return list;
        }

    }

}
