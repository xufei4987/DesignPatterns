package com.youxu.dp.a04_pattern.snapshotlist;

public interface List<T> extends Iterable<T>{
    void add(T t);
    T remove(int idx);
}
