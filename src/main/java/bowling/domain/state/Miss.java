package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

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
    public Score getScore() {

        return new Score(firstPin.getCount() + secondPin.getCount(), 0);
    }

    @Override
    public Score calculateAdditionalScore(Score scoreV2) {
        Score score = scoreV2.add(firstPin.getCount());

        if (score.canCalculateScore()) {
            return score;
        }

        return score.add(secondPin.getCount());
    }

    @Override
    public List<Pin> pins() {
        return List.of(firstPin, secondPin);
    }
}
