package com.hyoj.bowling.domain.status;

public class Miss implements ResultStatus {
    public static final String MARK = "";
    private static final Miss instance = new Miss();

    private Miss() {}

    public static Miss getInstance() {
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
