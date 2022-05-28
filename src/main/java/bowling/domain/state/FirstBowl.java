package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

import java.util.Objects;

public class FirstBowl extends Running {
    private static final String CANNOT_SUM_BEFORE_SCORE = "공을 한 번만 굴렸기 때문에 이전 점수와 더할 수 없습니다.";
    private static final String SCORE_FORMAT = "   %s  |";

    private final Pins firstPins;

    public FirstBowl(Pins pins) {
        this.firstPins = pins;
    }

    @Override
    public State bowl(int fallPins) {
        Pins secondPins = new Pins(fallPins);
        if (firstPins.isSpare(secondPins)) {
            return new Spare(firstPins, secondPins);
        }
        return new Miss(firstPins, secondPins);
    }

    @Override
    public Score sumBeforeScore(Score beforeScore) {
        beforeScore.bowl(firstPins);
        if (beforeScore.canCalculate()) {
            return beforeScore;
        }
        throw new IllegalArgumentException(CANNOT_SUM_BEFORE_SCORE);
    }

    @Override
    public boolean canCalculate(Score beforeScore) {
        beforeScore.bowl(firstPins);
        return beforeScore.canCalculate();
    }

    @Override
    public String mark() {
        return String.format(SCORE_FORMAT, firstPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(firstPins, firstBowl.firstPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins);
    }
}
