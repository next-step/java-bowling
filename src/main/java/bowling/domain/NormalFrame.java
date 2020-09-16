package bowling.domain;

import bowling.domain.state.Ready;

import java.util.List;

public class NormalFrame implements Frame {

    private int number;
    private State state;

    public NormalFrame(int number) {
        this.number = number;
        this.state = new Ready();
    }

    public static Frame from() {
        return new NormalFrame(1);
    }

    @Override
    public Frame hit(int count) {
        state = state.roll(count);
        return this;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public List<String> toResults() {
        return state.toValues();
    }

    @Override
    public Score getScore() {
        return state.getScore();
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        return state.getScore().sum(beforeScore);
    }
}
