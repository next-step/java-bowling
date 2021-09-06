package bowling.domain;

public enum Score {

    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10);

    private final Integer numberOfPins;

    Score(final Integer numberOfPins) {
        this.numberOfPins = numberOfPins;
    }

    public Integer getNumberOfPins() {
        return numberOfPins;
    }

    public int plus(final Score score) {
        return this.numberOfPins + score.numberOfPins;
    }

    public boolean isStrike() {
        return this.equals(Score.TEN);
    }
}
