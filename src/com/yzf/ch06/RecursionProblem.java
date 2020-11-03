package com.yzf.ch06;

/**
 * @description:
 * @author:leo_yuzhao
 * @date:2020/10/17
 */
public class RecursionProblem {

    /*
    [
         [2],
        [3,4],
       [6,5,7],
      [4,1,8,3]
    ]
    */
    private int array[][] = new int[][]
            {
                    {2},
                    {3, 4},
                    {6, 5, 7},
                    {4, 1, 8, 3}
            };

    private int sum = 0;
    private int max = 0;

    public static void main(String[] args) {
        RecursionProblem recursionProblem = new RecursionProblem();
        recursionProblem.getSum(0, 0);
        System.out.println("max:"+recursionProblem.max);
    }

    public void getSum(int n, int col) {
        if (n == 4) {
            System.out.println(sum);
            if (max < sum) {
                max = sum;
            }
        } else {
            for (int i = col; i <= col + 1 && i < array[n].length; i++) {
                int value = array[n][i];
                sum += value;
                getSum(n + 1, i);
                sum -= value;
            }
        }
    }

}
