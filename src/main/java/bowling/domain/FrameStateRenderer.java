package bowling.domain;

import java.util.Objects;

public class FrameStateRenderer implements Renderer {

    private static final String READY_FRAME_STATE_FORMAT = "      ";
    private static final String PROCEEDING_FRAME_STATE_FORMAT = "  %s   ";
    private static final String MISS_FRAME_STATE_FORMAT = "  %s|%s ";
    private static final String GUTTER = "-";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";

    private static final FrameStateRenderer ready = new FrameStateRenderer(READY_FRAME_STATE_FORMAT);

    private final String state;

    private FrameStateRenderer(String state) {
        this.state = Objects.requireNonNull(state);
    }

    public static FrameStateRenderer of() {
        return ready;
    }

    public static FrameStateRenderer of(PinCount pinCount) {
        return new FrameStateRenderer(String.format(PROCEEDING_FRAME_STATE_FORMAT, render(pinCount)));
    }

    public static FrameStateRenderer of(PinCount first, PinCount second) {
        if (second.spare(first)) {
            return new FrameStateRenderer(String.format(MISS_FRAME_STATE_FORMAT, render(first), SPARE));
        }
        return new FrameStateRenderer(String.format(MISS_FRAME_STATE_FORMAT, render(first), render(second)));
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

    @Override
    public String render() {
        return state;
    }
}
