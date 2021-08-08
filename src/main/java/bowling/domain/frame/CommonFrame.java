package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.state.CommonState;
import bowling.domain.state.pitching.FirstPitching;

import java.util.List;

public class CommonFrame implements Frame {
    public static final int START_NUMBER = 1;
    public static final int END_NUMBER = 10;
    private static final int LIMIT_COUNT_OF_COMMON_FRAME = 9;

    private CommonState state;

    private CommonFrame(CommonState state) {
        this.state = state;
    }

    public static CommonFrame of() {
        return new CommonFrame(FirstPitching.of());
    }

    @Override
    public boolean isBowlingFinish() {
        return false;
    }

    private boolean isFrameFinish() {
        return false;
    }

    private void createNextFrame(List<Frame> frames) {
        if (frames.size() < LIMIT_COUNT_OF_COMMON_FRAME) {
            frames.add(CommonFrame.of());
            return;
        }

        frames.add(LastFrame.of());
    }

    @Override
    public void hitPins(Pins pins) {
        this.state = state.hitPins(pins);
    }

    @Override
    public void addFrame(List<Frame> frames) {
        if (isFrameFinish()) {
            createNextFrame(frames);
        }
    }

}
