package bowling.domain.value;

import bowling.annotations.GetterForUI;
import bowling.domain.type.Score;
import bowling.utils.Preconditions;

import java.util.Objects;

public class Pins {
    private static final int GUTTER_COUNT = 0;
    public static final int STRIKE_OR_SPARE_COUNT = 10;

    private final int pins;
    private Score score;

    public Pins(int pins) {
        Preconditions.checkMinimumSize(pins, GUTTER_COUNT,
                                       String.format("쓰러트린 핀의 갯수는 %s 이상 이어야 합니다.", GUTTER_COUNT));
        Preconditions.checkMaximumSize(pins, STRIKE_OR_SPARE_COUNT,
                                       String.format("쓰러트린 핀의 갯수는 %s 이하 이어야 합니다.", STRIKE_OR_SPARE_COUNT));

        this.pins = pins;
    }

    public static Pins from(int pins) {
        return new Pins(pins);
    }

    public void addScore(Score score) {
        this.score = score;
    }

    public int getPins() {
        return pins;
    }

    public boolean isStrike() {
        return STRIKE_OR_SPARE_COUNT == pins;
    }

    @GetterForUI
    public String getMark() {
        if (score == Score.MISS) {
            return String.valueOf(pins);
        }

        return score.getMark();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return pins == pins1.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
