package bowling.domain.frame;

public final class FinalFrame extends Frame {

    private FinalFrame(RoundNumber roundNumber, FrameScore frameScore) {
        super(roundNumber, frameScore);
    }

    public static FinalFrame from(FrameScore frameScore) {
        return new FinalFrame(new RoundNumber(RoundNumber.MAX), frameScore);
    }

    @Override
    public Frame createNextFrame() {
        return null;
    }

    @Override
    public void knockDownPin(Pin pin) {

    }

    @Override
    public RoundNumber roundNumber() {
        return super.roundNumber();
    }
}
