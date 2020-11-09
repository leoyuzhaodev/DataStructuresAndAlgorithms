package com.yzf.ch10;

/**
 * @description:线索化二叉树
 * @author:leo_yuzhao
 * @date:2020/11/8
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedTreeNode t1 = new ThreadedTreeNode(1, "张三");
        ThreadedTreeNode t2 = new ThreadedTreeNode(2, "李四");
        ThreadedTreeNode t3 = new ThreadedTreeNode(3, "王五");
        ThreadedTreeNode t4 = new ThreadedTreeNode(4, "马六");
        ThreadedTreeNode t5 = new ThreadedTreeNode(5, "吴七");
        ThreadedTreeNode t6 = new ThreadedTreeNode(6, "宋八");
        t1.setLeft(t2);
        t1.setRight(t3);
        t2.setLeft(t4);
        t2.setRight(t5);
        t3.setLeft(t6);
        ThreadedTree threadedTree = new ThreadedTree(t1);
        threadedTree.infixThreadedTree();
        System.out.println();
        threadedTree.infixThreadedShow();
    }
}

class ThreadedTree {
    private ThreadedTreeNode root;

    // 记录当前节点的前驱节点
    private ThreadedTreeNode pre;

    public ThreadedTree(ThreadedTreeNode root) {
        this.root = root;
    }

    /**
     * 中序线索化二叉树
     */
    public void infixThreadedTree() {
        if (root == null) {
            System.out.println("当前树为空，无法线索化...");
        } else {
            infixThreadedTree(root);
        }
    }

    /**
     * 中序线索化二叉树
     */
    private void infixThreadedTree(ThreadedTreeNode node) {
        // 向左递归
        if (node.getLeft() != null) {
            infixThreadedTree(node.getLeft());
        }
        // 线索化当前节点/处理前驱节点
        if (node.getLeft() == null) {
            // 设置当前节点左指针类型
            node.setLeftType(1);
            // 设置当前节点的前驱节点
            node.setLeft(pre);
        }
        // 处理上一个节点的后继节点问题，注意判断表达式：pre.getRight() == null 十分重要
        // right == null 说明该节点的 right 指向的是后继节点，也就说 rightType == 1
        if (pre != null && pre.getRight() == null) {
            // 设置上一个节点的右指针类型
            pre.setRightType(1);
            // 设置上一个节点的后继节点
            pre.setRight(node);
        }
        // 重置 pre 节点
        pre = node;
        // 向右递归
        if (node.getRight() != null) {
            infixThreadedTree(node.getRight());
        }
    }

    /**
     * 遍历中序线索化二叉树
     */
    public void infixThreadedShow() {
        if (root == null) {
            System.out.println("当前树为空，无法线索化...");
        } else {
            infixThreadedShow(root);
        }
    }

    private void infixThreadedShow(ThreadedTreeNode node) {
        if (node == null) {
            return;
        }
        // 向左找
        while (node.getLeftType() == 0) {
            node = node.getLeft();
        }

        // 输出当前节点
        System.out.println(node);

        // 输出当前节点的后继节点
        while (node.getRightType() == 1) {
            System.out.println(node.getRight());
            node = node.getRight();
        }
        node = node.getRight();
        infixThreadedShow(node);
    }

    // 后序线索化二叉树
    // 遍历中序线索化二叉树

    // 前序线索化二叉树
    // 遍历中序线索化二叉树

}


class ThreadedTreeNode {
    private int id;
    private String name;
    private ThreadedTreeNode left;
    private ThreadedTreeNode right;
    // leftType 0:left指向的是子树 1:left指向的是前驱节点
    private int leftType = 0;
    // rightType 0:left指向的是子树 1:right指向的是前驱节点
    private int rightType = 0;

    public ThreadedTreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThreadedTreeNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedTreeNode left) {
        this.left = left;
    }

    public ThreadedTreeNode getRight() {
        return right;
    }

    public void setRight(ThreadedTreeNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "ThreadedTreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}