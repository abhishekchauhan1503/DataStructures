package com.data.structures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Heap Tests
 */
public class HeapTest {
    private Heap<String> cut;

    @Before
    public void setUp() {
        cut = new MinHeap<>();
    }

    @Test
    public void testGetRootNode() {
        String root = "root";
        cut.addNodeToHeap(root);
        String receivedRoot = cut.getRootNode().getData();
        Assert.assertEquals(root, receivedRoot);
    }

    @Test
    public void testAddNodeToHeapEmptyHeap() {
        String root = "Root";
        cut.addNodeToHeap(root);
        Assert.assertEquals(root, cut.getRootNode().getData());
    }

    @Test
    public void testAddNodeToHeapNonEmptyHeap() {
        String root = "1";
        cut.addNodeToHeap(root);
        String childOne = "2";
        cut.addNodeToHeap(childOne);
        Assert.assertEquals(root, cut.getRootNode().getData());
        Assert.assertEquals(childOne, cut.getRootNode().getLeftChild().getData());
        Assert.assertEquals(cut.getRootNode(), cut.getRootNode().getLeftChild().getParent());
    }

    @Test
    public void testAddAllNodesToHeap() {
        List<String> nodes = new ArrayList<>();
        nodes.add("1");
        nodes.add("2");
        nodes.add("3");
        cut.addAllNodesToHeap(nodes);
        Assert.assertEquals("1", cut.getRootNode().getData());
        Assert.assertEquals("2", cut.getRootNode().getLeftChild().getData());
        Assert.assertEquals("3", cut.getRootNode().getRightChild().getData());
    }


    @Test
    public void testAddNodeToMaxHeapNonEmptyHeap() {
        cut = new MaxHeap<>();
        String root = "1";
        cut.addNodeToHeap(root);
        String childOne = "2";
        cut.addNodeToHeap(childOne);
        Assert.assertEquals(childOne, cut.getRootNode().getData());
        Assert.assertEquals(root, cut.getRootNode().getLeftChild().getData());
        Assert.assertEquals(cut.getRootNode(), cut.getRootNode().getLeftChild().getParent());
    }

    @Test
    public void testAddAllNodesToMaxHeap() {
        cut = new MaxHeap<>();
        List<String> nodes = new ArrayList<>();
        nodes.add("1");
        nodes.add("2");
        nodes.add("3");
        cut.addAllNodesToHeap(nodes);
        Assert.assertEquals("3", cut.getRootNode().getData());
        Assert.assertEquals("1", cut.getRootNode().getLeftChild().getData());
        Assert.assertEquals("2", cut.getRootNode().getRightChild().getData());
    }

    @Test
    public void testAddAllNodesToMaxHeapWithSubclasses() {
        Heap<Calendar> maxHeap = new MaxHeap<>();
        List<GregorianCalendar> nodes = new ArrayList<>();
        GregorianCalendar one = new GregorianCalendar();
        one.setFirstDayOfWeek(1);
        GregorianCalendar two = new GregorianCalendar();
        two.setFirstDayOfWeek(2);
        GregorianCalendar three = new GregorianCalendar();
        three.setFirstDayOfWeek(3);
        nodes.add(one);
        nodes.add(two);
        nodes.add(three);
        maxHeap.addAllNodesToHeap(nodes);
        Assert.assertEquals(3, maxHeap.size());
    }


    @Test
    public void testDoesNodeExist() {
        cut = new MaxHeap<>();
        List<String> nodes = new ArrayList<>();
        nodes.add("1");
        nodes.add("2");
        nodes.add("3");
        cut.addAllNodesToHeap(nodes);
        Assert.assertTrue(cut.doesNodeExist("1"));
        Assert.assertTrue(cut.doesNodeExist("2"));
        Assert.assertTrue(cut.doesNodeExist("3"));
        Assert.assertFalse(cut.doesNodeExist("4"));
    }


    @Test
        public void testSize() {
        cut = new MaxHeap<>();
        List<String> nodes = new ArrayList<>();
        nodes.add("1");
        nodes.add("2");
        nodes.add("3");
        cut.addAllNodesToHeap(nodes);
        Assert.assertEquals(3, cut.size());
    }

    @Test
    public void testRemoveRootNode() {
        cut = new MaxHeap<>();
        List<String> nodes = new ArrayList<>();
        nodes.add("1");
        nodes.add("2");
        nodes.add("3");
        nodes.add("4");
        nodes.add("5");
        cut.addAllNodesToHeap(nodes);
        String root = cut.removeRootNode();
        Assert.assertEquals("5", root);
        root = cut.removeRootNode();
        Assert.assertEquals("4", root);
        root = cut.removeRootNode();
        Assert.assertEquals("3", root);
        root = cut.removeRootNode();
        Assert.assertEquals("2", root);
        root = cut.removeRootNode();
        Assert.assertEquals("1", root);
    }

    @Test
    public void testHeapSort(){
        Heap<String> minHeap = new MinHeap();
        List<String> nodes = new ArrayList<>();
        nodes.add("5");
        nodes.add("4");
        nodes.add("3");
        nodes.add("2");
        nodes.add("1");
        minHeap.addAllNodesToHeap(nodes);
        Collections.sort(nodes);
        System.out.println("Sorted List: ");
        List<String> sortedList = new ArrayList<>();
        while(minHeap.size() > 0){
            sortedList.add(minHeap.removeRootNode());
        }
        for(int i = 0; i<nodes.size();i++){
            Assert.assertEquals(nodes.get(i), sortedList.get(i));
        }
    }
}
