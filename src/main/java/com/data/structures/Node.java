package com.data.structures;

/**
 * Node in a Heap
 */
public class Node<T> {
    private Node<T> parent;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private boolean isLeftChild;
    private boolean isRightChild;
    private T data;

    public void copyTo(Node<T> destinationNode) {
        destinationNode.setIsLeftChild(isLeftChild);
        destinationNode.setIsRightChild(isRightChild);
        destinationNode.setData(data);
        destinationNode.setParent(parent);
        destinationNode.setLeftChild(leftChild);
        destinationNode.setRightChild(rightChild);

    }

    public <T extends Comparable> Node<T> getLargestChild(Node<T> node) {
        if (node.getLeftChild() != null) {
            if (node.getRightChild() != null) {
                if (node.getLeftChild().getData().compareTo(node.getRightChild().getData()) < 0) {
                    return node.getRightChild();
                } else {
                    return node.getLeftChild();
                }
            } else {
                return node.getLeftChild();
            }

        }
        if (node.getRightChild() != null) {
            return node.getRightChild();
        }
        return null;
    }

    public <T extends Comparable> Node<T> getSmallestChild(Node<T> node) {
        if (node.getLeftChild() != null) {
            if (node.getRightChild() != null) {
                if (node.getLeftChild().getData().compareTo(node.getRightChild().getData()) > 0) {
                    return node.getRightChild();
                } else {
                    return node.getLeftChild();
                }
            } else {
                return node.getLeftChild();
            }

        }
        if (node.getRightChild() != null) {
            return node.getRightChild();
        }
        return null;
    }

    public boolean isLeftChild() {
        return isLeftChild;
    }

    public void setIsLeftChild(boolean leftChild) {
        isLeftChild = leftChild;
    }

    public boolean isRightChild() {
        return isRightChild;
    }

    public void setIsRightChild(boolean rightChild) {
        isRightChild = rightChild;
    }

    public boolean hasChildren() {
        return leftChild != null || rightChild != null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }


    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

}
