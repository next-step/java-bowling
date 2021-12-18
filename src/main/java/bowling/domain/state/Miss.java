package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Miss extends Finished {
    private static final String NO_MISS_EXCEPTION_MESSAGE = "쓰러진 핀의 갯수가 10개면 Spare 처리가 되어야 합니다.";

    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        validPins(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void validPins(Pin firstPin, Pin secondPin) {
        if (firstPin.add(secondPin).isMaxCount()) {
            throw new IllegalArgumentException(NO_MISS_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public Score score() {
        return Score.miss(firstPin, secondPin);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = beforeScore.addScoreByPin(firstPin);
        if (score.canCalculateScore()) {
            return score;
        }

        return score.addScoreByPin(secondPin);
    }

    @Override
    public List<Pin> pins() {
        return Arrays.asList(firstPin, secondPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Miss miss = (Miss) o;
        return Objects.equals(firstPin, miss.firstPin) && Objects.equals(secondPin, miss.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
