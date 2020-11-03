package com.yzf.ch06;

/**
 * @description:递归：阶乘
 * @author:leo_yuzhao
 * @date:2020/10/16
 */
public class RecursionDemo01 {
    public static void main(String[] args) {
        System.out.println(getN(3));
    }

    /**
     * 递归的方式求阶乘
     *
     * @param n
     * @return
     */
    public static int getN(int n) {
        if (n == 1) {
            return 1;
        } else {
            // 重要原则：必须向递归结束条件逼近，否则会造成 “死龟”
            return n * getN(n - 1);
        }
    }
}
