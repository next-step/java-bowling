package bowling.domain;

import bowling.domain.state.NotBowled;
import bowling.domain.state.State;

import static bowling.domain.Frames.FIRST_FRAME_NO;
import static bowling.domain.Frames.LAST_FRAME_NO;

public class NormalFrame implements Frame {
    private final int frameNo;
    private State state;
    private Frame next;

    private NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.state = NotBowled.init();
        this.next = next();
    }

    public static NormalFrame init() {
        return new NormalFrame(FIRST_FRAME_NO);
    }

    public static NormalFrame of(int frameNo) { return new NormalFrame(frameNo); }

    public Frame bowl(int pins) {
        if(state.isOver()) {
            return next.bowl(pins);
        }

        state = state.bowl(pins);
        return this;
    }

    public Score getScore() {
        Score score = state.calculateScore();

        if(score.hasNoTryLeft()) {
            return score;
        }

        return next.addBonusScore(score);
    }

    @Override
    public Score addBonusScore(Score prevScore) {
        Score score = state.addBonusScore(prevScore);
        if(score.hasNoTryLeft()) {
            return score;
        }

        return next.addBonusScore(score);
    }

    @Override
    public boolean isOver() {
        return state.isOver();
    }

    @Override
    public Frame getNext() {
        return next;
    }

    private Frame next() {
        if(frameNo == LAST_FRAME_NO - 1) {
            return new FinalFrame();
        }

        return new NormalFrame(frameNo + 1);
    }

    @Override
    public String display() {
        return state.display();
    }
}
