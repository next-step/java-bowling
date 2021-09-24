package bowling.domain;

import java.util.Arrays;

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

    public static boolean isSpare(final Score first, final Score second) {
        return first.plus(second) == TEN.getNumberOfPins();
    }

    public static boolean isStrike(final Score score) {
        return Score.TEN.equals(score);
    }

    public Integer getNumberOfPins() {
        return numberOfPins;
    }

    public int plus(final Score score) {
        return this.numberOfPins + score.numberOfPins;
    }

    public static Score valueOf(final int pins) {
        return Arrays.stream(Score.values())
                .filter(score -> score.getNumberOfPins().equals(pins))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 투구 점수 입니다."));
    }

    @Override
    public String toString() {
        return "Score{" +
                "numberOfPins=" + numberOfPins +
                '}';
    }
}
