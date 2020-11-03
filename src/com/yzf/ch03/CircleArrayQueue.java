package com.yzf.ch03;

/**
 * @description:数组模拟环形队列
 * @author:leo_yuzhao
 * @date:2020/9/23
 */
public class CircleArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int array[];

    /**
     * 构造函数
     *
     * @param maxSize
     */
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[this.maxSize];
        this.rear = 0;
        this.front = -1;
    }

    /**
     * 队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return rear == front;
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == -1;
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
        int temp = array[front];
        front = (front + 1) % maxSize;
        if (front == rear) {
            front = -1;
        }
        return temp;
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
        array[rear] = number;
        if (front == -1) {
            front = rear;
        }
        rear = (rear + 1) % maxSize;
    }

    /**
     * 显示队列的数据
     */
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        int i = front;
        do {
            System.out.printf("array[%d]=%d\t", i, array[i]);
            i = (i + 1) % maxSize;
        } while (i != rear);
        System.out.println();
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
        return array[front];
    }

}
