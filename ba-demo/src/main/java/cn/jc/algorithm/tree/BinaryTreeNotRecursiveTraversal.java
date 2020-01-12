package cn.jc.algorithm.tree;

import cn.jc.structures.TreeNode;

import java.util.Stack;

/**
 * 二叉树的非递归遍历
 */
public class BinaryTreeNotRecursiveTraversal {

    public static void main(String[] args) {
        // 构建二叉树
        TreeNode<String> treeNode = new TreeNode<>("A");
        treeNode.setLeft(new TreeNode<>("B"));
        treeNode.setRight(new TreeNode<>("C"));

        System.out.println("栈的非递归中序遍历算法：");

        inOrderTraversalByStack(treeNode);
    }

    /**
     * 利用栈的非递归中序遍历算法
     * @param treeNode
     */
    static void inOrderTraversalByStack(TreeNode treeNode) {
        // 创建并初始化栈
        Stack<TreeNode> stack = new Stack<>();
        TreeNode popNode;
        // push根节点
        stack.push(treeNode);
        while (!stack.empty()) {
            // 一直向左并将沿途结点节点压入堆栈
            while (stack.peek() != null) {
                stack.push(stack.peek().getLeft());
            }

            // 弹出头部的空结点
            stack.pop();

            if (!stack.empty()) {
                // 弹出头部节点
                popNode = stack.pop();
                // 输出节点内容
                System.out.println(popNode.getVal());
                // 右节点入栈
                stack.push(popNode.getRight());
            }
        }
    }
}
