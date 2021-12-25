package bowling.domain;

import bowling.annotations.ForUI;
import bowling.domain.state.State;

public class NormalFrame extends AbstractFrame {
    private static final int FIRST = 1;

    private Frame next;

    public NormalFrame(int number) {
        this(new FrameRoundNumber(number));
    }

    private NormalFrame(FrameRoundNumber roundNumber) {
        super(roundNumber);
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
        return state.isFinished();
    }

    @Override
    public Frame next() {
        return next;
    }

    @ForUI
    @Override
    public State getState() {
        return state;
    }
}
