package bowling.domain.frame;

public final class NormalFrame extends Frame {

    public NormalFrame(RoundNumber roundNumber, FrameScore frameScore) {
        super(roundNumber, frameScore);
    }

    @Override
    public Frame createNextFrame() {
        return null;
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
