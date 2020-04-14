package bowling.domain.state;

public class Spare implements State {
    public Spare(int felledPin, int newFelledPin) {
    }

    @Override
    public State play(int felledPin) {
        return null;
    }
}
