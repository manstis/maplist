package com.anstis.maplist;

public interface MapList<K, V> {

    int size();

    boolean isEmpty();

    V get(int index);
}
