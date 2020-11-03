package com.yzf.ch05;

import com.sun.deploy.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @description:逆波兰表达式(后缀表达式)计算器，支持多位数，小数，小括号，+ - * / 运算
 * @author:leo_yuzhao
 * @date:2020/10/14
 */
public class PolandNotationV2 {
    public static void main(String[] args) {
        // 中缀表达式：(31+5)*2-1 = 15 => 31 5 + 2 * 1 -
        // 中缀表达式：(3+52)*2+10-8*2/2 => 3 52 + 2 * 10 + 8 2 * 2 / -
        // 1,将中缀表达式转化为后缀表达式
        String suffixExpression = toSuffixExpression("1.7+((2+3.5)*4.7)-5.6");
        // 2,将后缀表达式拆成为 list
        List<String> suffixList = getSuffixList(suffixExpression);
        // 3,根据后缀表达式List 计算结果
        double result = calSuffixExpressionResult(suffixList);
        System.out.printf("suffixExpression:%s,result:%.2f", suffixExpression, result);
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param infixExpression
     * @return
     */
    private static String toSuffixExpression(String infixExpression) {

        // 0，对中缀表达式进行处理
        infixExpression = handleInfixExpression(infixExpression);

        // 1，将中缀表达式存入 list 中
        List<String> infixList = getInfixList(infixExpression);
        // 运算符栈
        Stack<String> s1 = new Stack<>();
        // 存储中间结果栈
        Stack<String> s2 = new Stack<>();

        // 2，遍历 list 进行转化
        for (int i = 0; i < infixList.size(); i++) {

            // 2.1，初始化需要使用的变量
            String temp = infixList.get(i);

            // 2.2，判断当前元素是数字还是其他
            if (isNumber(temp.charAt(0) + "")) {
                // 数字：将数字压如 s2 中
                s2.push(temp);

            } else {

                char oper = temp.charAt(0);

                // 判断当前元素是运算符还是括号
                if (isBracket(oper)) {
                    // 括号：判断是 左括号 还是
                    if (isRightBracket(oper)) {
                        // 右括号：右括号则依次弹出 s1 栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                        while (!isLeftBracket(s1.peek().charAt(0))) {
                            s2.push(s1.pop());
                        }
                        // 弹出左括号
                        s1.pop();
                    } else {
                        // 左括号：
                        s1.push(temp);
                    }
                } else {
                    // 运算符：如果s1为空或者栈顶运算符为左括号，则直接将此运算符入栈
                    // 否则若优先级比栈顶运算符高，也将压入 s1
                    // 否则将 s1 栈顶的运算符弹出并压入到 s2 中
                    if (s1.isEmpty() || isLeftBracket(s1.peek().charAt(0))) {
                        s1.push(temp);
                    } else if (!isPriorityLowOrEqual(oper, s1.peek().charAt(0))) {
                        s1.push(temp);
                    } else {
                        s2.push(s1.pop());
                        s1.push(temp);
                    }
                }
            }
        }

        // 3，将 s1 中剩余的运算符依次弹出并压入 s2
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        //4，依次弹出 s2 中的元素并输出，结果的逆序就为中缀表达式对应的后缀表达式
        List<String> suffixExpressionList = new ArrayList<>();
        while (!s2.isEmpty()) {
            suffixExpressionList.add(s2.pop());
        }
        Collections.reverse(suffixExpressionList);

        // 返回结果
        return StringUtils.join(suffixExpressionList, " ");
    }

    /**
     * 对中缀表达式进行处理
     *
     * @param infixExpression
     * @return
     */
    private static String handleInfixExpression(String infixExpression) {
        // 1，判断表达式是否为空
        if (infixExpression == null || infixExpression.trim().length() == 0) {
            throw new RuntimeException("中缀表达式为空...");
        }

        // 2，替换表达式中的任何空白字符，包括 空格，制表符，换页符，[\f\n\r\t\v]
        infixExpression = infixExpression.replaceAll("\\s+", "");

        // 3，查看表达式中是否存在 运算符，括号，数字，点 以外的字符
        for (int i = 0; i < infixExpression.length(); i++) {
            char temp = infixExpression.charAt(i);
            if (!(isPoint(temp) || isNumber(temp + "") || isBracket(temp) || isOper(temp))) {
                throw new RuntimeException("中缀表达式存在非法字符：" + temp);
            }
        }

        return infixExpression;
    }

    /**
     * 根据后缀表达式 List 计算结果
     *
     * @param suffixList
     * @return
     */
    private static double calSuffixExpressionResult(List<String> suffixList) {
        // 1，初始变量
        Stack<String> stack = new Stack<>();
        // 2，从左到右扫描后缀表达式
        for (String temp : suffixList) {
            // 判断当前 temp 是数字还是运算符
            if (isNumber(temp)) {
                // 数字：直接入栈
                stack.push(temp);
            } else {
                // 运算符：从栈中弹出两个数，利用当前运算符进行运算
                double number1 = Double.parseDouble(stack.pop());
                double number2 = Double.parseDouble(stack.pop());
                stack.push(cal(number1, number2, temp.charAt(0)) + "");
            }
        }
        return Double.parseDouble((stack.pop()));
    }

    /**
     * 判断当前字符是不是数字
     *
     * @param temp
     * @return
     */
    private static boolean isNumber(String temp) {
        return temp.matches("[0-9]+(.[0-9]+)?");
    }

    /**
     * 将中缀表达式转化为 list
     *
     * @param infixExpression
     * @return
     */
    private static List<String> getInfixList(String infixExpression) {
        int index = 0;
        StringBuilder numberStr = new StringBuilder("");
        List<String> expressionList = new ArrayList<>();
        int expLength = infixExpression.length();

        while (index != expLength) {
            String temp = infixExpression.charAt(index) + "";
            // 判断当前字符是否是数字，或者是否是小数点
            if (isNumber(temp) || isPoint(temp.charAt(0))) {
                // 是：继续向后遍历拼接数字
                // 清空 numberStr
                numberStr.delete(0, numberStr.length());
                do {
                    temp = infixExpression.charAt(index) + "";
                    if (isNumber(temp) || isPoint(temp.charAt(0))) {
                        numberStr.append(temp);
                    } else {
                        break;
                    }
                } while (++index < expLength);
                expressionList.add(numberStr.toString());
            } else {
                // 否：直接将符号放入容器
                expressionList.add(temp);
                index++;
            }
        }
        return expressionList;
    }

    /**
     * 将中缀表达式拆分成为 list
     *
     * @param suffixExpression
     * @return
     */
    private static List<String> getSuffixList(String suffixExpression) {
        String[] suffixArray = suffixExpression.split(" ");
        List<String> suffixList = new ArrayList<>();
        for (String cha : suffixArray) {
            suffixList.add(cha);
        }
        return suffixList;
    }

    /**
     * 计算结果
     *
     * @param number1 先弹出
     * @param number2 后弹出
     * @param oper
     * @return
     */
    public static double cal(double number1, double number2, int oper) {
        double res = 0D;
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
        return new BigDecimal(res).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 判断当前符号是否是括号
     *
     * @param temp
     * @return
     */
    public static boolean isBracket(int temp) {
        return isRightBracket(temp) || isLeftBracket(temp);
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
        } else {
            throw new RuntimeException("运算符不合法:" + (char) oper);
        }
    }

    /**
     * 判断字符是否是小数点
     *
     * @param oper
     * @return
     */
    public static boolean isPoint(int oper) {
        return oper == '.';
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
}
