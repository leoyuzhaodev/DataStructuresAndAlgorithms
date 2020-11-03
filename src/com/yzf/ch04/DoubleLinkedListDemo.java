package com.yzf.ch04;

/**
 * @description:双向链表
 * @author:leo_yuzhao
 * @date:2020/10/10
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleHeroNode heroNode1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode heroNode2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode heroNode3 = new DoubleHeroNode(3, "吴用", "智多星");
        DoubleHeroNode heroNode4 = new DoubleHeroNode(4, "林冲1", "豹子头1");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

// 测试插入并排序
//        doubleLinkedList.addAndSort1(heroNode3);
//        doubleLinkedList.addAndSort1(heroNode1);
//        doubleLinkedList.addAndSort1(heroNode2);
//        doubleLinkedList.addAndSort1(heroNode4);
//        doubleLinkedList.showList();

// 测试双向链表遍历
//        doubleLinkedList.showList();

// 测试更新
//        DoubleHeroNode newDoubleHeroNode = new DoubleHeroNode(4, "林冲", "豹子头");
//        System.out.println("更新前：");
//        doubleLinkedList.showList();
//        doubleLinkedList.updateHeroNode(newDoubleHeroNode);
//        System.out.println("更新后：");
//        doubleLinkedList.showList();

// 测试删除
//        System.out.println("删除前：");
//        doubleLinkedList.showList();
//        doubleLinkedList.deleteHeroNodeById(1);
//        doubleLinkedList.deleteHeroNodeById(4);
//        System.out.println("删除后：");
//        doubleLinkedList.showList();

    }
}

/**
 * 双向链表
 */
class DoubleLinkedList {

    private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    /**
     * 判断当前链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return head.next == null;
    }

    /**
     * 显示遍历节点
     */
    public void showList() {
        if (isEmpty()) {
            System.out.println("链表为空...");
            return;
        }
        DoubleHeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 增
     *
     * @param newNode
     */
    public void add(DoubleHeroNode newNode) {
        // 1，找出链表中最后一个节点
        DoubleHeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 2，在最后一个节点之后插入数据
        temp.next = newNode;
        newNode.pre = temp;
    }

    /**
     * 插入节点并排序
     *
     * @param newDoubleHeroNode
     */
    public void addAndSort(DoubleHeroNode newDoubleHeroNode) {
        if (isEmpty()) {
            head.next = newDoubleHeroNode;
            newDoubleHeroNode.pre = head;
            return;
        }
        // 寻找合适的插入点
        DoubleHeroNode cur = head.next;
        boolean isFind = false;
        boolean isTail = false;
        while (cur != null) {
            if (cur.no > newDoubleHeroNode.no) {
                isFind = true;
                break;
            } else if (cur.no == newDoubleHeroNode.no) {
                break;
            }
            if (cur.next == null) {
                isTail = true;
                break;
            }
            cur = cur.next;
        }
        // 执行插入
        if (isFind) {
            cur.pre.next = newDoubleHeroNode;
            newDoubleHeroNode.pre = cur.pre;
            newDoubleHeroNode.next = cur;
            cur.pre = newDoubleHeroNode;
        } else {
            if (isTail) {
                cur.next = newDoubleHeroNode;
                newDoubleHeroNode.pre = cur;
            } else {
                System.out.printf("插入节点ID:%d重复...\n", newDoubleHeroNode.no);
            }
        }

    }

    /**
     * 插入节点并排序
     *
     * @param newDoubleHeroNode
     */
    public void addAndSort1(DoubleHeroNode newDoubleHeroNode) {

        DoubleHeroNode temp = head;
        boolean isFind = false;
        boolean isRepeat = false;
        while (temp.next != null) {
            if (temp.next.no > newDoubleHeroNode.no) {
                isFind = true;
                break;
            } else if (temp.next.no == newDoubleHeroNode.no) {
                isRepeat = true;
                break;
            }
            temp = temp.next;
        }
        if (isFind) {
            newDoubleHeroNode.next = temp.next;
            temp.next.pre = newDoubleHeroNode;
            temp.next = newDoubleHeroNode;
            newDoubleHeroNode.pre = temp;
        } else {
            if (isRepeat) {
                System.out.printf("插入节点ID:%d重复...\n", newDoubleHeroNode.no);
            } else {
                temp.next = newDoubleHeroNode;
                newDoubleHeroNode.pre = temp;
            }
        }
    }

    /**
     * 改
     *
     * @param newHeroNode
     */
    public void updateHeroNode(DoubleHeroNode newHeroNode) {

        if (isEmpty()) {
            System.out.println("当前链表为空...");
            return;
        }

        // 查找并修改节点
        DoubleHeroNode cur = head.next;
        boolean isExists = false;
        while (cur != null) {
            if (cur.no == newHeroNode.no) {
                cur.name = newHeroNode.name;
                cur.nickName = newHeroNode.nickName;
                isExists = true;
                break;
            }
            cur = cur.next;
        }

        if (!isExists) {
            System.out.println("需要修改的节点不存在！");
        }
    }

    /**
     * 根据节点 id 删除节点
     *
     * @param no
     */
    public void deleteHeroNodeById(int no) {
        // 判断链表是否为空
        if (this.isEmpty()) {
            System.out.println("当前链表为空...");
            return;
        }
        // 寻找删除节点的前一个节点
        DoubleHeroNode cur = head.next;
        boolean flag = false;
        while (cur != null) {
            if (cur.no == no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        // 执行删除
        if (flag) {
            // 剥离删除的节点
            cur.pre.next = cur.next;
            if (cur.next != null) {
                cur.next.pre = cur.pre;
            }
        } else {
            System.out.println("未找到需要删除的节点...");
        }
    }
}

/**
 * 双向英雄节点
 */
class DoubleHeroNode {
    // 编号
    public int no;
    // 姓名
    public String name;
    // 昵称
    public String nickName;
    // 下一个节点
    public DoubleHeroNode next;
    // 上一个节点
    public DoubleHeroNode pre;

    public DoubleHeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
