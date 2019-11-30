package bowling.domain;

import java.util.Objects;

public class Frame {

    private static final int DECREASING_INDEX = 1;
    private static final int INIT_REMAIN_COUNT = 1;
    private static final int MAX_PIN_COUNT = 10;

    private Pin pin;
    private int countOfRemain;

    private Frame(int countOfHit, int countOfRemain) {
        this.pin = new Pin(countOfHit);
        this.countOfRemain = countOfRemain;
    }

    private static Frame of(int countOfHit, int countOfRemain) {
        return new Frame(countOfHit, countOfRemain);
    }

    public static Frame firstFrame(int countOfHit) {
        return of(countOfHit, INIT_REMAIN_COUNT);
    }

    public Frame nextFrame(int countOfHit) {
        if (pin.isAvailable(countOfHit)) {
            return of(countOfHit, countOfRemain - DECREASING_INDEX);
        }
        throw new IllegalArgumentException("잘 못된 점수 입니다.");
    }

    public String getScore(int score) {
        if (isSecond()) {
            return new Score(pin.getCountOfHit() + score, countOfRemain).getScore();
        } else {
            return getScoreFirst(score);
        }
    }

    private String getScoreFirst(int score) {
        if (pin.isMiss(score)) {
            return new Score(pin.getCountOfHit(), countOfRemain).getScore();
        }
        return new Score(pin.getCountOfHit() + score, countOfRemain).getScore();
    }

    public int getCountOfHit() {
        return pin.getCountOfHit();
    }

    public boolean isSecond() {
        return countOfRemain == INIT_REMAIN_COUNT && !pin.equals(new Pin(MAX_PIN_COUNT));
    }

    public boolean isRemain() {
        return countOfRemain == INIT_REMAIN_COUNT;
    }

    public boolean isStrike() {
        return pin.equals(new Pin(MAX_PIN_COUNT));
    }

    public Pin hit() {
        return pin;
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
