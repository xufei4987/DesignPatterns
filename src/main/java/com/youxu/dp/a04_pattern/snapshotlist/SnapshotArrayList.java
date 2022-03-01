package com.youxu.dp.a04_pattern.snapshotlist;

import java.util.Iterator;

public class SnapshotArrayList<T> implements List<T> {
    private int totalSize;
    private int actualSize;
    private Object[] elements;
    private Long[] addTimestamps;
    private Long[] delTimestamps;
    private static final int DEFAULT_CAPACITY = 1 << 10;

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
        if (idx >= totalSize || delTimestamps[idx] != Long.MAX_VALUE) {
            throw new IllegalArgumentException("idx is overflow");
        }
        delTimestamps[idx] = System.nanoTime();
        actualSize--;
        return (T) elements[idx];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor;
            long createTimestamp;
            int leftCount; // 快照中还有几个元素未被遍历

            {
                cursor = 0;
                createTimestamp = System.nanoTime();
                leftCount = actualSize;
                findNext();
            }

            @Override
            public boolean hasNext() {
                return leftCount > 0;
            }

            @Override
            public T next() {
                Object element = elements[cursor];
                if (findNext()) {
                    leftCount--;
                    cursor++;
                }
                return (T) element;
            }

            private boolean findNext() {
                while (cursor < totalSize) {
                    if (createTimestamp > addTimestamps[cursor] && createTimestamp < delTimestamps[cursor]) {
                        return true;
                    }
                    cursor++;
                }
                return false;
            }
        };
    }
}
