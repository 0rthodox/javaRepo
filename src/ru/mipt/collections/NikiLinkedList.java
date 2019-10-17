package ru.mipt.collections;

public class NikiLinkedList implements CustomList{
    private NikiNode top;
    private int size;

    public class NikiNode {
        NikiNode prev;
        NikiNode next;
        Object value;

        public NikiNode(NikiNode prev, NikiNode next, Object value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }

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
        NikiNode currNode = top;
        while (top != null) {
            if (top.value.equals(element))
                return true;
            top = top.next;
        }
        return false;
    }

    @Override
    public boolean add(Object element) {
        return false;
    }

    @Override
    public boolean remove(Object element) {
        return false;
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
        return null;
    }
}
