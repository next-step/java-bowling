package com.jaeyeonling.bowling.domain;

// TODO: atomic?
public class Counter {

    private static final int DEFAULT = 0;

    private int count;

    private Counter(final int count) {
        this.count = count;
    }

    public static Counter initialize() {
        return initialize(DEFAULT);
    }

    public static Counter initialize(final int initializeCount) {
        return new Counter(initializeCount);
    }

    public void count() {
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
