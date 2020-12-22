package bowling.domain;

import java.util.Objects;

public class FrameNumber {

    public static final int FRAME_START_NUMBER = 1;
    public static final int FRAME_LAST_NUMBER = 10;
    public static final String MIN_SIZE_ERROR = String.format("프레임 번호는 %s 미만 값은 생성할 수 없습니다.", FRAME_START_NUMBER);
    public static final String OVER_SIZE_ERROR = String.format("프레임 번호는 %s 초과 값은 생성할 수 없습니다.", FRAME_LAST_NUMBER);
    public static final int ADD_NUMBER = 1;

    private final int number;

    private FrameNumber(int number) {
        this.number = number;
        checkFrameNumber();
    }

    private void checkFrameNumber() {
        checkMinimumSizeNumber();
        checkOverSizeNumber();
    }

    private void checkMinimumSizeNumber() {
        if (number < FRAME_START_NUMBER) {
            throw new IllegalArgumentException(MIN_SIZE_ERROR);
        }
    }

    private void checkOverSizeNumber() {
        if (number > FRAME_LAST_NUMBER) {
            throw new IllegalArgumentException(OVER_SIZE_ERROR);
        }
    }

    public static FrameNumber from(int number) {
        return new FrameNumber(number);
    }

    public static FrameNumber init() {
        return from(FRAME_START_NUMBER);
    }

    public static FrameNumber last() {
        return from(FRAME_LAST_NUMBER);
    }

    public FrameNumber add() {
        return from(number + ADD_NUMBER);
    }

    public int getNumber() {
        return number;
    }

    public boolean isLastFrame() {
        return number == FRAME_LAST_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNumber that = (FrameNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
