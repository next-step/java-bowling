package bowling.domain.concrete.frame;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Frame;
import bowling.domain.engine.frame.Score;
import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.StateFactory;


public class NormalFrame implements Frame {

    private State state;
    private Frame nextFrame;

    private NormalFrame(State state) {
        this.state = state;
    }

    private NormalFrame(State state, Frame nextFrame) {
        this.state = state;
        this.nextFrame = nextFrame;
    }

    public static NormalFrame init() {
        return new NormalFrame(StateFactory.ready());
    }

    public static NormalFrame init(Frame nextFrame) {
        return new NormalFrame(StateFactory.ready(), nextFrame);
    }

    @Override
    public void roll(RollResult rollResult) {
        if (isEnded()) {
            throw new IllegalStateException("이미 프레임이 종료된 상태입니다.");
        }

        this.state = state.transit(rollResult);
    }

    @Override
    public Score getScore() {
        Score score = state.createScore();

        if (!score.isCalculationCompleted()) {
            score = nextFrame.addScoreTo(score);
        }

        return score;
    }

    @Override
    public Score addScoreTo(Score score) {
        score = state.addScoreTo(score);

        if (!score.isCalculationCompleted()) {
            score = nextFrame.addScoreTo(score);
        }

        return score;
    }

    @Override
    public boolean isEnded() {
        return state.isFinished();
    }

    @Override
    public String exportState() {
        return state.export();
    }
}
