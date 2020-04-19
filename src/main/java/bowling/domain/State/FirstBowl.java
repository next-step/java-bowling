package bowling.domain.State;

import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

public class FirstBowl extends Running {
    private final Pins firstPins;

    public FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public State bowl(int felledPins) {
        Pins secondPins = Pins.bowl(felledPins);
        if (firstPins.isSpare(secondPins)) {
            return new Spare(firstPins, secondPins);
        }
        return new Miss(firstPins, secondPins);
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc();
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = firstPins.sumScore(score);
        if (score.canCalculateScore()) {
            return score;
        }
        throw new CannotCalculateException("계산 할 수 있는 상태가 아닙니다");
    }
}
