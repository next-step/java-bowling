package bowling.view;

import bowling.domain.FrameStatus;

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
        if (first != 10) {
            appendNext(sb, getNumberOrSign(first, second));
            return appendNext(sb, third).toString();
        }

        if (second == null) {
            return sb.toString();
        }

        appendNext(sb, second);

        if (second != 10) {
            return appendNext(sb, getNumberOrSign(second, third)).toString();
        }

        return appendNext(sb, third).toString();
    }

    private String getNumberOrSign(Integer aNumber, Integer nextNumber) {
        if (nextNumber != null && aNumber + nextNumber == 10) {
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
        if (fallingPins == 0)
            return "-";
        if (fallingPins == 10)
            return "X";
        return String.valueOf(fallingPins);
    }
}
