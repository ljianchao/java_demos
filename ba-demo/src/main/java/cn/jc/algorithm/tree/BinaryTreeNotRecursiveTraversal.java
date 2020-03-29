package cn.jc.algorithm.tree;

import cn.jc.structures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
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

        System.out.println("二叉树利用栈的非递归中序遍历算法：");
        inOrderTraversalByStack(treeNode);

        System.out.println("二叉树利用队列进行层序遍历算法：");
        LevelOrderByQueue(treeNode);
    }

    /**
     * 二叉树利用栈的非递归中序遍历算法
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

    /**
     * 二叉树利用队列进行层序遍历算法
     * 1. 先根结点入队
     * 2. 从队列取出一个元素
     * 3. 访问该元素所指结点
     * 4. 若该元素所指结点的左、右孩子结点非空，
     *    则将其左、右孩子的指针顺序入队
     * @param treeNode
     */
    static void LevelOrderByQueue(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (treeNode == null)
            return;
        TreeNode pollNode;
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            pollNode = queue.poll();
            System.out.println(pollNode.getVal());
            if (pollNode.getLeft() != null)
                queue.offer(pollNode.getLeft());
            if (pollNode.getRight() != null)
                queue.offer(pollNode.getRight());
        }
    }

}
