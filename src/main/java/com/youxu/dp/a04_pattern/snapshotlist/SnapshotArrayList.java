package com.youxu.dp.a04_pattern.snapshotlist;

import java.util.Iterator;

public class SnapshotArrayList<T> implements List<T>{
    private int totalSize;
    private int actualSize;
    private Object[] elements;
    private Long[] addTimestamps;
    private Long[] delTimestamps;
    private static final int DEFAULT_CAPACITY = 1 << 10;

    public int getTotalSize() {
        return totalSize;
    }

    public int getActualSize() {
        return actualSize;
    }

    public Object[] getElements() {
        return elements;
    }

    public Long[] getAddTimestamps() {
        return addTimestamps;
    }

    public Long[] getDelTimestamps() {
        return delTimestamps;
    }

    public SnapshotArrayList(int capacity) {
        this.elements = new Object[capacity];
        this.addTimestamps = new Long[capacity];
        this.delTimestamps = new Long[capacity];
    }

    public SnapshotArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T t) {
        elements[totalSize] = t;
        addTimestamps[totalSize] = System.nanoTime();
        delTimestamps[totalSize] = Long.MAX_VALUE;
        totalSize++;
        actualSize++;
    }

    @Override
    public T remove(int idx) {
        if (idx >= totalSize || delTimestamps[idx] != Long.MAX_VALUE){
            throw new IllegalArgumentException("idx is overflow");
        }
        delTimestamps[idx] = System.nanoTime();
        actualSize--;
        return (T) elements[idx];
    }

    @Override
    public Iterator<T> iterator() {
        return new SnapshotIterator(this);
    }

    private class SnapshotIterator implements Iterator<T>{
        private long createTimestamp;
        private int leftCount; // 快照中还有几个元素未被遍历
        private int cursor;
        private SnapshotArrayList<T> list;

        public SnapshotIterator(SnapshotArrayList<T> list) {
            this.list = list;
            this.cursor = 0;
            this.createTimestamp = System.nanoTime();
            this.leftCount = list.getActualSize();
            findNext();
        }

        @Override
        public boolean hasNext() {
            return leftCount > 0;
        }

        @Override
        public T next() {
            Object element = list.getElements()[cursor];
            if (findNext()){
                leftCount--;
                cursor++;
            }
            return (T) element;
        }

        private boolean findNext(){
            while (cursor < list.getTotalSize()){
                if (createTimestamp > list.getAddTimestamps()[cursor] && createTimestamp < list.getDelTimestamps()[cursor]){
                    return true;
                }
                cursor++;
            }
            return false;
        }
    }
}
