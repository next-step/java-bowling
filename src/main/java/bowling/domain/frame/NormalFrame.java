package bowling.domain.frame;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.end.End;
import bowling.domain.state.progress.Start;
import bowling.strategy.PitchNumberStrategy;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final FrameInfo frameInfo;
    private Frame nextFrame;
    private State state;

    private NormalFrame() {
        this.frameInfo = FrameInfo.init();
        this.nextFrame = null;
        this.state = new Start();
    }

    private NormalFrame(FrameInfo frameInfo) {
        this.frameInfo = frameInfo;
        this.nextFrame = null;
        this.state = new Start();
    }

    public static Frame first() {
        return new NormalFrame();
    }

    @Override
    public void run(PitchNumberStrategy numberStrategy) {
        if (!isEnd()) {
            Pitch pitch = frameInfo.createPitch(numberStrategy);
            state = state.run(pitch);
            addPitch(pitch);
        }
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
    public int no() {
        return frameInfo.no();
    }

    @Override
    public int currentFallDownPinsCount() {
        return frameInfo.currentFallDownPinsCount();
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
    public boolean isEnd() {
        return state.isEnd();
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
        if (score.calculated()) {
            return score;
        }
        if (!isCalculateNextFrame()) {
            return null;
        }
        return nextFrame.calculateBonusScore(score);
    }

    @Override
    public String symbol() {
        return state.symbol();
    }

    private boolean isCalculateNextFrame() {
        return Objects.nonNull(nextFrame) && !(nextFrame.state() instanceof Start);
    }
}
