package bowling.domain.frame;

import bowling.domain.Pitch;
import bowling.domain.Score;
import bowling.domain.state.Start;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame extends TemplateFrame {
    private Frame nextFrame;

    private NormalFrame() {
        super();
        this.nextFrame = null;
    }

    private NormalFrame(FrameInfo frameInfo) {
        super(frameInfo, new Start());
        this.nextFrame = null;
    }

    public static Frame first() {
        return new NormalFrame();
    }

    @Override
    public Frame next() {
        if (frameInfo.nextLast()) {
            nextFrame = FinalFrame.create(frameInfo.next());
            return nextFrame;
        }
        nextFrame = new NormalFrame(frameInfo.next());
        return nextFrame;
    }

    @Override
    public void addPitch(Pitch pitch) {
        frameInfo.addPitch(pitch);
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public boolean isThirdPitch() {
        return false;
    }

    @Override
    public Score score() {
        Score score = state.score();
        if (score.calculated()) {
            return score;
        }
        if (!isCalculateNextFrame()) {
            return null;
        }
        return nextFrame.calculateBonusScore(score);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        if (beforeScore.calculated()) {
            return beforeScore;
        }
        Score score = state.calculateBonusScore(beforeScore);
        if (!isCalculateNextFrame()) {
            return null;
        }
        return nextFrame.calculateBonusScore(score);
    }

    @Override
    public void addState(State state) {
        throw new UnsupportedOperationException();
    }

    private boolean isCalculateNextFrame() {
        return Objects.nonNull(nextFrame) && nextFrame.isEnd();
    }
}
