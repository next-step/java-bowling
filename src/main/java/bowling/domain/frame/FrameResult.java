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

    public static String get(final Frame frame) {
        if (frame.getFirst().isStrike()) {
            return STRIKE.flag;
        }

        if (frame.isEnd()) {
            if (frame.getFirst().getNumber() + frame.getSecond().getNumber() == 10) {
                return frame.getFirst().getNumber() + DELIMITER + SPARE.flag;
            }

            return (frame.getFirst().getNumber() + DELIMITER + frame.getSecond().getNumber())
                .replaceAll("0", GUTTER.flag);
        }

        return String.valueOf(frame.getFirst().getNumber());
    }
}
