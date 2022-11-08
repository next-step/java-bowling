package bowling.domain.frame;

public class FrameNumber {
    public static final int FINAL_FRAME_NUMBER = 10;

    private final int frameNumber;

    public FrameNumber(int frameNumber) {
        if (frameNumber > FINAL_FRAME_NUMBER) {
            throw new IllegalArgumentException(String.format("프레임번호(%s)는 %s 을 초과할 수 없습니다", frameNumber, FINAL_FRAME_NUMBER));
        }

        this.frameNumber = frameNumber;
    }

    public FrameNumber increase() {
        return new FrameNumber(frameNumber + 1);
    }

    public boolean isFinal() {
        return frameNumber == FINAL_FRAME_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FrameNumber)) return false;

        FrameNumber that = (FrameNumber) o;

        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return frameNumber;
    }
}
