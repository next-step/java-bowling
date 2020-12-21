package bowling.domain;

public class Spare implements State {
    public static final String DELIMITER = "|";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score getScore() {
        Score score = new Score(firstPins);
        score.setSecond(secondPins);
        return score;
    }

    @Override
    public String toString() {
        return this.firstPins.get() + DELIMITER + Symbol.SPARE.getSymbol();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
