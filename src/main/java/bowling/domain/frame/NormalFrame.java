package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.FirstPitch;
import bowling.domain.state.Pins;
import bowling.domain.state.State;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {

    public final int TOTAL_FRAME_COUNT = 10;

    private final int frameCount;
    private State state;
    private Frame nextFrame;


    public NormalFrame(int frameCount) {
        this.frameCount = frameCount;
        state = new FirstPitch();
    }

    @Override
    public List<Pins> getPinsList() {
        return Collections.singletonList(state.getPins());
    }

    @Override
    public int getFrameCount() {
        return frameCount;
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        return addNextFrame(score);
    }

    @Override
    public Score addScore(Score lastScore) {
        Score score = state.addNextScore(lastScore);
        return addNextFrame(score);
    }

    private Score addNextFrame(Score score) {
        if (score.isFinished() || Objects.isNull(nextFrame)) {
            return score;
        }
        return nextFrame.addScore(score);
    }

    @Override
    public Frame bowl(int count) {
        state = state.bowl(count);

        if (!state.isFinished()) {
            return this;
        }

        if (frameCount >= TOTAL_FRAME_COUNT - 1) {
            nextFrame = new FinalFrame();
            return nextFrame;
        }
        nextFrame = new NormalFrame(frameCount + 1);
        return nextFrame;
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }
}
