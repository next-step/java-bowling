package bowling;

import java.util.Objects;

public class FrameNumber {
    public static final int FIRST = 1;
    private final int frameNumber;

    public static FrameNumber first() {
        return new FrameNumber();
    }

    public static FrameNumber number(int number) {
        return new FrameNumber(number);
    }

    public FrameNumber next() {
        return new FrameNumber(this.frameNumber + 1);
    }

    private FrameNumber(int frameNumber) {
        if (frameNumber < FIRST) {
            throw new IllegalArgumentException(String.format("프레임의 숫자는 {}보다 작을 수 없습니다.", FIRST));
        }
        this.frameNumber = frameNumber;
    }

    private FrameNumber() {
        this.frameNumber = FIRST;
    }

    public int retrieveIndexNumber() {
        return frameNumber - 1;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public boolean isBelow(int number) {
        return frameNumber <= number;
    }

    public boolean isSameNumber(int number) {
        return this.frameNumber == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNumber that = (FrameNumber) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
