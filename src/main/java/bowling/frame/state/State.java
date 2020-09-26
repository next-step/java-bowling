package bowling.frame.state;

public abstract class State {

    protected abstract State bowl(String falledPins);

    protected abstract boolean isFinish();

}
