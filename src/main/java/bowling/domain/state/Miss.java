package bowling.domain.state;

public class Miss implements State {
    public Miss(int felledPin, int newFelledPin) {

    }

    @Override
    public State play(int felledPin) {
        return null;
    }
}
