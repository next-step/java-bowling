package step4.domain;

import step4.domain.state.Ready;
import step4.domain.state.State;
import step4.exception.NeedAdditionalFrameException;

public class NormalFrame extends ProtoTypeFrame {
    private State state;
    private Frame nextFrame;

    public NormalFrame(int round) {
        super(round);
        this.state = new Ready();
    }

    public Frame createFrame(int round) {
        if (round < 10) {
            nextFrame = new NormalFrame(round);
            return nextFrame;

        }
        nextFrame = new LastFrame(10);
        return nextFrame;
    }

    public void throwBowl(int falledPins, Frames frames) {
        state = state.throwBowl(falledPins);
        if (isFinish()) {
            nextFrame = createFrame(round() + 1);
            frames.add(nextFrame);
        }
    }

    public State state() {
        return state;
    }

    public Score getScore() {
        Score score = state.score();

        if (score.canCalculateScore()) {
            return score;
        }

        if (nextFrame == null) {
            throw new NeedAdditionalFrameException();
        }

        return nextFrame.calculateScoreFromNextFrame(score);
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }


    @Override
    public String getSymbol() {
        return state.getSymbol();
    }


    public Score calculateScoreFromNextFrame(Score beforeScore) {
         Score score = state.calculateScore(beforeScore);
         if (score.canCalculateScore()) {
             return score;
         }

        if (nextFrame == null) {
            throw new NeedAdditionalFrameException();
        }

        return nextFrame.calculateScoreFromNextFrame(score);
    }

    public boolean isGameEnd() {
        return false;
    }
}
