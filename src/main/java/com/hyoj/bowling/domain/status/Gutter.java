package com.hyoj.bowling.domain.status;

public class Gutter implements ResultStatus {
    public static final String MARK = "-";
    private static final Gutter instance = new Gutter();

    private Gutter() {}

    public static Gutter getInstance() {
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
