package bowling.domain.State;

abstract class Finished implements State {
    @Override
    public State bowl(int felledPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
