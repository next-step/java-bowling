package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Ready extends Running {
    private static final String CANNOT_CALCULATE_SCORE = "공을 굴리지 않았기 때문에 점수를 구할 수 없습니다.";
    private static final String CANNOT_SUM_BEFORE_SCORE = "공을 굴리지 않았기 때문에 이전 점수와 더할 수 없습니다.";

    @Override
    public State bowl(int fallPins) {
        Pins pins = new Pins(fallPins);
        if (pins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pins);
    }

    @Override
    public boolean canCalculate(Score score) {
        return false;
    }

    @Override
    public String mark() {
        return "      |";
    }

    @Override
    public Score score() {
        throw new IllegalArgumentException(CANNOT_CALCULATE_SCORE);
    }

    @Override
    public Score sumBeforeScore(Score beforeScore) {
        throw new IllegalArgumentException(CANNOT_SUM_BEFORE_SCORE);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ready;
    }
}
