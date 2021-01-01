package com.yzf.ch10.review;

import org.junit.Test;

/**
 * @description:二叉树复习
 * @author:leo_yuzhao
 * @date:2020/12/24
 */
public class BinaryTreeReview {

    static public TreeNode root;
    private static TreeNode pre = null;

    static {
        root = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        root.left = t2;
        root.right = t3;
        t2.left = t4;
        t2.right = t5;
    }

    /**
     * 测试二叉树的前序遍历
     */
    @Test
    public void test1() {
        preOrder(root);
    }

    /**
     * 测试二叉树的前序查找
     */
    @Test
    public void test2() {
        preOrderFind(root, 5);
    }

    /**
     * 测试删除二叉树的节点
     */
    @Test
    public void test3() {
        preOrderDelete(root, 4);
        preOrder(root);
    }

    /**
     * 中序线索化二叉树
     */
    @Test
    public void test4() {
        threadedBinaryTree(root);
    }


    /**
     * 中序线索化二叉树
     *
     * @param node
     */
    public static void threadedBinaryTree(TreeNode node) {

        // 向左递归
        if (node.left != null) {
            threadedBinaryTree(node.left);
        }

        if (node.left == null) {
            node.left = pre;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
        }
        pre = node;

        // 向右递归
        if (node.right != null) {
            threadedBinaryTree(node.right);
        }

    }

    /**
     * 前序遍历树
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (root == null) {
            System.out.println("当前二叉树为空，无法遍历...");
            return;
        }
        System.out.println(node.value);
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    /**
     * 前序遍历树：查找元素
     *
     * @param node
     */
    public static TreeNode preOrderFind(TreeNode node, int value) {
        if (node.value == value) {
            return node;
        }

        // 向左查找
        if (node.left != null) {
            TreeNode temp = preOrderFind(node.left, value);
            if (temp != null) {
                return temp;
            }
        }

        // 向右查找
        if (node.right != null) {
            TreeNode temp = preOrderFind(node.right, value);
            if (temp != null) {
                return temp;
            }
        }

        return null;
    }

    /**
     * 前序遍历删除
     *
     * @param node
     * @param value
     * @return
     */
    public static boolean preOrderDelete(TreeNode node, int value) {
        if (node == root && root.value == value) {
            root = null;
            return true;
        }

        // 判断当前节点的左子节点是否是要删除的元素
        if (node.left != null && node.left.value == value) {
            node.left = null;
            return true;
        }
        // 判断当前节点的右子节点是否是要删除的元素
        if (node.right != null && node.right.value == value) {
            node.right = null;
            return true;
        }

        // 向左递归删除
        if (node.left != null) {
            boolean flag = preOrderDelete(node.left, value);
            if (flag) {
                return flag;
            }
        }

        // 向右递归删除
        if (node.right != null) {
            boolean flag = preOrderDelete(node.right, value);
            if (flag) {
                return flag;
            }
        }

        return false;
    }

}

/**
 * 树节点
 */
class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }


}