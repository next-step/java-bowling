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
        if (first() == null) {
            return builder.toString();
        }
        builder.append(toNumberOrGutter(first()));
        if (!isStrike(first())) {
            appendNext(getNumberOrSign(first(), second()));
            return appendNext(third()).toString();
        }

        if (second() == null) {
            return builder.toString();
        }

        appendNext(second());

        if (!isStrike(second())) {
            return appendNext(getNumberOrSign(second(), third())).toString();
        }

        return appendNext(third()).toString();
    }

    private Integer third() {
        return frameStatus.getThird();
    }

    private Integer second() {
        return frameStatus.getSecond();
    }

    private Integer first() {
        return frameStatus.getFirst();
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
