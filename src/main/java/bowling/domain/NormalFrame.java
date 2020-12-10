package bowling.domain;

public class NormalFrame extends Frame {
    private State state = State.BEFORE_FIRST_PITCHING;

    public NormalFrame(final int index) {
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
        this.state = State.nextState(firstPitching, secondPitching);
    }

    @Override
    public boolean isPitchable() {
        return state.isPitchable();
    }
}