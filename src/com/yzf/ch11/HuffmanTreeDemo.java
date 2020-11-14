package com.yzf.ch11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:赫夫曼树
 * @author:leo_yuzhao
 * @date:2020/11/14
 */
public class HuffmanTreeDemo {

    public static void main(String[] args) {
        Node root = createHuffmanTree(new int[]{13, 7, 8, 3, 29, 6, 1});
        preOrder(root);
    }

    /**
     * 根据数组构建赫夫曼树
     *
     * @param array
     * @return
     */
    public static Node createHuffmanTree(int array[]) {
        if (array != null && array.length > 0) {
            List<Node> nodeList = new ArrayList<>();
            for (int nodeValue : array) {
                nodeList.add(new Node(nodeValue));
            }
            while (nodeList.size() > 1) {
                // 1，将集合中的元素排序
                Collections.sort(nodeList);
                // 2，取出最小的两个元素构成树
                Node left = nodeList.get(0);
                Node right = nodeList.get(1);
                Node father = new Node(left.getValue() + right.getValue(), left, right);
                // 3，处理集合
                nodeList.remove(left);
                nodeList.remove(right);
                nodeList.add(father);
            }
            return nodeList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 前序遍历赫夫曼树
     *
     * @param root
     */
    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("赫夫曼树为空，无法遍历");
        } else {
            root.preOrder();
        }
    }

}

class Node implements Comparable<Node> {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
