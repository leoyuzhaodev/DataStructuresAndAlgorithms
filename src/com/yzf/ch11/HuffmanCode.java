package com.yzf.ch11;

import javax.annotation.Generated;
import java.util.*;

/**
 * @description: 赫夫曼编码
 * @author:leo_yuzhao
 * @date:2020/11/15
 */
public class HuffmanCode {

    public static void main(String[] args) {
        test4();
    }

    /**
     * 测试生成Huffman解码
     */
    public static void test2() {
        String str = "i like like like java do you like a java";
        byte[] bytes = huffmanZip(str);
    }

    /**
     * 测试生成Huffman编码
     */
    public static void test1() {
        String str = "i like like like java do you like a java";
        String huffmanCode = getHuffmanCode(str);
        System.out.println("huffmanCode:" + huffmanCode);
    }

    public static void test3() {
        String str = "i like like like java do you like a java";
        Map<Byte, String> huffmanCodeMap = getHuffmanCodeMap(str);
        String huffmanCode = generateHuffmanCode(str, huffmanCodeMap);
        System.out.println(huffmanCode.length());
    }

    /**
     * 测试还原 huffmanCode 的正确性
     */
    public static void test4() {
        String str = "i like like like java do you like a java";
        String huffmanCode = getHuffmanCode(str);
        byte[] bytes = huffmanZip(str);
        String uncompressStr = getHuffmanCodeFromByte(bytes);
        String str1 = getOriginalStrFromHuffmanMap(uncompressStr, getHuffmanCodeMap(str));
        System.out.println("str1:" + str1);
        System.out.println(str.equals(str1));

    }

    /**
     * 将赫夫曼编码后的byte[]->二进制字符串（赫夫曼编码后的字符串）
     *
     * @param huffmanByteZipArray
     * @return
     */
    public static String getHuffmanCodeFromByte(byte[] huffmanByteZipArray) {
        // 1，得到最后一位的长度
        int bLength = huffmanByteZipArray.length;
        int endLength = huffmanByteZipArray[bLength - 1];
        // 2，遍历压缩数组，将每一个元素生成对应的二进制
        StringBuilder binaryStr = new StringBuilder();
        String tempBinary = null; // 临时存储截取的二进制
        int tempLength = 8; // 截取字符串的长度
        int ele = -1;
        for (int i = 0; i < bLength - 1; i++) {
            ele = huffmanByteZipArray[i];
            if (i == bLength - 2) {
                ele |= ((int) Math.pow(2, endLength));
                tempLength = endLength;
            } else {
                if (ele >= 0) {
                    ele = ele | 256;
                }
            }
            tempBinary = Integer.toBinaryString(ele);
            tempBinary = tempBinary.substring(tempBinary.length() - tempLength);
            binaryStr.append(tempBinary);
        }
        return binaryStr.toString();
    }

    /**
     * 将赫夫曼编码后的字符串根据赫夫曼编码表还原成原来的字符串
     *
     * @param huffmanCode
     * @param huffmanMap
     * @return
     */
    public static String getOriginalStrFromHuffmanMap(String huffmanCode, Map<Byte, String> huffmanMap) {

        // 1，Map<Byte,String> -> Map<String,Byte>
        Map<String, Byte> huffmanMap1 = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanMap.entrySet()) {
            huffmanMap1.put(entry.getValue(), entry.getKey());
        }
        // 2，遍历HuffmanCode反向生成原来的字符串
        int hcLength = huffmanCode.length();
        StringBuilder originalStr = new StringBuilder();
        int endIndex = 0;
        boolean flag = false; // 匹配标识
        for (int start = 0; start < hcLength; ) {
            endIndex = start + 1;
            while (endIndex <= hcLength) {
                Byte originalChar = huffmanMap1.get(huffmanCode.substring(start, endIndex));
                if (originalChar != null) {
                    originalStr.append(Character.toChars(originalChar));
                    flag = true;
                    break;
                }
                endIndex++;
            }
            if (flag) {
                start = endIndex;
            } else {
                start += 1;
            }
        }
        return originalStr.toString();
    }


    /**
     * 对使用赫夫曼编码后的字符串进行压缩
     *
     * @param originalStr
     * @return
     */
    public static byte[] huffmanZip(String originalStr) {
        // 1，根据原始字符串获取赫夫曼编码后的字符串
        String huffmanCode = getHuffmanCode(originalStr);

        // 2，将编码后的字符串8位一个byte存放在byte[]中，编码后的字符串长度获取压缩byte[]的长度
        int zipByteArrayLen = (huffmanCode.length() + 7) / 8 + 1;

        // 3，开始压缩
        byte[] zipByteArray = new byte[zipByteArrayLen];
        int zipByteArrayIndex = 0;
        StringBuilder stringBuilder = new StringBuilder(huffmanCode);
        String temp = null;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            if (i + 8 > stringBuilder.length()) {
                temp = stringBuilder.substring(i);
            } else {
                temp = stringBuilder.substring(i, i + 8);
            }
            zipByteArray[zipByteArrayIndex++] = (byte) Integer.parseInt(temp, 2);
        }

        // 4,存储赫夫曼编码后的字符串，不足8位的长度
        zipByteArray[zipByteArrayLen - 1] = (byte) (huffmanCode.length() % 8);

        return zipByteArray;
    }

    // 1，获取编码
    public static String getHuffmanCode(String originalStr) {

        if (originalStr == null && originalStr.trim().length() == 0) {
            return null;
        }

        // 1，将原生string生成 字符 与 权重(出现次数) 的码表，并将该码表构建成 List<CodeNode>
        List<CodeNode> codeNodes = getCharNode(originalStr);

        // 2，将 codeNodes 转化成赫夫曼树
        CodeNode root = getHuffmanTree(codeNodes);

        // 3，根据树生成赫夫曼编码表
        Map<Byte, String> huffmanCodeMap = getHuffmanCodeMap(root);

        // 4，将原生字符串根据编码转化成为对应的字符串
        String huffmanCode = generateHuffmanCode(originalStr, huffmanCodeMap);

        // 5，返回数据
        return huffmanCode;
    }


    /**
     * 根据赫夫曼树生成赫夫曼码表
     *
     * @param originalStr
     * @return
     */
    private static Map<Byte, String> getHuffmanCodeMap(String originalStr) {

        if (originalStr == null) {
            return null;
        }

        // 1，将原生string生成 字符 与 权重(出现次数) 的码表，并将该码表构建成 List<CodeNode>
        List<CodeNode> codeNodes = getCharNode(originalStr);

        // 2，生成Huffman树
        CodeNode root = getHuffmanTree(codeNodes);

        // 3，根据Huffman树生成，编码表
        if (root == null) {
            return null;
        } else {
            Map<Byte, String> map = new HashMap<>();
            StringBuilder stringBuilder = new StringBuilder("");
            return getHuffmanCodeMap(map, root, stringBuilder);
        }
    }


    /**
     * 将原生字符串根据编码转化成为对应的字符串
     *
     * @param originalStr
     * @param huffmanCodeMap
     * @return
     */
    private static String generateHuffmanCode(String originalStr, Map<Byte, String> huffmanCodeMap) {
        StringBuilder huffmanCodeStr = new StringBuilder("");
        for (Byte code : originalStr.getBytes()) {
            huffmanCodeStr.append(huffmanCodeMap.get(code));
        }
        return huffmanCodeStr.toString();
    }

    /**
     * 根据赫夫曼树生成赫夫曼码表
     *
     * @param root
     * @return
     */
    private static Map<Byte, String> getHuffmanCodeMap(CodeNode root) {

        if (root == null) {
            return null;
        } else {
            Map<Byte, String> map = new HashMap<>();
            StringBuilder stringBuilder = new StringBuilder("");
            return getHuffmanCodeMap(map, root, stringBuilder);
        }
    }

    /**
     * 根据赫夫曼树生成赫夫曼码表
     *
     * @param map
     * @param node
     * @param stringBuilder
     * @return
     */
    private static Map<Byte, String> getHuffmanCodeMap(Map<Byte, String> map, CodeNode node, StringBuilder stringBuilder) {

        if (node.getData() != null) {
            map.put(node.getData(), stringBuilder.toString());
        }

        // 左递归
        if (node.getLeft() != null) {
            getHuffmanCodeMap(map, node.getLeft(), stringBuilder.append("0"));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        // 右递归
        if (node.getRight() != null) {
            getHuffmanCodeMap(map, node.getRight(), stringBuilder.append("1"));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return map;
    }

    /**
     * 将 List<CodeNode>转化成为 Huffman树
     *
     * @param codeNodes
     * @return
     */
    private static CodeNode getHuffmanTree(List<CodeNode> codeNodes) {

        while (codeNodes.size() > 1) {
            // 1,排序
            Collections.sort(codeNodes);

            // 2,取出前两个元素
            CodeNode left = codeNodes.get(0);
            CodeNode right = codeNodes.get(1);

            // 3,构建树
            CodeNode fatherNode = new CodeNode(null, left.getWeight() + right.getWeight());
            fatherNode.setLeft(left);
            fatherNode.setRight(right);

            // 4,重置List<CodeNode>
            codeNodes.remove(left);
            codeNodes.remove(right);
            codeNodes.add(fatherNode);
        }

        return codeNodes.get(0);
    }

    /**
     * 将原生string生成 字符 与 权重(出现次数) 的码表，并将该码表构建成 List<CodeNode>
     * [CodeNode{data:97,weight:1}]
     *
     * @param originalStr
     * @return
     */
    private static List<CodeNode> getCharNode(String originalStr) {
        byte[] bytes = originalStr.getBytes();

        // 遍历字符串获取，得到每个字符串出现的次数，从而生成对应的码表
        Map<Byte, Integer> codeTimesMap = new HashMap<>();
        for (byte code : bytes) {
            Integer count = codeTimesMap.get(code);
            if (count == null) {
                codeTimesMap.put(code, 1);
            } else {
                codeTimesMap.put(code, count + 1);
            }
        }

        // 根据字符出现次数的码表，生成List<CodeNode>
        List<CodeNode> codeNodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : codeTimesMap.entrySet()) {
            codeNodes.add(new CodeNode(entry.getKey(), entry.getValue()));
        }

        return codeNodes;
    }

}

class CodeNode implements Comparable<CodeNode> {
    private Byte data;
    private int weight;
    private CodeNode left;
    private CodeNode right;

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public CodeNode getLeft() {
        return left;
    }

    public void setLeft(CodeNode left) {
        this.left = left;
    }

    public CodeNode getRight() {
        return right;
    }

    public void setRight(CodeNode right) {
        this.right = right;
    }

    public CodeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CodeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(CodeNode o) {
        // 注意该语句，该语句决定了 List<CodeNode> 的排序顺序
        return this.weight - o.weight;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}