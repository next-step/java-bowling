package bowling.domain.frame;

import bowling.domain.pitch.PitchResult;

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
        if (frame instanceof LastFrame) {
            final LastFrame lastFrame = (LastFrame) frame;
            return lastFrame.getFirst().getNumber() + DELIMITER
                + lastFrame.getSecond().getNumber() + DELIMITER
                + lastFrame.getBonus().getNumber();
        }

        if (frame.getFirst().getPitchResult().equals(PitchResult.STRIKE)) {
            return STRIKE.flag;
        }

        if (frame.isEnd()) {
            return fromEndFrame(frame);
        }

        return String.valueOf(frame.getFirst().getNumber());
    }

    private static String fromEndFrame(final Frame frame) {
        if (frame.getFirst().getNumber() + frame.getSecond().getNumber() == 10) {
            return frame.getFirst().getNumber() + DELIMITER + SPARE.flag;
        }

        final String result = frame.getFirst().getNumber() + DELIMITER + frame.getSecond().getNumber();
        return result.replaceAll("0", GUTTER.flag);
    }
}
