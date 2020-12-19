package bowling.view;

import bowling.domain.FrameStatus;

import static bowling.domain.BallThrow.MAX_PINS;
import static bowling.domain.BallThrow.MIN_PINS;

public class FrameStatusView {
    private final StringBuilder builder = new StringBuilder();
    private final FrameStatus frameStatus;

    FrameStatusView(Integer... fallingPinValues) {
        this(new FrameStatus(fallingPinValues));
    }

    public FrameStatusView(FrameStatus frameStatus) {
        this.frameStatus = frameStatus;
    }

    @Override
    public String toString() {
        if (frameStatus.getFirst() == null) {
            return builder.toString();
        }
        builder.append(toNumberOrGutter(frameStatus.getFirst()));
        if (!isStrike(frameStatus.getFirst())) {
            appendNext(getNumberOrSign(frameStatus.getFirst(), frameStatus.getSecond()));
            return appendNext(frameStatus.getThird()).toString();
        }

        if (frameStatus.getSecond() == null) {
            return builder.toString();
        }

        appendNext(frameStatus.getSecond());

        if (!isStrike(frameStatus.getSecond())) {
            return appendNext(getNumberOrSign(frameStatus.getSecond(), frameStatus.getThird())).toString();
        }

        return appendNext(frameStatus.getThird()).toString();
    }

    private String getNumberOrSign(Integer aNumber, Integer nextNumber) {
        if (nextNumber != null && aNumber + nextNumber == MAX_PINS) {
            return "/";
        }
        if (nextNumber == null) {
            return null;
        }
        return toNumberOrGutter(nextNumber);
    }

    private StringBuilder appendNext(Integer next) {
        if (next != null) {
            appendNext(toNumberOrGutter(next));
        }
        return builder;
    }

    private StringBuilder appendNext(String next) {
        if (next != null) {
            builder.append("|");
            builder.append(next);
        }
        return builder;
    }
    private String toNumberOrGutter(Integer fallingPins) {
        if (fallingPins == null) {
            return "";
        }
        if (isGutter(fallingPins))
            return "-";
        if (isStrike(fallingPins))
            return "X";
        return String.valueOf(fallingPins);
    }

    private boolean isStrike(Integer pins) {
        return pins == MAX_PINS;
    }

    private boolean isGutter(Integer fallingPins) {
        return fallingPins == MIN_PINS;
    }
}
