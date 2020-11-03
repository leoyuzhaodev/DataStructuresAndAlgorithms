package com.yzf.ch04;

import java.util.Stack;

/**
 * @description:单链表
 * @author:leo_yuzhao
 * @date:2020/9/27
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲1", "豹子头1");
        HeroNode heroNode5 = new HeroNode(5, "林冲2", "豹子头2");
        HeroNode heroNode6 = new HeroNode(6, "林冲3", "豹子头3");

        SingleNodeList singleNodeList = new SingleNodeList();
        singleNodeList.add(heroNode1);
        singleNodeList.add(heroNode2);
        singleNodeList.add(heroNode3);
        singleNodeList.add(heroNode4);
// 测试查找倒数第k个节点
//        System.out.println(singleNodeList.searchLastNode(1));

// 测试逆序遍历
//        singleNodeList.reverseList();

// 测试统计有效节点个数
//        System.out.println("有效节点个数：" + singleNodeList.count());

// 测试节点删除
        System.out.println("删除前:");
        singleNodeList.showList();
        System.out.println("删除后:");
        singleNodeList.deleteHeroNodeById(3);
        singleNodeList.showList();

// 测试合并有序插入
//        SingleNodeList singleNodeList1 = new SingleNodeList();
//        SingleNodeList singleNodeList2 = new SingleNodeList();
//
//        // 初始化有序链表 1
//        singleNodeList1.addAndSort(heroNode2);
//        singleNodeList1.addAndSort(heroNode3);
//        singleNodeList1.addAndSort(heroNode4);
//
//        // 初始化有序链表 2
//        // singleNodeList2.addAndSort(heroNode3);
//        singleNodeList2.addAndSort(heroNode5);
//        singleNodeList2.addAndSort(heroNode1);
//        singleNodeList2.addAndSort(heroNode6);
//
//        System.out.println("list1:");
//        singleNodeList1.showList();
//
//        System.out.println("list2:");
//        singleNodeList2.showList();
//
//        System.out.println("list1,list2 有序合并：");
//        singleNodeList1.sortInsertAllLinkedList(singleNodeList2);
//        singleNodeList1.showList();

// 测试有序插入
//        singleNodeList.addAndSort(heroNode1);
//        singleNodeList.addAndSort(heroNode3);
//        singleNodeList.addAndSort(heroNode2);
//        singleNodeList.addAndSort(heroNode4);
//        singleNodeList.showList();

// 测试更新
//        HeroNode updateHeroNode = new HeroNode(2, "小卢俊义", "小玉麒麟");
//        singleNodeList.updateHeroNode(updateHeroNode);
//        System.out.println("===========更新后===========");
//        singleNodeList.showList();

// 测试链表反转
//        System.out.println("反转前：");
//        singleNodeList.showList();
//        singleNodeList.reverse();
//        System.out.println("反转后：");
//        singleNodeList.showList();

    }
}

class SingleNodeList {
    // 头结点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    // 判断当前链表是否为空
    public boolean isEmpty() {
        return head.next == null;
    }

    // 显示遍历节点
    public void showList() {
        HeroNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空...");
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 插入节点
    public void add(HeroNode newNode) {
        // 1，找出链表中最后一个节点
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 2，在最后一个节点之后插入数据
        temp.next = newNode;
    }

    // 有序插入节点
    public void addAndSort(HeroNode newNode) {
        HeroNode temp = head;
        boolean isRepeat = false;
        // 找出节点的插入位置
        while (temp.next != null) {
            if (temp.next.no > newNode.no) {
                break;
            } else if (temp.next.no == newNode.no) {
                isRepeat = true;
                System.out.printf("插入结点 id:%d 重复!\n", newNode.no);
                break;
            }
            temp = temp.next;
        }
        // 插入节点
        if (!isRepeat) {
            // 判断 temp 是否为尾结点
            if (temp.next == null) {
                temp.next = newNode;
            } else {
                newNode.next = temp.next;
                temp.next = newNode;
            }
        }
    }

    // 更新节点
    public void updateHeroNode(HeroNode newHeroNode) {
        HeroNode temp = head;
        boolean isExists = false;
        while (temp.next != null) {
            if (temp.next.no == newHeroNode.no) {
                temp.next.name = newHeroNode.name;
                temp.next.nickName = newHeroNode.nickName;
                isExists = true;
                break;
            }
            temp = temp.next;
        }
        if (!isExists) {
            System.out.println("需要修改的节点不存在！");
        }
    }

    // 根据节点 id 删除节点
    public void deleteHeroNodeById(int no) {

        // 判断链表是否为空
        if (this.isEmpty()) {
            System.out.println("当前链表为空...");
            return;
        }

        // 寻找删除节点的前一个节点
        HeroNode temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 执行删除
        if (flag) {
            // 剥离删除的节点
            temp.next = temp.next.next;
        } else {
            System.out.println("未找到需要删除的节点...");
        }

    }

    // 统计链表节点个数【百度面试题】
    public int count() {
        if (isEmpty()) {
            return 0;
        } else {
            int count = 0;
            HeroNode temp = head.next;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }
    }

    // 查找链表中倒数第  K 个节点【新浪面试题】
    public HeroNode searchLastNode(int k) {

        int size = count();

        if (k <= 0 || k > size) {
            System.out.println("k取值的区间应该为：[1,链表的有效节点个数]");
            return null;
        }

        if (isEmpty()) {
            System.out.println("当前链表为空...");
            return null;
        }

        // 查找节点
        int count = 0;
        int key = size - k + 1;
        HeroNode temp = head.next;
        while (temp != null) {
            count++;
            if (count == key) {
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }

    // 链表反转【腾讯面试题】
    public void reverse() {
        // 链表为空或者链表只有一个元素时，直接返回当前链表
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 当前遍历节点
        HeroNode cur = head.next;
        // 反转链表的头节点
        HeroNode RH = new HeroNode(0, null, null);
        // 下一个链表的指向
        HeroNode next = null;
        while (cur != null) {
            next = cur.next;
            // 将取出来的节点，插入到反转链表的最前方
            cur.next = RH.next;
            RH.next = cur;
            // 移动当前节点
            cur = next;
        }
        // 改变头节点指向
        head.next = RH.next;
    }

    // 反向遍历链表
    public void reverseList() {
        if (isEmpty()) {
            System.out.println("当前链表为空...");
            return;
        } else {

            // 将链表元素加入到栈中
            HeroNode temp = head.next;
            Stack<HeroNode> stack = new Stack<>();
            while (temp != null) {
                stack.push(temp);
                temp = temp.next;
            }

            // 遍历栈中的元素
            while (!stack.empty()) {
                System.out.println(stack.pop());
            }

        }
    }

    // 合并两个有序链表合并后的链表仍然有序
    public void sortInsertAllLinkedList(SingleNodeList insertedList) {
        // 如果当前链表或者插入链表为空，就直接返回
        if (insertedList.isEmpty()) {
            return;
        } else if (this.isEmpty()) {
            this.head = insertedList.getHead();
            return;
        }

        HeroNode insertedListHead = insertedList.getHead();
        HeroNode insertedCur = insertedListHead.next;
        HeroNode next = null;
        while (insertedCur != null) {
            next = insertedCur.next;
            {
                HeroNode location = null;
                HeroNode cur = head;
                boolean canInsert = true;
                // 寻找合适的插入位置
                while (cur.next != null) {
                    if (cur.next.no > insertedCur.no) {
                        location = cur;
                        break;
                    } else if (cur.next.no == insertedCur.no) {
                        canInsert = false;
                        break;
                    }
                    cur = cur.next;
                }
                if (canInsert) {
                    // 插入待插入的节点
                    if (location == null) {
                        // 表示待插入节点（insertedCur），应该插入到链表的最后
                        cur.next = insertedCur;
                        insertedCur.next = null;
                    } else {
                        insertedCur.next = cur.next;
                        cur.next = insertedCur;
                    }
                } else {
                    // 当前节点重复，无法插入
                }
            }
            insertedCur = next;
        }
    }
}

/**
 * 英雄节点
 */
class HeroNode {
    // 编号
    public int no;
    // 姓名
    public String name;
    // 昵称
    public String nickName;
    // 下一个结点
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

