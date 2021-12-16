package com.anstis.maplist.examples;

import java.util.HashMap;
import java.util.Map;

import com.anstis.maplist.MapList;
import com.anstis.maplist.MapListImpl;

public class Simple {

    static class SimpleContent {

        String content;

        public SimpleContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public void run() {
        System.out.println("==> SIMPLE");

        final Map<String, SimpleContent> map = new HashMap<>();
        map.put("1", new SimpleContent("item1"));
        map.put("2", new SimpleContent("item2"));
        map.put("3", new SimpleContent("item3"));
        MapList<String, SimpleContent> mapList = new MapListImpl<>(map);

        System.out.println("---> Original map");
        map.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
        System.out.println("---> Original list");
        for (int i = 0; i < mapList.size(); i++) {
            System.out.println(mapList.get(i));
        }

        mapList.get(0).content = "updated1";

        System.out.println("---> Updated map");
        map.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
        System.out.println("---> Updated list");
        for (int i = 0; i < mapList.size(); i++) {
            System.out.println(mapList.get(i));
        }

        mapList.get(1).content = "updated2";

        System.out.println("---> Updated map");
        map.forEach((key, value) -> System.out.printf("%s=%s%n", key, value));
        System.out.println("---> Updated list");
        for (int i = 0; i < mapList.size(); i++) {
            System.out.println(mapList.get(i));
        }
    }
}
