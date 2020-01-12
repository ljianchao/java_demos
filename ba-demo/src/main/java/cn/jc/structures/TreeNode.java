package cn.jc.structures;

/**
 * Binary tree node.
 * @param <T>
 */
public class TreeNode<T> {

    T val;

    TreeNode<T> left;

    TreeNode<T> right;

    public TreeNode(T val) {
        this.val = val;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
