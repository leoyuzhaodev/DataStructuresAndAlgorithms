package com.yzf.ch03;

import java.util.Scanner;

/**
 * @description:队列测试
 * @author:leo_yuzhao
 * @date:2020/9/23
 */
public class CircleArrayQueueTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);
        char key;
        boolean loop = true;

        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            try {
                switch (key) {
                    case 's':
                        arrayQueue.show();
                        break;
                    case 'e':
                        loop = false;
                        break;
                    case 'a':
                        System.out.println("请输入数字：");
                        int number = scanner.nextInt();
                        arrayQueue.addQueue(number);
                        break;
                    case 'h':
                        System.out.println("头数据："+arrayQueue.showHead());
                        break;
                    case 'g':
                        System.out.println("得到数据："+arrayQueue.getData());
                        break;
                    default:
                        System.out.println("输入错误...");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("退出程序...");
    }
}
