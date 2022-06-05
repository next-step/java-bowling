package bowling.domain.frame;

public class FrameNumber {
    public static final int LAST_FRAME_NUMBER = 9;
    public static final int START_FRAME_NUMBER = 0;

    private final int number;

    public FrameNumber(int frameNumber) {
        validateFrameNumber(frameNumber);
        this.number = frameNumber;
    }

    private void validateFrameNumber(int frameNumber) {
        if (frameNumber > LAST_FRAME_NUMBER || frameNumber < START_FRAME_NUMBER) {
            throw new IllegalArgumentException(String.format("프레임 넘버는 0 ~ 9를 벗어날 수 없습니다. 전달 받은 프레임 넘버 : %d", frameNumber));
        }
    }

    public FrameNumber nextFrameNumber() {
        validateNextFrameNumber();
        return new FrameNumber(this.number + 1);
    }

    private void validateNextFrameNumber() {
        if (this.number + 1 > LAST_FRAME_NUMBER) {
            throw new IllegalArgumentException(String.format("다음 프레임 넘버는 최대 넘버인 9를 초과합니다. 현재 프레임 넘버 : %d", this.number));
        }
    }

    public boolean isLast() {
        return this.number == LAST_FRAME_NUMBER;
    }

    public int frameNumber() {
        return this.number;
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
        return number;
    }
}