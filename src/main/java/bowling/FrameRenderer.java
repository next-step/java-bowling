package bowling;

import bowling.domain.PinCount;

import java.util.Objects;

public class FrameRenderer {

    private static final String PROCEEDING_FRAME_STATE_FORMAT = "  %s   ";
    private static final String STRIKE_FRAME_STATE_FORMAT = "  X   ";
    private static final String SPARE_FRAME_STATE_FORMAT = "  %s|/ ";
    private static final String MISS_FRAME_STATE_FORMAT = "  %s|%s ";

    private final String state;

    private FrameRenderer(String state) {
        this.state = Objects.requireNonNull(state);
    }

    public static FrameRenderer of(PinCount firstFallenPinCount) {
        return new FrameRenderer(String.format(PROCEEDING_FRAME_STATE_FORMAT, firstFallenPinCount));
    }

    public static FrameRenderer strike() {
        return new FrameRenderer(STRIKE_FRAME_STATE_FORMAT);
    }

    public static FrameRenderer spare(PinCount firstFallenPinCount) {
        return new FrameRenderer(String.format(SPARE_FRAME_STATE_FORMAT, firstFallenPinCount));
    }

    public static FrameRenderer miss(PinCount firstFallenPinCount, PinCount secondFallenPinCount) {
        return new FrameRenderer(String.format(MISS_FRAME_STATE_FORMAT, firstFallenPinCount, secondFallenPinCount));
    }

    public String renderState() {
        return state;
    }
}
