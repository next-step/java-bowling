package com.jaeyeonling.bowling;

public class Counter {

    private int count;

    public void count() {
        count++;
    }

    public boolean isHigherAndEquals(final int count) {
        return this.count >= count;
    }

    public boolean isLowerAndEquals(final int count) {
        return this.count <= count;
    }
}
