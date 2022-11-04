package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.ScoreV2;

public class Miss extends State {

    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public State bowl(Pin pin) {
        throw new UnsupportedOperationException("더 이상 공을 던질 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public ScoreV2 getScore() {

        return new ScoreV2(firstPin.getCount() + secondPin.getCount(), 0);
    }

    @Override
    public ScoreV2 calculateAdditionalScore(ScoreV2 scoreV2) {
        ScoreV2 score = scoreV2.add(firstPin.getCount());

        if (score.canCalculateScore()) {
            return score;
        }

        return score.add(secondPin.getCount());
    }

    public String describe() {
        return firstPin.getCount() + "|" + secondPin.getCount();
    }
}
