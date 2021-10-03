package bowling.domain.frame;

public enum FrameType {

    NORMAL(2), FINAL(3), NONE(-1);

    private final int size;

    FrameType(int size) {
        this.size = size;
    }

    public static FrameType from(int size) {
        if (size == FrameType.NORMAL.size) {
            return FrameType.NORMAL;
        }

        if (size == FrameType.FINAL.size) {
            return FrameType.FINAL;
        }

        return FrameType.NONE;
    }
}
