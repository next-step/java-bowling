package domain;

import domain.state.BowlState;

import java.util.List;

public abstract class Frame {
    static final int MINIMUM_FRAME = 1;
    static final int MAXIMUM_FRAME = 10;

    protected List<BowlState> states;
    protected int number;

    public Frame(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    protected abstract Frame bowl(int pins);
}