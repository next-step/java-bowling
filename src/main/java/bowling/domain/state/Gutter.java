package bowling.domain.state;

public class Gutter extends Running {
    private static final String GUTTER_SYMBOL = "-";

    Gutter() {
        super(MIN_COUNT_OF_PINS, GUTTER_SYMBOL);
    }

    @Override
    public AbstractState bowl(int fallenPins) {
        return bowlSecond(fallenPins);
    }
}
