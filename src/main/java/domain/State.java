package domain;

public interface State {

    State update(Pins fallenPins);

    boolean isClosed();
}
