package com.datastruct.tree;

import jdk.nashorn.internal.ir.BinaryNode;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void makeEmpty() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType node) {
        return contains(node, root);
    }

    private boolean contains(AnyType node, BinaryNode<AnyType> root) {
        if (root == null) return false;
        int compareResult = node.compareTo(root.element);
        if (compareResult < 0) {
            return contains(node, root.leftNode);//小于当前节点，左子树走
        } else if (compareResult > 0) {
            return contains(node, root.rightNode);//大于当前节点，右子树走
        } else {
            return true;
        }
    }

    public AnyType findMax() {
        if (root == null) return null;
        return findMax(root).element;
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> node) {
        if (node.rightNode != null) {
            return findMax(node.rightNode);
        }
        return node;
    }

    public AnyType findMin() throws Exception {
        if (root == null) throw new Exception();
        return findMin(root).element;
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> node) {
        if (node.leftNode != null) {
            return findMin(node.leftNode);
        }
        return node;
    }

    public BinaryNode<AnyType> insert(AnyType t, BinaryNode<AnyType> node) {
        if (node == null) {
            return new BinaryNode<>(t);
        }
        int compareResult = t.compareTo(node.element);
        if (compareResult < 0) {
            node.leftNode = insert(t, node.leftNode);
        } else if (compareResult > 0) {
            node.rightNode = insert(t, node.rightNode);
        }
        return node;
    }

    private static class BinaryNode<AnyType> {
        AnyType element;//节点数据
        BinaryNode<AnyType> leftNode;//左子节点
        BinaryNode<AnyType> rightNode;//右子节点

        public BinaryNode(AnyType element) {
            this(element, null, null);
        }

        public BinaryNode(AnyType element, BinaryNode<AnyType> leftNode, BinaryNode<AnyType> rightNode) {
            this.element = element;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }
}
