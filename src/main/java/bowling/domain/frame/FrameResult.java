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

    public static String get(final NormalFrame normalFrame) {
        if (normalFrame.isStrike()) {
            return STRIKE.flag;
        }

        if (normalFrame.isEnd()) {
            return fromEndFrame(normalFrame);
        }

        return String.valueOf(normalFrame.getFirst());
    }

    private static String fromEndFrame(final NormalFrame normalFrame) {
        if (normalFrame.isSpare()) {
            return normalFrame.getFirst() + DELIMITER + SPARE.flag;
        }

        final String result = normalFrame.getFirst() + DELIMITER + normalFrame.getSecond();
        return result.replaceAll("0", GUTTER.flag);
    }
}
