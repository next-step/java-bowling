package bowling.frame;

import bowling.Pins;
import bowling.exception.CannotScoreCalculateException;
import bowling.state.Throwing;
import bowling.state.running.Ready;

public class NormalFrame implements Frame {

    private final int frameNo;
    private Throwing state;
    private Frame nextFrame;

    private NormalFrame(int frameNo) {
        this.state = new Ready();
        this.frameNo = frameNo;
    }

    public static NormalFrame first() {
        return new NormalFrame(Frame.MIN_FRAME_NO);
    }


    @Override
    public Frame bowl(Pins fallenPins) {
        this.state = this.state.bowl(fallenPins);
        if (this.state.isEnd()) {
            this.nextFrame = createNextFrame();
            return this.nextFrame;
        }

        return this;
    }

    @Override
    public String symbol() {
        return state.symbol();
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public int calculateScore() {
        try {
            return getScore().getScore();
        } catch (CannotScoreCalculateException e) {
            return Score.UN_SCORE_STATE;
        }
    }

    private Score getScore() throws CannotScoreCalculateException {
        Score score = state.getScore();
        if (score.isCalculatorScore()) {
            return score;
        }
        return nextFrame.calculateAdditionalScore(score);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) throws CannotScoreCalculateException {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.isCalculatorScore()) {
            return score;
        }
        return nextFrame.calculateAdditionalScore(score);
    }

    private Frame createNextFrame() {
        if (isLastBeforeFrame()) {
            return LastFrame.first();
        }
        return new NormalFrame(this.frameNo + 1);
    }

    private boolean isLastBeforeFrame() {
        return this.frameNo == Frame.MAX_FRAME_NO - 1;
    }
}
