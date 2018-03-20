package com.beauty.myweb.user.controller;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import javax.annotation.Nullable;

public class Foo implements Comparable<Foo>{

    public static Ordering<Foo> fooOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
        public String apply(Foo foo) {
            return foo.sortedBy;
        }
    });

    @Nullable
    String sortedBy;
    int notSortedBy;

    @Nullable
    public String getSortedBy() {
        return sortedBy;
    }

    public void setSortedBy(@Nullable String sortedBy) {
        this.sortedBy = sortedBy;
    }

    public int getNotSortedBy() {
        return notSortedBy;
    }

    public void setNotSortedBy(int notSortedBy) {
        this.notSortedBy = notSortedBy;
    }

    @Override
    public int compareTo(Foo o) {
        return 0;
    }
}
