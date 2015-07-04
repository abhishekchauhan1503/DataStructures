package com.data.structures;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Common features required by Heaps
 */
abstract class AbstractHeap<E extends Comparable<E>> implements Heap<E> {

    protected long numberOfOperationsPerformed;
    protected Node<E> rootNode;

    @Override
    public Node<E> getRootNode() {
        return rootNode;
    }


    @Override
    public <T extends E> Heap<E> addNodeToHeap(T data) {
        if (this.rootNode == null) {
            setupRootNode(data);
            
            return this;
        }

        Node<T> dataNode = new Node<>();
        dataNode.setData(data);
        insertNodeIntoHeap(dataNode);
        heapify(dataNode);
        return this;
    }

    @Override
    public <T extends E> Heap<E> addAllNodesToHeap(Collection<T> nodes) {
        for (T node : nodes) {
            addNodeToHeap(node);
        }
        return this;
    }


    @Override
    public <T extends E> boolean doesNodeExist(T node) {
        return isNodeFoundUsingBFS(node);
    }

    @Override
    public long size() {
        return countNumberOfNodesInHeap();
    }

    @Override
    public E removeRootNode() {
        if (rootNode != null && !rootNode.hasChildren()) {
            E data = rootNode.getData();
            rootNode = null;

            return data;
        }
        E rootNodeData = rootNode.getData();

        Node<E> newRoot = getLastNodeOnLowestLevel();
        rootNode.setData(newRoot.getData());
        updateChildLinksForNewRoot(newRoot);
        heapifyTopDown();
       return rootNodeData;
    }

    private <T extends E> Node<T> getLastNodeOnLowestLevel() {
        List<Node> searchList = new ArrayList<>();

        searchList.add(rootNode);

        updateSearchList(searchList);

        while (searchList.size() > 1) {

            updateSearchList(searchList);
        }
        return searchList.get(0);
    }

    private void updateChildLinksForNewRoot(Node<E> newRoot) {
        if (newRoot.isLeftChild()) {

            newRoot.getParent().setLeftChild(null);
        } else {

            newRoot.getParent().setRightChild(null);
        }
    }

    private long countNumberOfNodesInHeap() {
        if (rootNode == null) {

            return 0;
        }
        List<Node> searchList = new ArrayList<>();
        searchList.add(rootNode);
        long count = 0;
        while (!searchList.isEmpty()) {

            count++;
            updateSearchList(searchList);
        }
        return count;
    }


    private <T extends E> boolean isNodeFoundUsingBFS(T node) {
        List<Node> searchList = new ArrayList<>();
        searchList.add(rootNode);
        Node<T> root = null;
        while (!searchList.isEmpty()) {

            root = searchList.get(0);
            if (root.getData().compareTo(node) == 0) {

                return true;
            } else {

                updateSearchList(searchList);
            }
        }
        return false;
    }

    protected <T extends E> void updateSearchList(List<Node> nodes) {

        Node root = nodes.remove(0);
        if (root.getLeftChild() != null) {
            nodes.add(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            nodes.add(root.getRightChild());
        }
    }

    abstract <T extends E> boolean isNodePositionValid(Node<T> node);


    private <T extends E> void insertNodeIntoHeap(Node<T> node) {
        Assert.assertNotNull("Root node cannot be null during insertion operation", rootNode);
        Node<T> firstNodeWithMissingChild = findFirstNodeWithMissingChild();
        addChildToParent(firstNodeWithMissingChild, node);
    }

    private <T extends E> void heapify(Node<T> node) {
        while (node.getParent() != null) {

            numberOfOperationsPerformed++;
            if (!isNodePositionValid(node)) {
                swapParentAndChildData(node.getParent(), node);
            }
            node = node.getParent();
        }
    }

    abstract <T extends E> Node<T> getNewRoot(Node<T> node);

    private <T extends E> void heapifyTopDown() {
        Node<T> node = (Node<T>) rootNode;
        boolean isNodeAtValidPosition = false;
        while (!isNodeAtValidPosition) {

            numberOfOperationsPerformed++;
            isNodeAtValidPosition = true;
            Node<T> newNode = getNewRoot(node);
            if (newNode.getData().compareTo(node.getData()) != 0) {
                isNodeAtValidPosition = false;
                swapParentAndChildData(node, newNode);
                node = newNode;
            }
        }
    }

    protected <T extends E> void swapParentAndChildData(Node<T> parent, Node<T> child) {

        T childData = child.getData();
        child.setData(parent.getData());
        parent.setData(childData);
    }

    private <T extends E> void addChildToParent(Node<T> parent, Node<T> child) {

        if (parent.getLeftChild() == null) {
            parent.setLeftChild(child);
            child.setIsLeftChild(true);
        } else if (parent.getRightChild() == null) {
            parent.setRightChild(child);
            child.setIsRightChild(true);
        }
        child.setParent(parent);
    }

    protected <T extends E> void setupRootNode(T data) {
        this.rootNode = new Node<>();
        this.rootNode.setIsRightChild(false);
        this.rootNode.setIsLeftChild(false);
        this.rootNode.setParent(null);
        this.rootNode.setData(data);
    }

    protected <T extends E> Node<T> findFirstNodeWithMissingChild() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(rootNode);
        while (!nodes.isEmpty()) {

            if (nodes.get(0).getLeftChild() == null) {
                return nodes.get(0);
            } else if (nodes.get(0).getRightChild() == null) {
                return nodes.get(0);
            }
            Node<E> curNode = nodes.remove(0);
            nodes.add(curNode.getLeftChild());
            nodes.add(curNode.getRightChild());
        }
        return null;
    }

    @Override
    public void clearNumberOfOperationsPerformed() {
        setNumberOfOperationsPerformed(0);
    }

    @Override
    public long getNumberOfOperationsPerformed() {
        return numberOfOperationsPerformed;
    }

    void setNumberOfOperationsPerformed(long numberOfOperationsPerformed) {
        this.numberOfOperationsPerformed = numberOfOperationsPerformed;
    }

    void setRootNode(Node<E> rootNode) {
        this.rootNode = rootNode;
    }
}
