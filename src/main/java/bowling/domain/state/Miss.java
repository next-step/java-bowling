package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public class Miss extends Finished {

    private static final int NO_CHANCE = 0;

    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(final Pin firstPin, final Pin secondPin) {

        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public State bowl(final Pin pin) {

        throw new IllegalArgumentException("더 이상 공을 던질 수 없습니다.");
    }

    @Override
    public Score getScore() {

        return new Score(firstPin.count() + secondPin.count(), NO_CHANCE);
    }

    @Override
    public Score calculateAdditionalScore(final Score score) {

        Score newScore = score.add(firstPin.count());

        if (newScore.canCalculateScore()) {
            return newScore;
        }

        return newScore.add(secondPin.count());
    }

    @Override
    public List<Pin> pins() {

        return List.of(firstPin, secondPin);
    }

    @Override
    public boolean hasPins(final int size) {

        return pins().size() == size;
    }
}
