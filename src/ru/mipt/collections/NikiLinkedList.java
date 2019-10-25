package ru.mipt.collections;

import java.util.Objects;

public class NikiLinkedList implements CustomList{
    private NikiNode firstNode;
    private int size;

    public NikiLinkedList() {
        size = 0;
        firstNode = null;
    }

    class NikiNode {
        NikiNode prev;
        NikiNode next;
        Object value;
        public NikiNode(Object value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return findNodes(element) != null;
    }

    private CustomList findNodes(Object element) {
        if (element != null) {
            CustomList foundNodes = new NikiList(0);
            NikiNode currNode = firstNode;
            while (currNode != null) {
                if (Objects.equals(currNode.value, element))
                    foundNodes.add(currNode);
                currNode = currNode.next;
            }
            return foundNodes;
        }
        return null;
    }

    @Override
    public boolean add(Object element) {
        if (element == null) {
            throw new IllegalArgumentException("Attempt to add null, interrupting..");
        }
        NikiNode elementWrapper = new NikiNode(element);
        if (firstNode != null) {
            elementWrapper.next = firstNode;
            elementWrapper.prev = null;
            firstNode.prev = elementWrapper;
        }
        firstNode = elementWrapper;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            throw new IllegalArgumentException("Attempt to remove null, interrupting..");
        }
        NikiNode currNode = firstNode;
        int old_size = size;
        while (currNode != null) {
            if (Objects.equals(currNode.value, element)) {
                currNode = removeNode(currNode);
                size--;
            } else {
                currNode = currNode.next;
            }
        }
        return old_size != size;
    }

    private NikiNode removeNode(NikiNode currNode) {
        if (size == 1) {
            currNode = null;
            return null;
        }
            currNode.prev.next = currNode.next;
            currNode.next.prev = currNode.prev;
            --size;
        return currNode.next;
    }

    @Override
    public boolean containsAll(CustomList anotherCustomList) {
        return (anotherCustomList.size() == size && containsSublist(anotherCustomList));
    }

    @Override
    public boolean containsSublist(CustomList anotherCustomList) throws RuntimeException {
        if (anotherCustomList.isEmpty())
            return true;
        CustomList foundStartingPoints = findNodes(anotherCustomList.get(0));
        for (int i = 0; i < foundStartingPoints.size(); ++i) {
            try {
                if (checkEquity(anotherCustomList, (NikiNode) foundStartingPoints.get(i))) {
                    return true;
                } //checking equity starting from each starting point
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException("Something is wrong with the nodes");
            };
        }
        return false;
    }

    private boolean checkEquity(CustomList anotherCustomList, NikiNode currentNode) throws IllegalArgumentException {
        if (size < anotherCustomList.size())
            return false;
        try {
            for (int i = 0; i < anotherCustomList.size(); ++i) {
                if (!Objects.equals(currentNode.value, anotherCustomList.get(i))) {
                    return false;
                }
                currentNode = currentNode.next;
            }
        } catch (NullPointerException npException) {
            throw new IllegalArgumentException("Null node is somewhere");
        };
        return true;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong index");
        }
        if (firstNode == null) {
            return null;
        }
        NikiNode currNode = firstNode;
        for(int i = 0; i < index; ++i) {
            currNode = currNode.next;
        }
        return currNode.value;
    }
}
