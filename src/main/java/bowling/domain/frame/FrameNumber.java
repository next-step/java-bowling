package bowling.domain.frame;

public class FrameNumber {
    public static final int FINAL_FRAME_NUMBER = 10;

    private final int frameNumber;

    public FrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int increase() {
        return frameNumber + 1;
    }

    public boolean isNextFinal() {
        return frameNumber == FINAL_FRAME_NUMBER - 1;
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
