package com.yzf.ch05;

import java.util.Scanner;

/**
 * @description:利用单向链表实现栈功能
 * @author:leo_yuzhao
 * @date:2020/10/12
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(5);
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("push:压栈");
            System.out.println("pop:弹栈");
            System.out.println("list:展示栈");
            System.out.println("exit:退出程序");
            System.out.println("请选择操作：");
            String operation = scanner.next();
            switch (operation) {
                case "push":
                    System.out.println("请输入数字：");
                    int value = scanner.nextInt();
                    linkedListStack.push(new StackNode(value));
                    break;
                case "pop":
                    try {
                        System.out.println("pop:" + linkedListStack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "list":
                    linkedListStack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序已退出...");
    }
}

class LinkedListStack {

    private int maxSize;
    private int size;
    private StackNode head;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        size = 0;
        head = new StackNode(-1);
    }

    /**
     * 判断栈满
     *
     * @return
     */
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * 判断栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 压栈
     *
     * @param value
     */
    public void push(StackNode value) {
        if (isFull()) {
            System.out.println("栈已满...");
            return;
        }
        value.setNext(head.getNext());
        head.setNext(value);
        size++;
    }

    /**
     * 弹栈
     *
     * @return
     */
    public StackNode pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空...");
        }
        StackNode temp = head.getNext();
        head.setNext(temp.getNext());
        size--;
        return temp;
    }

    /**
     * 展示栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空...");
            return;
        }
        StackNode cur = head.getNext();
        while (cur != null) {
            System.out.println(cur);
            cur = cur.getNext();
        }
    }
}

class StackNode {
    private int value;
    private StackNode next;

    public StackNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "value=" + value +
                '}';
    }
}