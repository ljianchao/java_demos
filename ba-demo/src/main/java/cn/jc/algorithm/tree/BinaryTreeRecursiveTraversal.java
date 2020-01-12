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
        treeNode.getLeft().setRight(new TreeNode<>("D"));

        System.out.println("前序遍历二叉树的结构为：");
        preOrder(treeNode);

        System.out.println("中序遍历二叉树的结构为：");
        inOrder(treeNode);

        System.out.println("后序遍历的结构为：");
        postOrder(treeNode);

        System.out.println("该二叉树的深度为：" + binTreeDepth(treeNode));

        System.out.println("查找二叉树上某结点值为D所在的层次：" + findNodeLevel(treeNode, "D", 1));
    }

    /**
     * 前序遍历二叉树
     * @param treeNode
     */
    static void preOrder(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.println(treeNode.getVal());
            preOrder(treeNode.getLeft());
            preOrder(treeNode.getRight());
        }
    }

    /**
     * 中序遍历二叉树
     * @param treeNode
     */
    static void inOrder(TreeNode treeNode) {
        if (treeNode != null) {
            inOrder(treeNode.getLeft());
            System.out.println(treeNode.getVal());
            inOrder(treeNode.getRight());
        }
    }

    /**
     * 后序遍历二叉树
     * @param treeNode
     */
    static void postOrder(TreeNode treeNode) {
        if (treeNode != null) {
            postOrder(treeNode.getLeft());
            postOrder(treeNode.getRight());
            System.out.println(treeNode.getVal());
        }
    }

    /**
     * 计算二叉树的深度
     * 利用后序遍历
     *
     * 空二叉树的深度为0
     * 非空二叉树的深度等于其左右子树中的最大深度加1
     * max(depLeft, depRight) + 1
     * @param treeNode
     */
    static int binTreeDepth(TreeNode treeNode) {

        // 空二叉树深度为0
        if (treeNode == null)
            return 0;

        int depLeft, depRight;
        depLeft = binTreeDepth(treeNode.getLeft());
        depRight = binTreeDepth(treeNode.getRight());
        return depLeft > depRight ? depLeft + 1 : depRight + 1;
    }

    /**
     * 查找二叉树上某结点值所在的层次
     * 利用前序遍历
     *
     * 空二叉树返回0
     * @param treeNode
     * @param val
     * @param currentLevel 当前结点所在的层次，调用时置初值为1
     * @return
     */
    static int findNodeLevel(TreeNode treeNode, String val, int currentLevel) {
        int level = 0;

        // 空二叉树深度返回0
        if (treeNode == null)
            return 0;

        if (val.equals(treeNode.getVal()))
            return currentLevel;
        // 遍历左子树
        level = findNodeLevel(treeNode.getLeft(), val, currentLevel + 1);

        // 遍历右子树
        if (level == 0)
            level = findNodeLevel(treeNode.getRight(), val, currentLevel + 1);

        return level;
    }
}
