package com.jaeyeonling.bowling.domain;

public class Counter {

    static final int DEFAULT = 0;

    private int count;

    public Counter(final int initialValue) {
        this.count = initialValue;
    }

    public Counter() {
        this(DEFAULT);
    }

    public void countup() {
        count++;
    }

    public void countdown() {
        count--;
    }

    public boolean isHigherAndEquals(final int count) {
        return this.count >= count;
    }

    public boolean isLowerAndEquals(final int count) {
        return this.count <= count;
    }
}
