package com.seok2.bowling.frame.domain;

import java.util.Objects;
import java.util.stream.IntStream;

public class Index {

    public static final int MAX = 10;

    private static final int MIN = 1;
    private static final int INCREMENT_VALUE = 1;
    private static final int END_THRESHOLD = 9;
    private static final Index [] CACHE = IntStream.rangeClosed(MIN,MAX)
        .mapToObj(Index::new)
        .toArray(Index[]::new);


    private int idx;

    private Index(int idx) {
        this.idx = idx;
    }

    protected static Index first() {
        return of(MIN);
    }

    protected static Index of(int idx) {
        try{
            return CACHE[--idx];
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("프레임 인덱스의 범위는 1보다 작거나 10보다 클 수 없습니다.");
        }
    }

    public Index increment() {
        return of(idx + INCREMENT_VALUE);
    }

    public boolean isThreshold() {
        return idx == END_THRESHOLD;
    }

    public int getIndex() {
        return idx;
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
