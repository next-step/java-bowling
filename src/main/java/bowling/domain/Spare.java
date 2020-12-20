package bowling.domain;

public class Spare implements State {

    public static final int PINS_SPARE = 10;

    @Override
    public Score getScore() {
        return new Score(new Pins(PINS_SPARE));
    }

    @Override
    public String toString() {
        return "10";
    }
}
