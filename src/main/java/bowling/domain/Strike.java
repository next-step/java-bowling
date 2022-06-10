package bowling.domain;

public class Strike extends State {
    private static final String STRIKE_SYMBOL = "X";

    Strike() {
        super(MAX_COUNT_OF_PINS, STRIKE_SYMBOL);
    }

    @Override
    public State bowl(int firstBowl) {
        return null;
    }
}
