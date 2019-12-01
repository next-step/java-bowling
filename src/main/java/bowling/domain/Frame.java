package bowling.domain;

import java.util.Objects;

public class Frame {

    private static final int DECREASING_INDEX = 1;
    private static final int INIT_REMAIN_COUNT = 1;
    private static final int INIT_FINAL_FRAME_REMAIN_COUNT = 1;
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

    public static Frame normalFrame(int countOfHit) {
        return of(countOfHit, INIT_REMAIN_COUNT);
    }

    public Frame nextFrame(int countOfHit) {
        if (pin.isAvailable(countOfHit)) {
            return of(countOfHit, countOfRemain - DECREASING_INDEX);
        }
        throw new IllegalArgumentException("잘 못된 점수 입니다.");
    }

    public static Frame finalFrame(int countOfHit) {
        return of(countOfHit, INIT_FINAL_FRAME_REMAIN_COUNT);
    }

    public Frame nextFinalFrame(int countOfHit) {
        if (pin.isAvailableLastFrame(countOfHit)) {
            return of(countOfHit, countOfRemain - DECREASING_INDEX);
        }
        throw new IllegalArgumentException("잘 못된 점수 입니다.");
    }

    public Frame bonusFrame(int countOfHit) {
        return of(countOfHit, countOfRemain - DECREASING_INDEX);
    }

    public String getScore(int score) {
        if (pin.isStrike()) {
            return STRIKE;
        }

        if (pin.isSpare(score)) {
            return SPARE;
        }

        if (pin.isGutter()) {
            return GUTTER;
        }

        return String.valueOf(pin.getCountOfHit());
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

    public boolean isRemainLastFrame() {
        return countOfRemain == 2;
    }

    public boolean isBonus(int countOfHit) {
        System.out.println(getScore(countOfHit));
        return isStrike() || getScore(countOfHit).equals("/");
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
