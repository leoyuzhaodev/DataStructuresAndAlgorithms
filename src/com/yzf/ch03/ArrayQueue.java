package com.yzf.ch03;

/**
 * @description:数组模拟队列
 * @author:leo_yuzhao
 * @date:2020/9/23
 */
public class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int array[];

    /**
     * 构造函数
     *
     * @param maxSize
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[this.maxSize];
        this.rear = -1;
        this.front = -1;
    }

    /**
     * 队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 显示队列的数据
     */
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        for (int i = (front == -1 ? 0 : front + 1); i <= rear; i++) {
            System.out.printf("%d\t", array[i]);
        }
        System.out.println();
    }

    /**
     * 从队列取数据
     *
     * @return
     */
    public int getData() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        return array[++front];
    }

    /**
     * 显示队列的头部
     *
     * @return
     */
    public int showHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        return array[front + 1];
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 往队列中添加数据
     *
     * @param number
     */
    public void addQueue(int number) {
        if (isFull()) {
            throw new RuntimeException("队列已满...");
        }
        array[++rear] = number;
    }

}
