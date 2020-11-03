package com.yzf.ch04;

/**
 * @description:数组解决约瑟夫问题
 * @author:leo_yuzhao
 * @date:2020/10/11
 */
public class JosephuProblemByArray {
    public static void main(String[] args) {
        solveProblem(1, 3, 10);
    }

    public static void solveProblem(int startNo, int flag, int sum) {

        if (startNo < 0 || flag < 0 || flag > sum || startNo > sum - 1) {
            System.out.println("数据输入不合法...");
            return;
        }
        // 1，初始化数组
        int count = sum;
        int array[] = new int[sum];
        for (int i = 0; i < sum; i++) {
            array[i] = i + 1;
        }
        // 2，移动排除
        while (true) {
            // 移动
            int temp = 0;
            for (int i = 0; i < flag - 1; i++) {
                temp = ++startNo % sum;
                if (array[temp] == -1) {
                    i--;
                }
            }
            // 剔除元素
            System.out.println(array[temp]);
            array[temp] = -1;
            count--;
            // 移动到下一个有效节点
            while (true) {
                int index = ++temp % sum;
                if ((array[index]) != -1) {
                    startNo = index;
                    break;
                }
            }
            // 退出循环
            if (count == 1) {
                System.out.println("最后一个编号为：" + array[startNo]);
                break;
            }
        }
    }

}
