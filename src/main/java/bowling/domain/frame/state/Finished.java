package bowling.domain.frame.state;

abstract class Finished implements State {
    @Override
    public final State bowl(int pins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean isFinish() {
        return true;
    }
}
