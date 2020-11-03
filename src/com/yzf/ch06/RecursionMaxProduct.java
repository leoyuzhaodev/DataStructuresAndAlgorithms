package com.yzf.ch06;

/**
 * @description: 设I是一个 n 位十进制整数。如果将 I 划分为 k 段，则可得到    k 个整数。
 * 这 k 个整数的乘积称为 I 的一个 k 乘积。试设计一个算法，对于给定的 I 和 k ，求出 I 的最大 k 乘积。
 * @author:leo_yuzhao
 * @date:2020/10/29
 */
public class RecursionMaxProduct {

    public static int getMaxProduct(String number, int segment) {
        if (segment <= 1) {
            return Integer.parseInt(number);
        } else {
            int maxVal = -1;
            // 控制当前级位数
            for (int i = 1; isSegmentRight(i, number, segment); i++) {
                int val = Integer.parseInt(number.substring(0, i));
                int tempVal = val * getMaxProduct(number.substring(i, number.length()), segment - 1);
                if (maxVal < tempVal) {
                    maxVal = tempVal;
                }
            }
            return maxVal;
        }
    }

    public static int getMaxProductV1(int number, int segment) {
        if (segment <= 1) {
            return number;
        } else {
            int maxVal = -1;
            String numberStr = number + "";
            // 控制当前级位数
            for (int i = 1; isSegmentRight(i, numberStr, segment); i++) {
                int val = Integer.parseInt(numberStr.substring(0, i));
                int tempVal = val * getMaxProduct(numberStr.substring(i, numberStr.length()), segment - 1);
                if (maxVal < tempVal) {
                    maxVal = tempVal;
                }
            }
            return maxVal;
        }
    }

    /**
     * 判断分段是否正确
     *
     * @return
     */
    public static boolean isSegmentRight(int i, String number, int segment) {
        return number.length() - i >= segment - 1;
    }

    public static void main(String[] args) {
        // int maxProduct = getMaxProduct("1234", 3);
        int maxProduct = getMaxProductV1(1234, 5);
        System.out.println(maxProduct);
    }
}
