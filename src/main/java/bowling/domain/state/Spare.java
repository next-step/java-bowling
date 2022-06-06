package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Spare extends EndState {
    private static final String SYMBOL_FORMAT = "%s|%s";
    private static final String GUTTER_SYMBOL = "-";
    private static final String SPARE_SYMBOL = "/";

    private final Pins firstPins;

    public Spare(Pins firstPins) {
        validatePins(firstPins);
        this.firstPins = firstPins;
    }

    private void validatePins(Pins firstPins) {
        if (firstPins.isStrike()) {
            throw new IllegalArgumentException(String.format("Spare 상태는 첫번쨰 투구로 쓰러트린 핀이 10개 일 수 없습니다. 전달 받은 쓰러뜨린 Pins 갯수 %s", firstPins));
        }
    }

    @Override
    public String symbol() {
        return String.format(SYMBOL_FORMAT, gutterOrValue(firstPins), SPARE_SYMBOL);
    }

    private String gutterOrValue(Pins pins) {
        if (pins.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(pins.hitPins());
    }

    @Override
    public Score score() {
        return Score.spare();
    }

    @Override
    public Score calculateAdditionalScore(Score previousScore) {
        if (previousScore.isNoBonusChance()) {
            return previousScore;
        }

        Score firstAdditionalScore = previousScore.addScore(firstPins.hitPins());
        if (firstAdditionalScore.isNoBonusChance()) {
            return firstAdditionalScore;
        }

        return firstAdditionalScore.addScore(firstPins.toComplementScore());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spare spare = (Spare) o;

        return firstPins.equals(spare.firstPins);
    }

    @Override
    public int hashCode() {
        return firstPins.hashCode();
    }
}