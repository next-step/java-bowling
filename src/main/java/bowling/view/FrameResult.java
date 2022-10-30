package bowling.view;

import bowling.domain.Frame;
import bowling.domain.ScoreType;

import static bowling.domain.Pin.MAX_COUNT;

public class FrameResult {
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";

    private final Frame frame;

    public FrameResult(Frame frame) {
        this.frame = frame;
    }

    public String getFrameScore() {
        return createFrameScore().replaceAll("10", STRIKE)
                .replaceAll("0", GUTTER);
    }

    public String createFrameScore() {
        if (isFirstFrame(frame)) {
            return "     ";
        }

        if (frame.pinsSize() == 3) {
            if (isFirstSpare(frame)) {
                return String.format("%d|/|%d", frame.pinNumber(0), frame.pinNumber(2));
            }

            if (isSecondSpare(frame)) {
                return String.format("%d|%d|/", frame.pinNumber(0), frame.pinNumber(1));
            }

            return String.format("%d|%d|%d", frame.pinNumber(0), frame.pinNumber(1), frame.pinNumber(2));
        }

        ScoreType score = frame.status();
        if (score.equals(ScoreType.SPARE)) {
            return String.format("  %d|/", frame.pinNumber(0));
        }

        if (frame.pinsSize() >= 2) {
            return String.format("  %d|%d", frame.pinNumber(0), frame.pinNumber(1));
        }

        return String.format("  %d  ", frame.pinNumber(0));
    }

    private boolean isFirstFrame(Frame frame) {
        return frame.number() == 1 && frame.isEmpty();
    }

    private boolean isFirstSpare(Frame frame) {
        return frame.pinNumber(0) != MAX_COUNT &&
                frame.pinNumber(0) + frame.pinNumber(1) == MAX_COUNT;
    }

    private boolean isSecondSpare(Frame frame) {
        return frame.pinNumber(1) != MAX_COUNT &&
                frame.pinNumber(1) + frame.pinNumber(2) == MAX_COUNT;
    }
}
