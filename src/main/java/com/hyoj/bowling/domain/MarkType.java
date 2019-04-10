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
        if (shotTimes == 0 && shot.isAllDown()) {
            return STRIKE;
        }

        if (shotTimes > 0 && shot.isAllDown()) {
            return SPARE;
        }

        if (shotTimes > 0 && shot.isAllStanding()) {
            return GUTTER;
        }

        return MISS;
    }

    @Override
    public String toString() {
        return looks;
    }
}
