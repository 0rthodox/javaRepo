package ru.mipt.collections;

public class NikiLinkedList implements CustomList{
    private NikiNode top;
    private int size;

    public NikiLinkedList() {
        size = 0;
        top = null;
    }

    public class NikiNode {
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
        return find(element) != null;
    }

    private CustomList find(Object element) {
        if (element != null) {
            CustomList foundNodes = new NikiList(0);
            NikiNode currNode = top;
            do {
                if (currNode.value.equals(element))
                    foundNodes.add(currNode);
                currNode = currNode.next;
            } while (currNode != top);
            return foundNodes;
        }
        return null;
    }

    @Override
    public boolean add(Object element) {
        if (element == null) {
            return false;
            //throw new IllegalArgumentException("Please do not add null to me");
        }
        NikiNode elementWrapper = new NikiNode(element);
        if (top != null) {
            elementWrapper.next = top;
            elementWrapper.prev = top.prev;
            top.prev.next = elementWrapper;
            top.prev = elementWrapper;
        } else {
            elementWrapper.prev = elementWrapper;
            elementWrapper.next = elementWrapper;
            top = elementWrapper;
        }
        size++;
        return true;

    }

    @Override
    public boolean remove(Object element) {
        NikiNode currNode = top;
        int old_size = size;
        do {
            if (element.equals(currNode.value)) {
                currNode = removeNode(currNode);
                size--;
            } else {
                currNode = currNode.next;
            }
        } while (currNode != top);
        return old_size != size;
    }

    private NikiNode removeNode(NikiNode currNode) {
        if (size == 1) {
            currNode = null;
            return currNode;
        }
            currNode.prev.next = currNode.next;
            currNode.next.prev = currNode.prev;
        return currNode.next;
    }

    @Override
    public boolean containsAll(CustomList anotherCustomList) {
        return false;
    }

    @Override
    public boolean containsSublist(CustomList anotherCustomList) {
        return false;
    }

    @Override
    public Object get(int index) {
        if (top == null) {
            return null;
        }
        NikiNode currNode = top;
        for(int i = 0; i < index; ++i) {
            currNode = currNode.next;
        }
        return currNode.value;
    }
}
