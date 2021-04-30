package bowling.view;

import bowling.domain.frame.Frame;

public final class FrameStatusView {

    private static final String DELIMITER = "|";
    private static final String STRIKE_SIGN = "X";
    private static final String SPARE_SIGN = "/";
    private static final String GUTTER_SIGN = "-";
    private static final String EMPTY_STRING = "";

    private final Frame frame;

    public FrameStatusView(Frame frame) {
        this.frame = frame;
    }

    public String frameStatus() {
        if (frame.isNotStarted()) {
            return EMPTY_STRING;
        }
        if (frame.isFinalFrame()) {
            return finalFrameStatus();
        }
        return normalFrameStatus();
    }

    private String normalFrameStatus() {
        return null;
    }

    private String finalFrameStatus() {
        return null;
    }
}
