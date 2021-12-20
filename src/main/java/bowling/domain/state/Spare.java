package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Spare extends Finished {
    private static final String LESS_THAN_TEN_PIN_MESSAGE = "쓰러트린 핀의 갯수가 10개가 아닙니다.";

    private final Pin firstPin;
    private final Pin secondPin;

    public Spare(Pin firstPin, Pin secondPin) {
        if (!firstPin.add(secondPin).isMaxCount()) {
            throw new IllegalArgumentException(LESS_THAN_TEN_PIN_MESSAGE);
        }
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public Score score() {
        return Score.spare();
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
        Spare miss = (Spare) o;
        return Objects.equals(firstPin, miss.firstPin) && Objects.equals(secondPin, miss.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
