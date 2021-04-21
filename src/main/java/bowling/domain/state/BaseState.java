package bowling.domain.state;

public abstract class BaseState implements FrameState {

    @Override
    public FrameState bowl(int pin) {
        return new Ready();
    }

    @Override
    public String printResult() {
        return "";
    }

    @Override
    public String printScore(int pin) {
        return FrameState.super.printScore(pin);
    }
}
