package bowling.domain;

import bowling.exception.InputException;

import java.util.Objects;

public class FinalFrame implements Frame {
    private int order = 1;
    private int countOfPoint;

    public FinalFrame(int countOfPin) {
        countOfPin = countOfPoint;
    }

    @Override
    public Frame next(int countOfPin) {
        return null;
    }

    @Override
    public String resultOfFrame() {
        return null;
    }
}
