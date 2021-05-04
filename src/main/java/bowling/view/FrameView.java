package bowling.view;

import bowling.domain.Frame;

import static bowling.util.BowlingFixture.*;

public abstract class FrameView {

    protected static final String STRIKE = "X";
    protected static final String SPARE = "/";
    protected static final String DELIMITER = "|";
    protected static final String GUTTER = "-";
    protected static final String BLANK = "   ";
    protected static final String NORMAL_SCORE_FORMAT = "  %-3s ";
    protected static final String FINAL_SCORE_FORMAT = " %-5s";
    protected static final String NEW_LINE = "\n";

    protected abstract String twiceScore(final Frame frame);

    protected abstract String formatFrame(final String frameResult);

    protected final String formatFrameScore(final Frame frame) {
        return formatFrame(formatScore(frame));
    }

    protected final String formatScore(final Frame frame) {
        if (frame.size() == ONE) {
            return oneScore(frame);
        }
        if (frame.size() == TWO) {
            return twiceScore(frame);
        }
        if (frame.size() == THREE) {
            return thirdScore(frame);
        }
        return emptyScore();
    }

    protected final String thirdScore(final Frame frame) {
        final String first = mapToSign(ZERO, frame.firstCount(), true);
        final String second = mapToSign(frame.firstCount(), frame.secondCount(), true);
        final String third = mapToSign(ZERO, frame.thirdCount(), true);
        return first + DELIMITER + second + DELIMITER + third;
    }

    protected final String mapToSign(final int prevCount, final int nowCount, final boolean availableStrike) {
        if (nowCount == ALL_PIN_CLEAR && availableStrike) {
            return STRIKE;
        }
        if (prevCount + nowCount == ALL_PIN_CLEAR) {
            return SPARE;
        }
        if (nowCount == ANYTHING_NOT_HIT) {
            return GUTTER;
        }
        return String.valueOf(nowCount);
    }

    protected final String emptyScore() {
        return BLANK;
    }

    protected final String oneScore(final Frame frame) {
        return mapToSign(ZERO, frame.firstCount(), true);
    }

}
