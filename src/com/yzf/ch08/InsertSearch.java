package com.yzf.ch08;

/**
 * @description:插值查找
 * @author:leo_yuzhao
 * @date:2020/11/1
 */
public class InsertSearch {

    public static void main(String[] args) {
        int array[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index = insertSearch(array, 0, array.length - 1, 5);
        System.out.println("index:"+index);
    }


    public static int insertSearch(int array[], int left, int right, int findVal) {

        if (left > right || findVal < array[left] || findVal > array[right]) {
            return -1;
        }
        // 插值查找关键
        // {1, 2, 3, 4, 5, 6, 7, 8, 9}
        // 5 => 0+(8-0)*((5-0)/9-0) = 0+8*5/9 = 4
        int mid = left + (right - left) * ((findVal - array[left]) / (array[right] - array[left]));
        if (array[mid] > findVal) {
            // [2] {5} => 向左递归
            return insertSearch(array, left, mid - 1, findVal);
        } else if (array[mid] < findVal) {
            // {5} [6] => 向右递归
            return insertSearch(array, mid + 1, right, findVal);
        } else {
            return mid;
        }

    }

}
