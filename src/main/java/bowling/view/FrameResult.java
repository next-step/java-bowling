package bowling.view;

import bowling.domain.Frame;
import bowling.domain.ScoreType;

public class FrameResult {

    private final Frame frame;

    public FrameResult(Frame frame) {

        this.frame = frame;
    }

    public String createFrameScore() {

        if (isFirstFrame(frame)) {
            return "";
        }

        ScoreType score = frame.status();
        if (score.equals(ScoreType.SPARE)) {
            return String.format("  %d|/ |", frame.pinNumber(0));
        }

        if (frame.pinsSize() == 2) {
            return String.format("  %d|%d |", frame.pinNumber(0), frame.pinNumber(1));
        }

        if (frame.pinsSize() == 3) {
            return String.format(" %d|/|%d|", frame.pinNumber(0), frame.pinNumber(2));
        }

        return String.format("  %d   |", frame.pinNumber(0));
    }

    private boolean isFirstFrame(Frame frame) {
        return frame.getNumber() == 1 && frame.isEmpty();
    }
}
