package bowling.view;

import bowling.domain.FrameStatus;

import static bowling.domain.BallThrow.MAX_PINS;
import static bowling.domain.BallThrow.MIN_PINS;

public class FrameStatusView extends FrameStatus {
    FrameStatusView(Integer first, Integer second, Integer third) {
        super(first, second, third);
    }

    public FrameStatusView(FrameStatus frameStatus) {
        super(frameStatus);
    }

    @Override
    public String toString() {
        if (first == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(toNumberOrGutter(first));
        if (!isStrike(first)) {
            appendNext(sb, getNumberOrSign(first, second));
            return appendNext(sb, third).toString();
        }

        if (second == null) {
            return sb.toString();
        }

        appendNext(sb, second);

        if (!isStrike(second)) {
            return appendNext(sb, getNumberOrSign(second, third)).toString();
        }

        return appendNext(sb, third).toString();
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

    private StringBuilder appendNext(StringBuilder sb, Integer next) {
        if (next != null) {
            appendNext(sb, toNumberOrGutter(next));
        }
        return sb;
    }

    private StringBuilder appendNext(StringBuilder sb, String next) {
        if (next != null) {
            sb.append("|");
            sb.append(next);
        }
        return sb;
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
