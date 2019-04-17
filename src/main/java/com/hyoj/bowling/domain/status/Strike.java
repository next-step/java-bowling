package com.hyoj.bowling.domain.status;

public class Strike implements ResultStatus {
    public static final String MARK = "X";
    private static final Strike instance = new Strike();

    private Strike() {}

    public static Strike getInstance() {
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
