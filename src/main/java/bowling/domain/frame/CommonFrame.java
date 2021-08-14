package bowling.domain.frame;

import bowling.domain.dto.StateData;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.Start;

import java.util.List;

public class CommonFrame extends Frame {

    private static final int COUNT_OF_COMMON_FRAME = 9;

    private Frame nextFrame;
    private State state;

    private CommonFrame(State state) {
        this.state = state;
        nextFrame = StartingFrame.of();
    }

    public static CommonFrame of() {
        return new CommonFrame(Start.of());
    }

    @Override
    public Score getScore() {
        return nextFrame.addStateScore(state.score());
    }

    public void hitPins(Pins pins) {
        this.state = state.hitPins(pins);
    }

    public StateData getFrameStates() {
        return StateData.of(state.getState());
    }

    @Override
    public boolean isStartState() {
        return state.isStart();
    }

    @Override
    protected void addFrame(List<Frame> frames) {
        if (isFrameNotFinish()) {
            return;
        }

        createNextFrame(frames);
    }

    @Override
    protected Score addStateScore(Score score) {
        Score addedScore = state.addScore(score);

        return nextFrame.addStateScore(addedScore);
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

    private static class StartingFrame extends Frame {

        private State state;

        private StartingFrame(State state) {
            this.state = state;
        }

        public static StartingFrame of() {
            return new StartingFrame(Start.of());
        }

        @Override
        public Score getScore() {
            return Score.NULL;
        }

        @Override
        public boolean isStartState() {
            return true;
        }

        @Override
        protected Score addStateScore(Score score) {
            return score;
        }


        @Override
        protected void hitPins(Pins pins) {
            this.state = state.hitPins(pins);
        }

        @Override
        protected StateData getFrameStates() {
            return StateData.of(state.getState());
        }

    }

}
