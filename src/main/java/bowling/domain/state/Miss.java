package bowling.domain.state;

public class Miss implements State {
    public Miss(int falledPin, int newFalledPin) {

    }

    @Override
    public State play(int falledPin) {
        return null;
    }
}
