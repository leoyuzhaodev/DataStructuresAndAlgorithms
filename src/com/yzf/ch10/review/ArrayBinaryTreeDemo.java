package com.yzf.ch10.review;

import org.junit.Test;

/**
 * @description:数组存储二叉树
 * @author:leo_yuzhao
 * @date:2020/12/24
 */
public class ArrayBinaryTreeDemo {

    private static int treeArray[] = {1, 2, 3, 4, 5, 6, 7};

    /**
     * 遍历顺序存储的二叉树
     */
    @Test
    public void test1() {
        preOrder(0);
    }

    /**
     * 前序遍历顺序存储的二叉树
     *
     * @param index
     */
    public static void preOrder(int index) {
        System.out.println(treeArray[index]);
        // 向左递归遍历
        if (index * 2 + 1 < treeArray.length) {
            preOrder(index * 2 + 1);
        }
        // 向右递归遍历
        if (index * 2 + 2 < treeArray.length) {
            preOrder(index * 2 + 2);
        }
    }


}
