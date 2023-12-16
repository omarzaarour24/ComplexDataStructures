package Avl;

//https://www.baeldung.com/java-avl-trees
public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVLTree() {
        root = null;
    }

    private int getHeight(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    private int getBalanceFactor(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    private AVLNode<T> rotateRight(AVLNode<T> y) {
        AVLNode<T> x = y.left;
        AVLNode<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }
    private AVLNode<T> rotateLeft(AVLNode<T> x) {
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }
    public void insert(T data) {
        if (data == null) {
            throw new NullPointerException("Null data cannot be inserted.");
        }
        root = insert(root, data);
    }
    private AVLNode<T> insert(AVLNode<T> node, T data) {
        if (node == null) {
            return new AVLNode<>(data);
        }

        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            node.left = insert(node.left, data);
        } else if (compareResult > 0) {
            node.right = insert(node.right, data);
        } else {
            // Handle duplicate data
            throw new IllegalArgumentException("Duplicate data: " + data);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalanceFactor(node);

        // Left Heavy
        if (balance > 1) {
            if (data.compareTo(node.left.data) < 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        // Right Heavy
        if (balance < -1) {
            if (data.compareTo(node.right.data) > 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }
    public T search(T data) {
        return search(root, data);
    }
    private T search(AVLNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            return search(node.left, data);
        } else if (compareResult > 0) {
            return search(node.right, data);
        } else {
            return node.data;
        }
    }
    public String graphViz() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph AVLTree {\n");

        graphViz(root, dot);

        dot.append("}\n");
        return dot.toString();
    }
    private void graphViz(AVLNode<T> node, StringBuilder dot) {
        if (node != null) {
            dot.append("  " + node.data + " [label=\"" + node.data + "\\nHeight: " + node.height + "\"];\n");

            if (node.left != null) {
                dot.append("  " + node.data + " -> " + node.left.data + " [label=\"L\"];\n");
                graphViz(node.left, dot);
            }

            if (node.right != null) {
                dot.append("  " + node.data + " -> " + node.right.data + " [label=\"R\"];\n");
                graphViz(node.right, dot);
            }
        }
    }
}

