package com.seok2.bowling.frame.domain;

import java.util.Objects;

public class Index {

    public static final int MAX = 10;

    private static final int MIN = 1;
    private static final int END_THRESHOLD = 9;

    private int idx;

    private Index(int idx) {
        this.idx = idx;
    }

    protected static Index first() {
        return of(MIN);
    }

    protected static Index of(int idx) {
        return new Index(idx);
    }

    public Index increment() {
        return of(++idx);
    }

    public boolean isThreshold() {
        return idx == END_THRESHOLD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Index)) {
            return false;
        }
        Index index = (Index) o;
        return idx == index.idx;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx);
    }

}
