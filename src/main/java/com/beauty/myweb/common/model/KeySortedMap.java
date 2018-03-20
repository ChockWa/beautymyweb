package com.beauty.myweb.common.model;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class KeySortedMap extends TreeMap<String,Object> {

    public KeySortedMap(Map<String,Object> map){
        super(new KeySortedComparator());
        this.putAll(map);
    }

    private static class KeySortedComparator implements Comparator<String>{

        @Override
        public int compare(String key1, String key2) {
            return key1.compareTo(key2);
        }
    }
}
