package step4.domain;

import step4.domain.state.Ready;
import step4.domain.state.State;
import step4.exception.NeedAdditionalFrameException;

public class NormalFrame implements Frame {
    private int round;
    private State state;
    private Frame nextFrame;

    public NormalFrame(int round) {
        this.round = round;
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

    public void throwBowl(int falledPins) {
        state = state.throwBowl(falledPins);
    }

    public State state() {
        return state;
    }

    public Score getScore() {
        Score score = state.score();

        if (score.canCalculate()) {
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
    public int round() {
        return round;
    }

    @Override
    public Frame next() {
        if (nextFrame == null) {
            throw new NeedAdditionalFrameException();
        }
        return nextFrame;
    }

    @Override
    public String getSymbol() {
        return state.getSymbol();
    }


    public Score calculateScoreFromNextFrame(Score beforeScore) {
         Score score = state.calculateScore(beforeScore);
         if (score.canCalculate()) {
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
