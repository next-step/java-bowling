package bowling.domain.state;

public class Spare implements State {
    public Spare(int falledPin, int newFalledPin) {
    }

    @Override
    public State play(int falledPin) {
        return null;
    }
}
