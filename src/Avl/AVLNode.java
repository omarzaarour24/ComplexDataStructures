package Avl;

class AVLNode<T> {
    T data;
    AVLNode<T> left;
    AVLNode<T> right;
    int height;

    AVLNode(T data) {
        this.data = data;
        this.height = 1;
    }
    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public int getHeight() {
        return height;
    }
}