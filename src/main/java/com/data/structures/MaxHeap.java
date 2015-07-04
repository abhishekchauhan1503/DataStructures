package com.data.structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Data structure for MaxHeap
 */
public class MaxHeap<E extends Comparable<E>> extends AbstractHeap<E> {

    <T extends E> boolean isNodePositionValid(Node<T> node) {
        if (node.getData().compareTo(node.getParent().getData()) > 0) {

            
            return false;
        }
        return true;
    }

    <T extends E> Node<T> getNewRoot(Node<T> node) {

        
        if (!node.hasChildren()) {
            return node;
        }

        Node<T> largestChild = node.getLargestChild(node);
        if (largestChild == null) {
            return node;
        }

        if (node.getData().compareTo(largestChild.getData()) < 0) {
            return largestChild;
        }

        return node;
    }

    public <T extends E> void validateHeapState() {
        List<Node> searchList = new ArrayList<>();
        searchList.add(rootNode);
        Node<T> root;
        while (!searchList.isEmpty()) {

            
            root = searchList.get(0);
            if (root.getLeftChild() != null) {
                if (root.getData().compareTo(root.getLeftChild().getData()) < 0) {
                    throw new IllegalStateException("Heap is now in an invalid state");
                }
            }
            if (root.getRightChild() != null) {
                if (root.getData().compareTo(root.getLeftChild().getData()) < 0) {
                    throw new IllegalStateException("Heap is now in an invalid state");
                }
            }
            updateSearchList(searchList);
        }
    }


}