package cn.jc.algorithm.tree;

import cn.jc.structures.TreeNode;

/**
 * 二叉树的遍历递归算法
 */
public class BinaryTreeRecursiveTraversal {

    public static void main(String[] args) {

        // 构建二叉树
        TreeNode<String> treeNode = new TreeNode<>("A");
        treeNode.setLeft(new TreeNode<>("B"));
        treeNode.setRight(new TreeNode<>("C"));

        System.out.println("前序遍历的结构为：");
        Preorder(treeNode);

        System.out.println("中遍历的结构为：");
        Inorder(treeNode);

        System.out.println("后序遍历的结构为：");
        Postorder(treeNode);
    }

    /**
     * 前序遍历二叉树
     * @param treeNode
     */
    static void Preorder(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.println(treeNode.getVal());
            Preorder(treeNode.getLeft());
            Preorder(treeNode.getRight());
        }
    }

    /**
     * 中序遍历二叉树
     * @param treeNode
     */
    static void Inorder(TreeNode treeNode) {
        if (treeNode != null) {
            Inorder(treeNode.getLeft());
            System.out.println(treeNode.getVal());
            Inorder(treeNode.getRight());
        }
    }

    /**
     * 后序遍历二叉树
     * @param treeNode
     */
    static void Postorder(TreeNode treeNode) {
        if (treeNode != null) {
            Postorder(treeNode.getLeft());
            Postorder(treeNode.getRight());
            System.out.println(treeNode.getVal());
        }
    }

}
