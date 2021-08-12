package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.CommonState;
import bowling.domain.state.pitching.FirstPitching;
import bowling.domain.dto.StateData;

import java.util.List;

public class CommonFrame implements Frame {
    public static final int START_COUNT = 1;
    public static final int END_COUNT = 10;
    private static final int COUNT_OF_COMMON_FRAME = 9;

    private CommonState state;
    private Frame nextFrame;

    private CommonFrame(CommonState state) {
        this.state = state;
        nextFrame = InitialFrame.of();
    }

    public static CommonFrame of() {
        return new CommonFrame(FirstPitching.of());
    }

    @Override
    public boolean isBowlingFinish() {
        return false;
    }

    @Override
    public void hitPins(Pins pins) {
        this.state = state.hitPins(pins);
    }

    @Override
    public void addFrame(List<Frame> frames) {
        if (isFrameNotFinish()) {
            return;
        }

        createNextFrame(frames);
    }

    @Override
    public StateData getFrameStates() {
        return StateData.of(state.getState());
    }

    @Override
    public Score getScore() {
        return nextFrame.addBonusScore(state.score());
    }

    @Override
    public Score addBonusScore(Score score) {
        Score addedScore = state.addScore(score);

        return nextFrame.addBonusScore(addedScore);
    }

    private boolean isFrameNotFinish() {
        return !state.isFinish();
    }

    private void createNextFrame(List<Frame> frames) {
        if (frames.size() < COUNT_OF_COMMON_FRAME) {
            updateNextFrame(frames, CommonFrame.of());
            return;
        }

        updateNextFrame(frames, LastFrame.of());
    }

    private void updateNextFrame(List<Frame> frames, Frame nextFrame) {
        this.nextFrame = nextFrame;
        frames.add(nextFrame);
    }

}
