package step2.domain.state;

public abstract class Finished implements State {

    public State bowl(int fallingPins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
