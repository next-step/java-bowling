package bowling.domain.frame;

public class FrameIndex {
    private static final int MIN_FRAME_INDEX = 0;
    private static final int MAX_FRAME_INDEX = 9;
    private static final String FRAME_MIN_INDEX_ERROR = "프레임은 0보다 커야합니다.";
    private int index;

    public FrameIndex(int index) {
        if (index < MIN_FRAME_INDEX) {
            throw new IllegalArgumentException(FRAME_MIN_INDEX_ERROR);
        }
        this.index = index;
    }

    public static FrameIndex create(int index) {
        return new FrameIndex(index);
    }

    public int getIndex() {
        return index;
    }

    public boolean isLast() {
        return this.index == MAX_FRAME_INDEX;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}

