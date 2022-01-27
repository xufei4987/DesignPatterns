package com.youxu.dp.a04_pattern.snapshotlist;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        SnapshotArrayList<Integer> snapshotArrayList = new SnapshotArrayList<>();
        snapshotArrayList.add(1);
        snapshotArrayList.add(2);
        Iterator<Integer> it1 = snapshotArrayList.iterator();
        snapshotArrayList.add(3);
        Iterator<Integer> it2 = snapshotArrayList.iterator();
        snapshotArrayList.remove(2);
        Iterator<Integer> it3 = snapshotArrayList.iterator();

        it1.forEachRemaining(System.out::println);
        it2.forEachRemaining(System.out::println);
        it3.forEachRemaining(System.out::println);
    }
}
