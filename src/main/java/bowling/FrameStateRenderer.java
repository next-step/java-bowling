package bowling;

import bowling.domain.PinCount;

import java.util.Objects;

public class FrameStateRenderer {

    private static final String READY_FRAME_STATE_FORMAT = "      ";
    private static final String STRIKE_FRAME_STATE_FORMAT = "  X   ";
    private static final String PROCEEDING_FRAME_STATE_FORMAT = "  %s   ";
    private static final String SPARE_FRAME_STATE_FORMAT = "  %s|/ ";
    private static final String MISS_FRAME_STATE_FORMAT = "  %s|%s ";

    private static final FrameStateRenderer ready = new FrameStateRenderer(READY_FRAME_STATE_FORMAT);
    private static final FrameStateRenderer strike = new FrameStateRenderer(STRIKE_FRAME_STATE_FORMAT);

    private final String state;

    private FrameStateRenderer(String state) {
        this.state = Objects.requireNonNull(state);
    }

    public static FrameStateRenderer ready() {
        return ready;
    }

    public static FrameStateRenderer strike() {
        return strike;
    }

    public static FrameStateRenderer of(PinCount firstFallenPinCount) {
        return new FrameStateRenderer(String.format(PROCEEDING_FRAME_STATE_FORMAT, firstFallenPinCount));
    }

    public static FrameStateRenderer spare(PinCount firstFallenPinCount) {
        return new FrameStateRenderer(String.format(SPARE_FRAME_STATE_FORMAT, firstFallenPinCount));
    }

    public static FrameStateRenderer miss(PinCount firstFallenPinCount, PinCount secondFallenPinCount) {
        return new FrameStateRenderer(String.format(MISS_FRAME_STATE_FORMAT, firstFallenPinCount, secondFallenPinCount));
    }

    public String renderState() {
        return state;
    }
}
