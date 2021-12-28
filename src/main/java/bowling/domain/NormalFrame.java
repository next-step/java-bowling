package bowling.domain;

import bowling.annotations.ForUI;
import bowling.domain.state.State;

public class NormalFrame extends AbstractFrame {
    private static final int FIRST = 1;

    private Frame next;

    private NormalFrame(int number) {
        this(new FrameRoundNumber(number));
    }

    private NormalFrame(FrameRoundNumber roundNumber) {
        super(roundNumber);
    }

    public static Frame of(int number) {
        return new NormalFrame(number);
    }

    public static Frame ofFirst() {
        return new NormalFrame(FIRST);
    }

    public static Frame ofFinal() {
        return new FinalFrame();
    }

    @Override
    public Frame addNextFrame() {
        if (isNinthFrame()) {
            next = NormalFrame.ofFinal();
            return next;
        }

        next = new NormalFrame(roundNumber.next());
        return next;
    }

    @Override
    public boolean isNinthFrame() {
        return roundNumber.equals(FrameRoundNumber.NINTH_FRAME_NUMBER);
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public Frame next() {
        return next;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Score getScore() {
        Score score = state.makeScore();

        if (score.canCalculateScore() || next == null) {
            return score;
        }

        return next.additionalCalculate(score);
    }

    @Override
    public Score additionalCalculate(Score beforeScore) {
        Score score = state.additionalCalculate(beforeScore);
        if (score.canCalculateScore() || next == null) {
            return score;
        }

        return next.additionalCalculate(score);
    }
}
