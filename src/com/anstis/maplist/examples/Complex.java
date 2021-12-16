package com.anstis.maplist.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.anstis.maplist.MapList;
import com.anstis.maplist.MapListImpl;

public class Complex {

    interface IValue<V> {

        V getValue();

        void setValue(V value);
    }

    static class UnitValue implements IValue<String> {

        private String value;

        public UnitValue(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    static class StructureValue implements IValue<Map<String, IValue<?>>> {

        private Map<String, IValue<?>> value;

        public StructureValue(Map<String, IValue<?>> value) {
            this.value = value;
        }

        @Override
        public Map<String, IValue<?>> getValue() {
            return value;
        }

        @Override
        public void setValue(Map<String, IValue<?>> value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.entrySet().stream().map(e -> String.format("%s=%s", e.getKey(), e.getValue())).collect(Collectors.joining(", "));
        }
    }

    public void run() {
        System.out.println("==> COMPLEX");

        final Map<String, IValue<?>> map = new HashMap<>();
        map.put("1", new UnitValue("item1"));
        map.put("2", new StructureValue(Map.of("2a", new UnitValue("item2"), "2b", new UnitValue("item3"))));
        map.put("3", new StructureValue(Map.of("3a", new UnitValue("item4"), "3b", new UnitValue("item5"))));
        MapList<String, IValue<?>> mapList = new MapListImpl<>(map);

        System.out.println("---> Original map");
        map.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
        System.out.println("---> Original list");
        for (int i = 0; i < mapList.size(); i++) {
            System.out.println(mapList.get(i));
        }

        ((UnitValue) mapList.get(0)).setValue("updated1");

        System.out.println("---> Updated map");
        map.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
        System.out.println("---> Updated list");
        for (int i = 0; i < mapList.size(); i++) {
            System.out.println(mapList.get(i));
        }

        ((UnitValue) ((StructureValue) mapList.get(1)).getValue().get("2a")).setValue("updated2");

        System.out.println("---> Updated map");
        map.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
        System.out.println("---> Updated list");
        for (int i = 0; i < mapList.size(); i++) {
            System.out.println(mapList.get(i));
        }

        ((UnitValue) ((StructureValue) mapList.get(2)).getValue().get("3b")).setValue("updated5");

        System.out.println("---> Updated map");
        map.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
        System.out.println("---> Updated list");
        for (int i = 0; i < mapList.size(); i++) {
            System.out.println(mapList.get(i));
        }
    }
}
