package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.CommonState;
import bowling.domain.state.pitching.FirstPitching;

import java.util.List;

public class CommonFrame extends Frame {
    private static final int COUNT_OF_COMMON_FRAME = 9;

    private Frame nextFrame;

    private CommonFrame(CommonState state) {
        super(state);
        nextFrame = InitialFrame.of();
    }

    public static CommonFrame of() {
        return new CommonFrame(FirstPitching.of());
    }

    @Override
    public Score getScore() {
        return nextFrame.addBonusScore(state.score());
    }

    @Override
    protected void addFrame(List<Frame> frames) {
        if (isFrameNotFinish()) {
            return;
        }

        createNextFrame(frames);
    }

    @Override
    protected Score addBonusScore(Score score) {
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
