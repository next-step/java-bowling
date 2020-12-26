package step2.domain.state;

import step2.domain.Pins;
import step2.domain.Score;

public class FirstBowl extends Running {

    private final Pins firstPins;

    public FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public State bowl(int fallingPins) {
        Pins pins = Pins.from(fallingPins);
        if (firstPins.isSpare(pins)) {
            return new Spare(firstPins, pins);
        }
        return new Miss(firstPins, pins);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score.bowl(firstPins.getFallingPins());
        if (score.validateChance()) {
            System.out.println("validate score = " + score);
            return score;
        }
        throw new IllegalArgumentException("추가 점수를 구할 수 없습니다.");
    }
}
