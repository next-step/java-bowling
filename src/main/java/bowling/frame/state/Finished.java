package bowling.frame.state;

abstract class Finished implements State {
    @Override
    public State bowl(int falledPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
