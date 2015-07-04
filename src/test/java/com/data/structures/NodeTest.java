package com.data.structures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Tests for Node
 */
public class NodeTest {

    @Mock
    private Node cut;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetLeftChild() {
        Node left = new Node();
        when(cut.getLeftChild()).thenReturn(left);
        Node leftChild = cut.getLeftChild();
        Assert.assertNotNull(leftChild);
    }


    @Test
    public void testGetRightChild() {
        Node right = new Node();
        when(cut.getRightChild()).thenReturn(right);
        Node rightChild = cut.getRightChild();
        Assert.assertNotNull(rightChild);
    }

    @Test
    public void testGetParent() {
        Node parentNode = new Node();
        when(cut.getParent()).thenReturn(parentNode);
        Node parent = cut.getParent();
        Assert.assertNotNull(parent);
    }

    @Test
    public void testGetData() {
        Node<String> node = new Node<>();
        String data = "data";
        node.setData(data);
        String receivedData = node.getData();
        Assert.assertEquals(data, receivedData);
    }

    @Test
    public void testHasChildren() {
        Node<String> root = new Node<>();
        Node<String> right = new Node();
        Node<String> left = new Node();
        root.setLeftChild(left);
        root.setRightChild(right);
        Assert.assertTrue(root.hasChildren());
    }


    @Test
    public void testHasChildrenNoLeftChild() {
        Node<String> root = new Node<>();
        Node<String> right = new Node();
        root.setRightChild(right);
        Assert.assertTrue(root.hasChildren());
    }


    @Test
    public void testHasChildrenNoRightChild() {
        Node<String> root = new Node<>();
        Node<String> left = new Node();
        root.setLeftChild(left);
        Assert.assertTrue(root.hasChildren());
    }


    @Test
    public void testHasChildrenNoChildren() {
        Node<String> root = new Node<>();

        Assert.assertFalse(root.hasChildren());
    }

    @Test
    public void testCopyTo() {
        Node<String> root = new Node<>();
        Node<String> right = new Node();
        right.setParent(root);
        right.setIsRightChild(true);
        right.setData("Right");
        Node<String> left = new Node();
        left.setParent(root);
        left.setIsLeftChild(true);
        left.setData("Left");
        root.setLeftChild(left);
        root.setRightChild(right);
        root.setParent(null);
        root.setIsLeftChild(false);
        root.setIsRightChild(false);
        root.setData("Root");
        Node<String> cloneRoot = new Node<>();
        root.copyTo(cloneRoot);

        Assert.assertEquals(right.getData(), cloneRoot.getRightChild().getData());
        Assert.assertEquals(left.getData(), cloneRoot.getLeftChild().getData());
        Assert.assertEquals(root.getData(), cloneRoot.getData());
        Assert.assertTrue(cloneRoot.getRightChild().isRightChild());
        Assert.assertTrue(cloneRoot.getLeftChild().isLeftChild());
    }

}
