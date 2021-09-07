package bowling.domain;

import bowling.exception.InputException;

import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int MAX_PIN_COUNT = 10;
    private static final int MAX_ORDER = 2;
    private static final String EXCESS_MAX_ORDER_ERROR = "투구는 " + MAX_ORDER + "번만 가능합니다.";
    private static final String EXCESS_MAX_PIN_COUNT_ERROR = "쓰러트릴 수 있는 핀의 갯수는 최대 " + MAX_PIN_COUNT + "개 입니다.";

    private int order = 1;
    private boolean canNextTry = true;
    private int countOfPin;
    private int frameNumber;

    public NormalFrame(int countOfPin, int frameNumber) {
        this.countOfPin = countOfPin;
        this.frameNumber = frameNumber;
        if (countOfPin == MAX_PIN_COUNT) {
            canNextTry = false;
        }
    }

    public int addPinCount(int countOfPin) {
        if (order == MAX_ORDER || !canNextTry) {
            throw new InputException(EXCESS_MAX_ORDER_ERROR);
        }
        if (countOfPin > MAX_PIN_COUNT) {
            throw new InputException(EXCESS_MAX_PIN_COUNT_ERROR);
        }
        order++;
        canNextTry = false;
        this.countOfPin += countOfPin;
        return countOfPin;
    }

    public Frame nextFrame(int countOfPin){
        if(frameNumber + 1 == MAX_PIN_COUNT) {
            return new FinalFrame(countOfPin);
        }
        return new NormalFrame(countOfPin, frameNumber+1);
    }

    public ScoreType getScore() {
        return ScoreType.of(order, countOfPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return order == that.order &&
                canNextTry == that.canNextTry &&
                countOfPin == that.countOfPin &&
                frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, canNextTry, countOfPin, frameNumber);
    }
}
