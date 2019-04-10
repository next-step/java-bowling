package com.hyoj.bowling.domain;

public enum MarkType {
    STRIKE("X"),
    SPARE("/"),
    MISS("N"),
    GUTTER("-");

    private String looks;

    MarkType(String looks) {
        this.looks = looks;
    }

    public static MarkType makeMarkType(final int shotTimes, final Shot shot) {
        if (shot.isAllDown() && shotTimes == 1) {
            return STRIKE;
        }

        if (shot.isAllDown() && shotTimes > 1) {
            return SPARE;
        }

        if (shot.isAllStanding()) {
            return GUTTER;
        }

        return MISS;
    }

    @Override
    public String toString() {
        return looks;
    }
}
