package bowling.domain.frame;

public enum FrameResult {

    STRIKE("X"),
    SPARE("/"),
    MISS(""),
    GUTTER("-");

    private static final String DELIMITER = "|";
    private final String flag;

    FrameResult(final String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public static String get(final Frame frame) {
        if (frame.isStrike()) {
            return STRIKE.flag;
        }

        if (frame.isEnd()) {
            return fromEndFrame(frame);
        }

        return String.valueOf(frame.getFirst());
    }

    private static String fromEndFrame(final Frame frame) {
        if (frame.isSpare()) {
            return frame.getFirst() + DELIMITER + SPARE.flag;
        }

        final String result = frame.getFirst() + DELIMITER + frame.getSecond();
        return result.replaceAll("0", GUTTER.flag);
    }
}
