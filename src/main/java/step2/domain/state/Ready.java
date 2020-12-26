package step2.domain.state;

import step2.domain.Pins;
import step2.domain.Score;

public class Ready extends Running {

    @Override
    public State bowl(int fallingPins) {
        Pins pins = Pins.from(fallingPins);
        if (pins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pins);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        throw new IllegalArgumentException("추가 점수를 구할 수 없습니다.");
    }

    @Override
    public String toString() {
        return "";
    }
}
