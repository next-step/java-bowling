package bowling.domain;

public class Miss implements State {
    public static final String DELIMITER = "|";

    private final Pins firstPins;
    private Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public Miss(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public Score getScore() {
        Score score = new Score(firstPins);
        score.setSecond(secondPins);
        return score;
    }

    @Override
    public String toString() {
        String ret = String.valueOf(this.firstPins.get());
        if (this.secondPins != null) {
            ret = ret + DELIMITER + (this.secondPins.get() == 0 ? Symbol.GUTTER.getSymbol() : this.secondPins.get());
        }
        return ret;
    }

    @Override
    public boolean isFinished() {
        return this.secondPins != null;
    }
}
