package bowling.domain;

public class NormalFrame extends AbstractFrame {
    private final FrameRoundNumber roundNumber;
    private Frame next;

    public NormalFrame(int number) {
        this(new FrameRoundNumber(number));
    }

    private NormalFrame(FrameRoundNumber roundNumber) {
        super(new NormalKnockedPinCounts());
        this.roundNumber = roundNumber;
    }

    public static Frame ofFirst() {
        return new NormalFrame(1);
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
    public Frame next() {
        return next;
    }
}
