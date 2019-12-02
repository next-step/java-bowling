package bowling.domain;

import java.util.Objects;

public class Frame {

    private static final int DECREASING_INDEX = 1;
    private static final int INIT_REMAIN_COUNT = 1;
    private static final int MIN_REMAIN_COUNT = 0;
    private static final int MAX_PIN_COUNT = 10;
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";
    private static final String SPARE = "/";

    private Pin pin;
    private int countOfRemain;

    private Frame(int countOfHit, int countOfRemain) {
        this.pin = new Pin(countOfHit);
        this.countOfRemain = countOfRemain;
    }

    private static Frame of(int countOfHit, int countOfRemain) {
        return new Frame(countOfHit, countOfRemain);
    }

    public static Frame frame(int countOfHit) {
        return of(countOfHit, INIT_REMAIN_COUNT);
    }

    public Frame nextFrame(int countOfHit) {
        if (pin.isAvailable(countOfHit)) {
            return of(countOfHit, countOfRemain - DECREASING_INDEX);
        }
        throw new IllegalArgumentException("잘 못된 점수 입니다.");
    }

    public Frame nextFinalFrame(int countOfHit) {
        if (pin.isAvailableLastFrame(countOfHit)) {
            return of(countOfHit, countOfRemain - DECREASING_INDEX);
        }
        throw new IllegalArgumentException("잘 못된 점수 입니다.");
    }

    public Frame bonusFrame(int countOfHit) {
        return of(countOfHit, INIT_REMAIN_COUNT);
    }

    public String getScoreByNormalFrame(int previousScore) {
        if (pin.isStrike() && countOfRemain >= 1) {
            return STRIKE;
        }

        if (pin.isSpare(previousScore)) {
            return SPARE;
        }

        if (pin.isGutter()) {
            return GUTTER;
        }

        return String.valueOf(pin.getCountOfHit());
    }

    public String getScoreByFinalFrame(int previousScore) {
        if (pin.isStrike()) {
            return STRIKE;
        }

        if (pin.isSpare(previousScore)) {
            return SPARE;
        }

        if (pin.isGutter()) {
            return GUTTER;
        }

        return String.valueOf(pin.getCountOfHit());
    }

    public boolean isRemain() {
        return countOfRemain > MIN_REMAIN_COUNT;
    }

    public boolean isBonus(int countOfHit) {
        return isStrike() || getScoreByFinalFrame(countOfHit).equals(SPARE);
    }

    public boolean isStrike() {
        return pin.equals(new Pin(MAX_PIN_COUNT)) && countOfRemain >= INIT_REMAIN_COUNT ;
    }

    public int getCountOfHit() {
        return pin.getCountOfHit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return countOfRemain == frame.countOfRemain &&
                Objects.equals(pin, frame.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, countOfRemain);
    }
}
