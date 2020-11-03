package com.yzf.ch09;

/**
 * @description:HashTableDemo
 * @author:leo_yuzhao
 * @date:2020/11/3
 */
public class HashTableDemo {

    public static void main(String[] args) {
        Emp emp1 = new Emp(1, "张三", "北京");
        Emp emp2 = new Emp(2, "张四", "北京");
        Emp emp3 = new Emp(3, "张五", "北京");
        Emp emp4 = new Emp(12, "张六", "北京");

        EmpHashTable empHashTable = new EmpHashTable(10);
        empHashTable.add(emp1);
        empHashTable.add(emp2);
        empHashTable.add(emp3);
        empHashTable.add(emp4);
        empHashTable.show();

    }

    public static void test1() {
        EmpLinkedList empLinkedList = new EmpLinkedList();
        empLinkedList.add(new Emp(1, "张三", "北京"));
        empLinkedList.add(new Emp(2, "李四", "上海"));
        empLinkedList.list();
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
     * 添加员工
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
