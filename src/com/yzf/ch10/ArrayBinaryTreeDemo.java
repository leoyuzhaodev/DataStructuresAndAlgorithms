package com.yzf.ch10;

/**
 * @description:顺序存储二叉树
 * @author:leo_yuzhao
 * @date:2020/11/8
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int array[] = new int[]{1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.preOrder();
    }

}

class ArrayBinaryTree {

    private int array[];

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    /**
     * 前序遍历入口
     */
    public void preOrder() {
        if (array == null || array.length <= 0) {
            System.out.println("树为空无法遍历...");
        } else {
            preOrder(0);
        }
    }

    /**
     * 前序遍历
     *
     * @param index
     */
    private void preOrder(int index) {
        // 输出当前元素
        System.out.println(array[index]);

        // 左递归遍历
        int leftIndex = 2 * index + 1;
        if (leftIndex < array.length) {
            preOrder(leftIndex);
        }

        // 右递归遍历
        int rightIndex = 2 * index + 2;
        if (rightIndex < array.length) {
            preOrder(rightIndex);
        }
    }
}