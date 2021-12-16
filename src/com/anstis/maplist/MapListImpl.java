package com.anstis.maplist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapListImpl<K, V> implements MapList<K, V> {

    private final Map<K, ? extends Object> map;
    private final List<V> list;

    public MapListImpl(Map<K, V> map) {
        this.map = map;
        this.list = flatten(map);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public V get(int index) {
        return list.get(index);
    }

    private List<V> flatten(Map<K, V> map) {
        final List<V> items = new ArrayList<>();
        map.values().forEach(v -> {
            if (v instanceof Map) {
                items.addAll(flatten((Map) v));
            } else {
                items.add(v);
            }
        });
        return items;
    }
}
