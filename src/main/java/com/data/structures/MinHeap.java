package com.data.structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Data structure for MinHeap
 */
public class MinHeap<E extends Comparable<E>> extends AbstractHeap<E> {

    <T extends E> boolean isNodePositionValid(Node<T> node) {

        
        if (node.getData().compareTo(node.getParent().getData()) < 0) {
            return false;
        }
        return true;
    }

    <T extends E> Node<T> getNewRoot(Node<T> node) {

        
        if (!node.hasChildren()) {
            return node;
        }

        Node<T> smallestChild = node.getSmallestChild(node);
        if (smallestChild == null) {
            return node;
        }

        if (node.getData().compareTo(smallestChild.getData()) > 0) {
            return smallestChild;
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
                if (root.getData().compareTo(root.getLeftChild().getData()) > 0) {
                    throw new IllegalStateException("Heap is now in an invalid state");
                }
            }
            if (root.getRightChild() != null) {
                if (root.getData().compareTo(root.getLeftChild().getData()) > 0) {
                    throw new IllegalStateException("Heap is now in an invalid state");
                }
            }
            updateSearchList(searchList);
        }
    }


}
