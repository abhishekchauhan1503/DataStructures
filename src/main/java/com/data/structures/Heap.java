package com.data.structures;

import java.util.Collection;

/**
 * Heap
 */
public interface Heap<E extends Comparable<E>> {
    Node<E> getRootNode();

    <T extends E> Heap<E> addNodeToHeap(T data);

    <T extends E> Heap<E> addAllNodesToHeap(Collection<T> nodes);

    <T extends E> boolean doesNodeExist(T node);

    E removeRootNode();

    long size();

    long getNumberOfOperationsPerformed();

    void clearNumberOfOperationsPerformed();

    //TODO: Implement clear method

    public <T extends E> void validateHeapState();
}
