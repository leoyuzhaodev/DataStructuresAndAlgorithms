package com.yzf.ch05;

/**
 * @description:使用栈实现计算器
 * @author:leo_yuzhao
 * @date:2020/10/13
 */
public class Calculator {
    public static void main(String[] args) {
        String exp = "106+20*3-90+1"; // 106-174=-68
        System.out.printf("%s = %d", exp, calculate(exp));
    }

    /**
     * 计算表达式的结果
     *
     * @param expression
     * @return
     */
    public static int calculate(String expression) {

        if (expression == null || expression.trim().length() == 0) {
            throw new RuntimeException("表达式为空...");
        }

        // 1，前期变量准备
        CalArrayStack numberStack = new CalArrayStack(20);
        CalArrayStack operStack = new CalArrayStack(20);
        int expLength = expression.length();
        int index = 0;  // 遍历指针
        int number1 = 0;
        int number2 = 0;
        int temp = 0;
        StringBuilder numbersStr = new StringBuilder("");

        // 2，中期数字字符入栈
        while (true) {
            // 2.1，获取到字符串中的数字或者符号
            temp = expression.charAt(index);

            // 2.2，判断取出来的字符是操作符还是数字
            if (CalArrayStack.isOper(temp)) {

                // 2.2.1，判断符号栈是否为空
                if (operStack.isEmpty()) {
                    // 空：直接入栈
                    operStack.push(temp);
                } else {
                    // 不空：将当前操作符与栈顶操作符进行比较
                    if (CalArrayStack.isPriorityLowOrEqual(temp, operStack.peek())) {
                        // 小于等于：在数字栈中弹出两个数字，在符号栈中弹出一个符号，
                        // 进行运算得到结果，将结果存入数字栈，将当前符号存入符号栈
                        number1 = numberStack.pop();
                        number2 = numberStack.pop();
                        numberStack.push(CalArrayStack.cal(number1, number2, operStack.pop()));
                        operStack.push(temp);
                    } else {
                        // 大于等于：直接入栈
                        operStack.push(temp);
                    }
                }
            } else {

                numbersStr.append(temp - 48);

                // 判断该数字是否是表达式的最后一位
                if (index == expLength - 1) {
                    // 是：入栈
                    numberStack.push(Integer.parseInt(numbersStr.toString()));
                    // 清空 numberStr
                    numbersStr.delete(0, numbersStr.length());
                } else {
                    // 判断当前指针指向位置的下一个位置是不是字符
                    int nextChar = expression.charAt(index + 1);
                    if (CalArrayStack.isOper(nextChar)) {
                        // 是：入栈
                        numberStack.push(Integer.parseInt(numbersStr.toString()));
                        // 清空 numberStr
                        numbersStr.delete(0, numbersStr.length());
                    }
                }
            }

            // 移动扫描指针
            index++;

            // 判断退出
            if (index >= expLength) {
                break;
            }

        }

        // 3，后期弹栈计算结果
        while (true) {
            if (operStack.isEmpty()) {
                break;
            } else {
                number1 = numberStack.pop();
                number2 = numberStack.pop();
                numberStack.push(CalArrayStack.cal(number1, number2, operStack.pop()));
            }
        }

        // 最终结果存放在 numberStack 的栈顶，将结果弹出即可
        return numberStack.pop();
    }

    /**
     * 计算表达式的结果()
     *
     * @param expression
     * @return
     */
    public static int calculate1(String expression) {

        if (expression == null || expression.trim().length() == 0) {
            throw new RuntimeException("表达式为空...");
        }

        // 1，前期变量准备
        CalArrayStack numberStack = new CalArrayStack(20);
        CalArrayStack operStack = new CalArrayStack(20);
        int expLength = expression.length();
        int index = 0;  // 遍历指针
        int number1 = 0;
        int number2 = 0;
        int temp = 0;
        StringBuilder numbersStr = new StringBuilder("");

        // 2，中期数字字符入栈
        while (true) {
            // 2.1，获取到字符串中的数字或者符号
            temp = expression.charAt(index);

            // 2.2，判断取出来的字符是操作符还是数字
            if (CalArrayStack.isOper(temp)
                    || CalArrayStack.isRightBracket(temp)
                    || CalArrayStack.isLeftBracket(temp)) {

                // 2.2.1，判断符号栈是否为空
                if (operStack.isEmpty()) {
                    // 空：直接入栈
                    operStack.push(temp);
                } else {

                    // 判断左右括号
                    if (CalArrayStack.isLeftBracket(temp)) {
                        // 左括号：直接入栈
                        operStack.push(temp);
                    } else if (CalArrayStack.isRightBracket(temp)) {
                        // 右括号：进行计算消除括号
                        while (operStack.peek() != '(') {
                            number1 = numberStack.pop();
                            number2 = numberStack.pop();
                            numberStack.push(CalArrayStack.cal(number1, number2, operStack.pop()));
                        }
                    } else {
                        // 不空：将当前操作符与栈顶操作符进行比较
                        if (CalArrayStack.isPriorityLowOrEqual(temp, operStack.peek())) {
                            // 小于等于：在数字栈中弹出两个数字，在符号栈中弹出一个符号，
                            // 进行运算得到结果，将结果存入数字栈，将当前符号存入符号栈
                            number1 = numberStack.pop();
                            number2 = numberStack.pop();
                            numberStack.push(CalArrayStack.cal(number1, number2, operStack.pop()));
                            operStack.push(temp);
                        } else {
                            // 大于等于：直接入栈
                            operStack.push(temp);
                        }
                    }
                }
            } else {

                numbersStr.append(temp - 48);

                // 判断该数字是否是表达式的最后一位
                if (index == expLength - 1) {
                    // 是：入栈
                    numberStack.push(Integer.parseInt(numbersStr.toString()));
                    // 清空 numberStr
                    numbersStr.delete(0, numbersStr.length());
                } else {
                    // 判断当前指针指向位置的下一个位置是不是字符
                    int nextChar = expression.charAt(index + 1);
                    if (CalArrayStack.isOper(nextChar)
                            || CalArrayStack.isLeftBracket(nextChar)
                            || CalArrayStack.isRightBracket(nextChar)) {
                        // 是：入栈
                        numberStack.push(Integer.parseInt(numbersStr.toString()));
                        // 清空 numberStr
                        numbersStr.delete(0, numbersStr.length());
                    }
                }
            }

            // 移动扫描指针
            index++;

            // 判断退出
            if (index >= expLength) {
                break;
            }

        }

        // 3，后期弹栈计算结果
        while (true) {
            if (operStack.isEmpty()) {
                break;
            } else {
                number1 = numberStack.pop();
                number2 = numberStack.pop();
                numberStack.push(CalArrayStack.cal(number1, number2, operStack.pop()));
            }
        }

        // 最终结果存放在 numberStack 的栈顶，将结果弹出即可
        return numberStack.pop();
    }

}

class CalArrayStack {

    // 栈的最大容量
    private int maxSize;
    // 栈顶指针
    private int top;
    // 栈存储
    int array[];

    public CalArrayStack(int maxSize) {
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

    /**
     * 根据运算符得到优先级
     *
     * @param oper
     * @return
     */
    private static int getOperPriority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;
    }

    /**
     * 判断 oper1 的优先级是否小于 oper2 的优先级
     *
     * @param oper1
     * @param oper2
     * @return
     */
    public static boolean isPriorityLowOrEqual(int oper1, int oper2) {
        if (getOperPriority(oper1) <= getOperPriority(oper2)) {
            return true;
        }
        return false;
    }

    /**
     * 是否是运算符
     *
     * @param oper
     * @return
     */
    public static boolean isOper(int oper) {
        if (oper == '*' || oper == '/' || oper == '+' || oper == '-') {
            return true;
        }
        return false;
    }

    /**
     * 计算结果
     *
     * @param number1 先弹出
     * @param number2 后弹出
     * @param oper
     * @return
     */
    public static int cal(int number1, int number2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = number2 + number1;
                break;
            case '-':
                res = number2 - number1;
                break;
            case '*':
                res = number2 * number1;
                break;
            case '/':
                res = number2 / number1;
                break;
        }
        return res;
    }

    /**
     * 查看栈顶
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法查看栈顶...");
        }
        return array[top];
    }

    /**
     * 判断字符是否是右括号
     *
     * @param temp
     * @return
     */
    public static boolean isRightBracket(int temp) {
        return temp == ')';
    }

    /**
     * 判断字符是否是左括号
     *
     * @param temp
     * @return
     */
    public static boolean isLeftBracket(int temp) {
        return temp == '(';
    }

}