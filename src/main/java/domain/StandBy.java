package domain;

public class StandBy implements State {

    @Override
    public State update(Pins fallenPins) {
        return null;
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
