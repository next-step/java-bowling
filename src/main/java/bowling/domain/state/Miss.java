package bowling.domain.state;

public class Miss implements State {

    public Miss(int countOfPin, int countOfPin1) {

    }

    @Override
    public State bowl(int countOfPin) {
        return null;
    }

    @Override
    public boolean isDone() {
        return true;
    }
}
