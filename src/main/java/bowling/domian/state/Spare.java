package bowling.domian.state;

public class Spare implements State {
    @Override
    public State bowl(int falledPinsCount) {
        return null;
    }

    public boolean isFinished() {
        return false;
    }
}
