package bowling.domain;

public class Strike implements State {

    public static final int PINS_STRIKE = 10;

    @Override
    public Score getScore() {
        return new Score(new Pins(PINS_STRIKE));
    }

    @Override
    public String toString() {
        return Symbol.STRIKE.getSymbol();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
