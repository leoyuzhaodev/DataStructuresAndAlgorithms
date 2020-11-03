package com.yzf.ch05;

import java.util.Scanner;

/**
 * @description:使用数组实现栈功能
 * @author:leo_yuzhao
 * @date:2020/10/12
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
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
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println("pop:" + arrayStack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "list":
                    arrayStack.list();
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

class ArrayStack {

    // 栈的最大容量
    private int maxSize;
    // 栈顶指针
    private int top;
    // 栈存储
    int array[];

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.array = new int[this.maxSize];
    }

    /**
     * 判断栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 压栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满...");
            return;
        }
        top++;
        array[top] = value;
    }

    /**
     * 弹栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空...");
        }
        int temp = array[top];
        top--;
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
        for (int i = top; i >= 0; i--) {
            System.out.printf("array[%d]=%d\n", i, array[i]);
        }
    }

}