package bowling.domain.frame;

public final class NormalFrame extends Frame {

    public NormalFrame(RoundNumber roundNumber, FrameScore frameScore) {
        super(roundNumber, frameScore);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(RoundNumber.firstRoundNumber(), new FrameScore());
    }

    @Override
    public Frame createNextFrame() {
        return new NormalFrame(roundNumber().nextRoundNumber(), new FrameScore());
    }

    @Override
    public void knockDownPin(Pin pin) {

    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
