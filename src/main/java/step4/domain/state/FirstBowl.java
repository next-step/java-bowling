package step4.domain.state;

import step4.domain.PinCount;
import step4.domain.Score;
import step4.domain.Symbol;

public class FirstBowl extends Running {
    private final PinCount firstPinCount;

    public FirstBowl(PinCount knockedDownPinCount) {
        this.firstPinCount = knockedDownPinCount;
    }

    @Override
    public State bowl(int pinCount) {
        PinCount secondPinCount = new PinCount(pinCount);

        if (firstPinCount.isSpare(secondPinCount)) {
            return new Spare(firstPinCount, secondPinCount);
        }

        return new Miss(firstPinCount, secondPinCount);
    }

    @Override
    public Score addScore(Score score) {
        score = score.add(firstPinCount.value());

        if (!score.isOpportunityLeft()) {
            return score;
        }

        return Score.unCountableScore();
    }

    @Override
    public String marks() {
        return Symbol.of(this, firstPinCount, true);
    }
}
