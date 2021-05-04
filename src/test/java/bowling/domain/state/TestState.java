package bowling.domain.state;

public final class TestState implements State {

    @Override
    public State bowl(PinCount hitCount) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isAllPinClear() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int firstCount() {
        return 0;
    }

    @Override
    public int secondCount() {
        return 0;
    }
}
