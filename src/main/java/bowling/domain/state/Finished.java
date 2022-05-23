package bowling.domain.state;

public abstract class Finished implements State {
    @Override
    public State bowl(int countOfPins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return false;
    }
}
