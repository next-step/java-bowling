package bowling.model.state;

public abstract class Finished implements State {
    @Override
    public State bowl(int knockedDownPin) {
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
