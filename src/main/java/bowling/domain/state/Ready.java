package bowling.domain.state;

public class Ready extends Running {

    @Override
    public AbstractState bowl(int fallenPins) {
        if (MAX_COUNT_OF_PINS == fallenPins) {
            return new Strike();
        }

        if (MIN_COUNT_OF_PINS == fallenPins) {
            return new Gutter();
        }

        return new FirstBowl(fallenPins);
    }
}
