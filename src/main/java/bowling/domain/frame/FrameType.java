package bowling.domain.frame;

public enum FrameType {
    NORMAL, FINAL;

    public static FrameType valueOf(Frame frame) {
        if (frame instanceof FinalFrame) {
            return FINAL;
        }

        return NORMAL;
    }
}
