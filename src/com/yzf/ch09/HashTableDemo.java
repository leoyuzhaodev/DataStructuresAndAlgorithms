package com.yzf.ch09;

import java.util.Scanner;

/**
 * @description:HashTableDemo
 * @author:leo_yuzhao
 * @date:2020/11/3
 */
public class HashTableDemo {

    public static void main(String[] args) {
        test5();
    }

    private static void test5() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        EmpHashTable empHashTable = new EmpHashTable(10);
        while (flag) {
            System.out.println("* ************************ *");
            System.out.println("1,添加(add)");
            System.out.println("2,删除(delete)");
            System.out.println("3,查找(find)");
            System.out.println("4,遍历(show)");
            System.out.println("5,退出(exit)");
            System.out.println("* ************************ *");
            System.out.println("请选择操作(add/delete/find/show)：");
            String opt = scanner.next();
            switch (opt) {
                case "add":
                    System.out.println("请输入id:");
                    int id = scanner.nextInt();
                    System.out.println("请输入姓名:");
                    String name = scanner.next();
                    empHashTable.addAndSort(new Emp(id, name, null));
                    break;
                case "delete":
                    System.out.println("请输入id:");
                    id = scanner.nextInt();
                    empHashTable.delete(id);
                    break;
                case "find":
                    System.out.println("请输入id:");
                    id = scanner.nextInt();
                    Emp emp = empHashTable.find(id);
                    System.out.println("查找结果：" + (emp == null ? "未找到" : emp));
                    break;
                case "show":
                    empHashTable.show();
                    break;
                case "exit":
                    flag = false;
                    System.out.println("正在退出......");
                    break;
                default:
                    System.out.println("操作输入有误!");
                    break;
            }
        }
    }

    public static void test1() {
        EmpLinkedList empLinkedList = new EmpLinkedList();
        empLinkedList.add(new Emp(1, "张三", "北京"));
        empLinkedList.add(new Emp(2, "李四", "上海"));
        empLinkedList.list();
    }

    public static void test2() {
        EmpLinkedList empLinkedList = new EmpLinkedList();
        empLinkedList.addAndSort(new Emp(2, "张三", "北京"));
        empLinkedList.addAndSort(new Emp(1, "李四", "上海"));
        empLinkedList.addAndSort(new Emp(3, "王五", "武汉"));
        empLinkedList.addAndSort(new Emp(5, "马六", "武汉"));
        empLinkedList.addAndSort(new Emp(0, "赵七", "武汉"));
        empLinkedList.list();
    }

    public static void test3() {
        EmpLinkedList empLinkedList = new EmpLinkedList();
        empLinkedList.addAndSort(new Emp(2, "张三", "北京"));
        empLinkedList.addAndSort(new Emp(1, "李四", "上海"));
        empLinkedList.addAndSort(new Emp(3, "王五", "武汉"));
        empLinkedList.addAndSort(new Emp(5, "马六", "武汉"));
        empLinkedList.addAndSort(new Emp(0, "赵七", "武汉"));
        System.out.println("原始链表");
        empLinkedList.list();
        System.out.println("删除id:0");
        empLinkedList.delete(0);
        empLinkedList.list();
        System.out.println("删除id:5");
        empLinkedList.delete(5);
        empLinkedList.list();
        System.out.println("删除id:3");
        empLinkedList.delete(3);
        empLinkedList.list();
        empLinkedList.delete(0);
    }

    public static void test4() {
        EmpLinkedList empLinkedList = new EmpLinkedList();
        empLinkedList.addAndSort(new Emp(2, "张三", "北京"));
        empLinkedList.addAndSort(new Emp(1, "李四", "上海"));
        empLinkedList.addAndSort(new Emp(3, "王五", "武汉"));
        empLinkedList.addAndSort(new Emp(5, "马六", "武汉"));
        empLinkedList.addAndSort(new Emp(0, "赵七", "武汉"));
        System.out.println(empLinkedList.find(2));
    }
}

class EmpHashTable {
    private int size;
    private EmpLinkedList[] empLinkedLists;

    public EmpHashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        // 初始化对象数组
        for (int i = 0; i < this.size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加
     *
     * @param emp
     */
    public void add(Emp emp) {
        this.empLinkedLists[getPosition(emp)].add(emp);
    }

    /**
     * 获取插入元素的位置
     *
     * @return
     */
    public int getPosition(Emp emp) {
        return emp.getId() % this.size;
    }

    /**
     * 获取插入元素的位置
     *
     * @return
     */
    public int getPosition(Integer id) {
        return id % this.size;
    }

    /**
     * 有序插入
     *
     * @param emp
     */
    public void addAndSort(Emp emp) {
        this.empLinkedLists[getPosition(emp)].addAndSort(emp);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Integer id) {
        this.empLinkedLists[getPosition(id)].delete(id);
    }


    // 查找
    public Emp find(Integer id) {
        return this.empLinkedLists[getPosition(id)].find(id);
    }

    /**
     * 遍历HashTable数据
     */
    public void show() {
        for (int i = 0; i < this.size; i++) {
            if (empLinkedLists[i].isEmpty()) {
                System.out.println("HashTable => index:" + i + " 为空");
            } else {
                System.out.print("HashTable => index:" + i + " ");
                empLinkedLists[i].list();
            }
        }
    }
}

/**
 * 链表
 */
class EmpLinkedList {

    private Emp head;

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    public Emp find(Integer id) {
        if (id == null) {
            System.out.println("id为空，无法查找!");
            return null;
        } else if (isEmpty()) {
            System.out.println("链表为空，无法查找!");
            return null;
        } else {
            Emp cur = head;
            while (cur != null) {
                if (cur.getId() == id) {
                    return cur;
                }
                cur = cur.getNext();
            }
            return null;
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Integer id) {
        if (id == null) {
            System.out.println("id为空，无法删除!");
            return;
        } else if (isEmpty()) {
            System.out.println("链表为空，无法删除!");
            return;
        } else {
            // 1，寻找删除位置
            Emp cur = head;
            Emp last = null;
            Emp temp = null;
            boolean flag = false;
            while (cur != null) {
                if (cur.getId() == id) {
                    flag = true;
                    break;
                }
                last = cur;
                cur = cur.getNext();
            }
            // 2，执行删除
            if (flag) {
                if (last == null) {
                    // 说明删除头节点
                    temp = head.getNext();
                    head.setNext(null);
                    head = temp;
                } else {
                    last.setNext(last.getNext().getNext());
                }
            } else {
                System.out.printf("未找到id：%d，的节点，无法删除！\n", id);
            }
        }

    }

    /**
     * 有序添加
     *
     * @param emp
     */
    public void addAndSort(Emp emp) {
        if (head == null) {
            head = emp;
        } else {
            // 获取链表尾部节点
            Emp cur = head;
            Emp last = null;

            // 找插入点
            while (cur != null) {
                if (cur.getId() == emp.getId()) {
                    System.out.printf("重复id：%d，emp:%s，无法添加!\n", emp.getId(), emp);
                    return;
                } else if (emp.getId() < cur.getId()) {
                    break;
                }
                last = cur;
                cur = cur.getNext();
            }

            // 执行插入
            if (last == null) {
                // 说明在插入点在链表的头部
                emp.setNext(head);
                head = emp;
            } else {
                // 在中间或者是尾部插入
                emp.setNext(last.getNext());
                last.setNext(emp);
            }
        }
    }

    /**
     * 添加员工，且 id 不能重复
     *
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
        } else {
            // 获取链表尾部节点
            Emp cur = head;
            while (cur.getNext() != null) {
                if (cur.getId() == emp.getId()) {
                    System.out.printf("重复id:%d，无法添加!\n", emp.getId());
                    return;
                }
                cur = cur.getNext();
            }
            // 添加
            cur.setNext(emp);
        }
    }

    /**
     * 展示链表
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("当前链表为空...");
            return;
        } else {
            Emp cur = head;
            while (cur != null) {
                System.out.print(cur.toString());
                if (cur.getNext() != null) {
                    System.out.print(" => ");
                }
                cur = cur.getNext();
            }
            System.out.println();
        }
    }

    /**
     * 判断当前链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

}


/**
 * 员工
 */
class Emp {
    private Integer id;
    private String name;
    private String address;
    private Emp next;

    public Emp(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
