package com.hyoj.bowling.domain.status;

public class None implements ResultStatus {
    public static final String MARK = "";
    private static final None instance = new None();

    private None() {}

    public static None getInstance() {
        return instance;
    }

    @Override
    public boolean canOneMoreShotAtFinal() {
        return false;
    }

    @Override
    public String toString() {
        return MARK;
    }
}
