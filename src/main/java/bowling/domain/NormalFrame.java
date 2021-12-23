package bowling.domain;

public class NormalFrame extends AbstractFrame {
    private final FrameRoundNumber roundNumber;

    private NormalFrame(int number) {
        this(new FrameRoundNumber(number));
    }

    private NormalFrame(FrameRoundNumber roundNumber) {
        super(new NormalKnockedPinCounts());
        this.roundNumber = roundNumber;
    }

    public static Frame ofFirst() {
        return new NormalFrame(1);
    }

    public static Frame ofNext(Frame frame) {
        return frame.next();
    }

    public static Frame ofFinal() {
        return new FinalFrame();
    }

    @Override
    public Frame next() {
        return new NormalFrame(roundNumber.next());
    }

    @Override
    public boolean isBeforeFinalFrame() {
        return roundNumber.equals(FrameRoundNumber.BEFORE_FINAL_FRAME_NUMBER);
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }
}
