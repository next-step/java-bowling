package bowling.domian.state;

public class Ready implements State {
    public State bowl(int i) {
        return new Ready();
    }

    public boolean isBowlPossible() {
        return false;
    }
}
