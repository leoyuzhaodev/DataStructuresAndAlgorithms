package com.yzf.ch04;

/**
 * @description:单向环形链表
 * @author:leo_yuzhao
 * @date:2020/10/11
 */
public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
// 测试 添加，批量添加，遍历
//        list.add(new BoyNode(1));
//        list.add(new BoyNode(2));
//        list.add(new BoyNode(3));
//        list.add(new BoyNode(4));
//        list.addBatch(5);
//        list.list();

        // 测试约瑟夫问题
        int sum = 5;
        list.addBatch(sum);
        list.solveJosephuProblem(1,2,sum);

    }
}

class CircleSingleLinkedList {

    private BoyNode first = null;

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 添加节点
     *
     * @param newBoyNode
     */
    public void add(BoyNode newBoyNode) {
        if (isEmpty()) {
            first = newBoyNode;
            newBoyNode.setNext(first);
        } else {
            BoyNode temp = first;
            while (temp.getNext() != first) {
                temp = temp.getNext();
            }
            temp.setNext(newBoyNode);
            newBoyNode.setNext(first);
        }
    }

    /**
     * 批量添加
     *
     * @param sum
     */
    public void addBatch(int sum) {

        // 初始化 tail
        BoyNode tail = null;
        if (!isEmpty()) {
            BoyNode temp = first;
            while (temp.getNext() != first) {
                temp = temp.getNext();
            }
            tail = temp;
        }
        for (int i = 1; i <= sum; i++) {
            BoyNode newBoyNode = new BoyNode(i);
            if (i == 1 && first == null) {
                first = newBoyNode;
                newBoyNode.setNext(first);
            } else {
                tail.setNext(newBoyNode);
                newBoyNode.setNext(first);
            }
            tail = newBoyNode;
        }

    }

    /**
     * 遍历节点
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("当前链表为空...");
            return;
        }
        BoyNode cur = first;
        do {
            System.out.println(cur);
            cur = cur.getNext();
        } while (cur != first);
    }

    /**
     * 使用链表的方式解决约瑟夫问题
     *
     * @param startNo 开始编号
     * @param k       间隔数
     * @param sum     总人数
     */
    public void solveJosephuProblem(int startNo, int k, int sum) {
        // 判断变量是否合法
        if (startNo > sum || startNo < 1 || k < 1) {
            System.out.println("数据不合法...");
            return;
        }
        // 判断链表是否为空
        if (isEmpty()) {
            System.out.println("链表为空...");
            return;
        }
        // 初始化辅助指针
        BoyNode helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 辅助指针和头指针移动 startNo-1 次
        for (int i = 0; i < startNo - 1; i++) {
            helper = helper.getNext();
            first = first.getNext();
        }
        // 移动剔除
        while (true) {
            // 移动
            for (int i = 0; i < k - 1; i++) {
                helper = helper.getNext();
                first = first.getNext();
            }
            // 剔除
            System.out.println(first);
            first = first.getNext();
            helper.setNext(first);
            // 判断退出
            if (first == helper) {
                System.out.println("最后一个：" + first);
                break;
            }
        }
    }

}

class BoyNode {

    private int no;
    private BoyNode next;

    public BoyNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public BoyNode getNext() {
        return next;
    }

    public void setNext(BoyNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "BoyNode{" +
                "no=" + no +
                '}';
    }
}

