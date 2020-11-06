package com.yzf.ch10;

/**
 * @description:二叉树
 * @author:leo_yuzhao
 * @date:2020/11/5
 */
public class BinaryTreeDemo {
    private static HeroTree heroTree = new HeroTree();

    static {
        HeroTreeNode HeroTreeNode1 = new HeroTreeNode(1, "宋江");
        HeroTreeNode HeroTreeNode2 = new HeroTreeNode(2, "吴用");
        HeroTreeNode HeroTreeNode3 = new HeroTreeNode(3, "卢俊");
        HeroTreeNode HeroTreeNode4 = new HeroTreeNode(4, "林冲");
        HeroTreeNode HeroTreeNode5 = new HeroTreeNode(5, "王二");
        HeroTreeNode1.setLeft(HeroTreeNode2);
        HeroTreeNode1.setRight(HeroTreeNode3);
        HeroTreeNode3.setRight(HeroTreeNode5);
        HeroTreeNode3.setLeft(HeroTreeNode4);
        heroTree.setRoot(HeroTreeNode1);

    }

    public static void main(String[] args) {
        test4();
    }

    public static void test1() {
        heroTree.preShow();
    }

    public static void test2() {
        HeroTreeNode heroTreeNode = heroTree.preOrderFind(5);
        System.out.println(heroTreeNode);
    }

    public static void test3() {
        HeroTreeNode heroTreeNode = heroTree.infixOrderFind(5);
        System.out.println(heroTreeNode);
    }

    public static void test4() {
        HeroTreeNode heroTreeNode = heroTree.postOrderFind(5);
        System.out.println(heroTreeNode);
    }

}

class HeroTree {
    private HeroTreeNode root;

    public HeroTree() {
    }

    public HeroTree(HeroTreeNode root) {
        this.root = root;
    }

    public HeroTreeNode getRoot() {
        return root;
    }

    public void setRoot(HeroTreeNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preShow() {
        this.root.preShow();
    }

    /**
     * 中序遍历
     */
    public void midShow() {
        this.root.midShow();
    }

    /**
     * 后序遍历
     */
    public void postShow() {
        this.root.postShow();
    }

    /**
     * 前序查找
     *
     * @param id
     * @return
     */
    public HeroTreeNode preOrderFind(Integer id) {
        return this.root.preOrderFind(id);
    }

    /**
     * 中序查找
     *
     * @param id
     * @return
     */
    public HeroTreeNode infixOrderFind(Integer id) {
        return this.root.infixOrderFind(id);
    }

    /**
     * 中序查找
     *
     * @param id
     * @return
     */
    public HeroTreeNode postOrderFind(Integer id) {
        return this.root.postOrderFind(id);
    }
}

class HeroTreeNode {
    private int id;
    private String name;
    private HeroTreeNode left;
    private HeroTreeNode right;

    public HeroTreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroTreeNode getLeft() {
        return left;
    }

    public void setLeft(HeroTreeNode left) {
        this.left = left;
    }

    public HeroTreeNode getRight() {
        return right;
    }

    public void setRight(HeroTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroTreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历：父 左 右
     */
    public void preShow() {
        // 父
        System.out.println(this);
        // 左
        if (this.getLeft() != null) {
            this.getLeft().preShow();
        }
        // 右
        if (this.getRight() != null) {
            this.getRight().preShow();
        }
    }

    /**
     * 中序遍历
     */
    public void midShow() {
        // 左
        if (this.getLeft() != null) {
            this.getLeft().preShow();
        }
        // 父
        System.out.println(this);
        // 右
        if (this.getRight() != null) {
            this.getRight().preShow();
        }
    }

    /**
     * 后序遍历
     */
    public void postShow() {
        // 左
        if (this.getLeft() != null) {
            this.getLeft().preShow();
        }
        // 右
        if (this.getRight() != null) {
            this.getRight().preShow();
        }
        // 父
        System.out.println(this);
    }

    /**
     * 前序查找
     *
     * @param id
     * @return
     */
    public HeroTreeNode preOrderFind(Integer id) {

        HeroTreeNode temp = null;

        // 1，比较当前元素
        if (this.id == id) {
            return this;
        }

        // 2，左递归查找
        if (this.left != null) {
            // 注意此处不能直接使用 return preOrderFind(id); 因为可能存在左边没有查找的节点，
            // 此时应该向右递归查找，如果此处使用了return，就无法向右查找了。
            temp = this.left.preOrderFind(id);
            // temp != null，说明查找到结果，就直接返回
            if (temp != null) {
                return temp;
            }
        }

        // 3，右递归查找
        if (this.right != null) {
            temp = this.right.preOrderFind(id);
            if (temp != null) {
                return temp;
            }
        }

        return null;
    }

    /**
     * 中序查找
     *
     * @param id
     * @return
     */
    public HeroTreeNode infixOrderFind(Integer id) {

        HeroTreeNode temp = null;

        // 1，左递归查找
        if (this.left != null) {
            // 注意此处不能直接使用 return preOrderFind(id); 因为可能存在左边没有查找的节点，
            // 此时应该向右递归查找，如果此处使用了return，就无法向右查找了。
            temp = this.left.infixOrderFind(id);
            // temp != null，说明查找到结果，就直接返回
            if (temp != null) {
                return temp;
            }
        }

        // 2，比较当前元素
        if (this.id == id) {
            return this;
        }

        // 3，右递归查找
        if (this.right != null) {
            temp = this.right.infixOrderFind(id);
            if (temp != null) {
                return temp;
            }
        }

        return null;
    }

    /**
     * 后序查找
     *
     * @param id
     * @return
     */
    public HeroTreeNode postOrderFind(Integer id) {

        HeroTreeNode temp = null;

        // 1，左递归查找
        if (this.left != null) {
            // 注意此处不能直接使用 return preOrderFind(id); 因为可能存在左边没有查找的节点，
            // 此时应该向右递归查找，如果此处使用了return，就无法向右查找了。
            temp = this.left.postOrderFind(id);
            // temp != null，说明查找到结果，就直接返回
            if (temp != null) {
                return temp;
            }
        }

        // 2，右递归查找
        if (this.right != null) {
            temp = this.right.postOrderFind(id);
            if (temp != null) {
                return temp;
            }
        }

        // 3，比较当前元素
        if (this.id == id) {
            return this;
        }

        return null;
    }

}