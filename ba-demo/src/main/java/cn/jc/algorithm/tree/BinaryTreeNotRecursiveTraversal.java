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
        InorderByStack(treeNode);
    }

    /**
     * 利用栈的非递归中序遍历算法
     * @param treeNode
     */
    static void InorderByStack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode popNode;
        // 入栈根结点
        stack.push(treeNode);
        while (!stack.empty()) {
            // 循环栈顶元素
            while (stack.peek() != null) {
                // 直到左子树为空为止
                stack.push(stack.peek().getLeft());
            }

            // 最后的null结点出栈
            popNode = stack.pop();
            if (!stack.empty()) {
                // 输出内容
                System.out.println(stack.peek().getVal());
                popNode = stack.pop();
                stack.push(popNode.getRight());
            }
        }
    }
}
