package bowling.domain;

public class FinalFrame extends Frame{
    private Pins firstPitching;
    private Pins secondPitching;
    private Pins bonusPitching;
    private State state = State.BEFORE_FIRST_PITCHING;
    
    public FinalFrame(final int index) {
        super(index);
    }

    @Override
    public void pitch(final Pins pins) {
        if (state == State.BEFORE_FIRST_PITCHING) {
            this.firstPitching = pins;
        }
        if (state == State.BEFORE_SECOND_PITCHING) {
            this.secondPitching = pins;
        }
        if (isBonusPitchable()) {
            this.bonusPitching = pins;
        }
        this.state = State.nextState(firstPitching, secondPitching);
    }

    @Override
    public boolean isPitchable() {
        return state == State.BEFORE_FIRST_PITCHING
                || state == State.BEFORE_SECOND_PITCHING
                || isBonusPitchable();
    }
    
    private boolean isBonusPitchable() {
        return state == State.STRIKE || state == State.SPARE;
    }
}
