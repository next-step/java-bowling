package com.hyoj.bowling.domain.status;

public class Spare implements ResultStatus {
    public static final String MARK = "/";
    private static final Spare instance = new Spare();

    private Spare() {}

    public static Spare getInstance() {
        return instance;
    }

    @Override
    public boolean canOneMoreShotAtFinal() {
        return true;
    }

    @Override
    public String toString() {
        return MARK;
    }
}
