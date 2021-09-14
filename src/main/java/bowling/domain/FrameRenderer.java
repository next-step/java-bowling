package bowling.domain;

import java.util.Objects;

public class FrameRenderer implements Renderer {

    private static final String EMPTY_FRAME_STATE_FORMAT = "      ";
    private static final String ONE_FRAME_STATE_FORMAT = "  %s   ";
    private static final String TWO_FRAME_STATE_FORMAT = "  %s ";
    private static final String THREE_FRAME_STATE_FORMAT = " %s";
    private static final String GUTTER = "-";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String PIN_COUNT_DELIMITER = "|";

    private static final FrameRenderer ready = new FrameRenderer(EMPTY_FRAME_STATE_FORMAT);

    private final String state;

    private FrameRenderer(String state) {
        this.state = Objects.requireNonNull(state);
    }

    public static FrameRenderer of(PinCount first, PinCount second) {
        return of(first, second, PinCount.UNDEFINED);
    }

    public static FrameRenderer of(PinCount first, PinCount second, PinCount third) {
        if (first.isDefined() && second.isDefined() && third.isDefined()) {
            return new FrameRenderer(String.format(THREE_FRAME_STATE_FORMAT, render(first, second, third)));
        }
        if (first.isDefined() && second.isDefined()) {
            return new FrameRenderer(String.format(TWO_FRAME_STATE_FORMAT, render(first, second)));
        }
        if (first.isDefined()) {
            return new FrameRenderer(String.format(ONE_FRAME_STATE_FORMAT, render(first)));
        }
        return ready;
    }

    private static String render(PinCount pinCount) {
        if (PinCount.ZERO.equals(pinCount)) {
            return GUTTER;
        }
        if (PinCount.TEN.equals(pinCount)) {
            return STRIKE;
        }
        return pinCount.toString();
    }

    private static String render(PinCount first, PinCount second) {
        if (second.spare(first)) {
            return String.join(PIN_COUNT_DELIMITER, render(first), SPARE);
        }
        return String.join(PIN_COUNT_DELIMITER, render(first), render(second));
    }

    private static String render(PinCount first, PinCount second, PinCount third) {
        if (second.spare(first)) {
            return String.join(PIN_COUNT_DELIMITER, render(first), SPARE, render(third));
        }
        return String.join(PIN_COUNT_DELIMITER, render(first), render(second, third));
    }

    @Override
    public String render() {
        return state;
    }
}
