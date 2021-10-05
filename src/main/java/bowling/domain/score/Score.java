package bowling.domain.score;

import static bowling.domain.score.Pin.PIN_MAX_VALUE;

import bowling.exception.score.CannotCalculateException;
import bowling.exception.score.ScoreAddPinException;
import bowling.exception.state.MissStateCrerateException;
import java.util.Objects;

public class Score {

    private final int score;
    private final int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score from(int score, int left) {
        return new Score(score, left);
    }

    public static Score strike() {
        return new Score(PIN_MAX_VALUE, 2);
    }

    public static Score spare() {
        return new Score(PIN_MAX_VALUE, 1);
    }

    public static Score miss(int pins) {
        checkMissPinsSum(pins);

        return new Score(pins, 0);
    }

    private static void checkMissPinsSum(int pins) {
        if (pins >= PIN_MAX_VALUE) {
            throw new MissStateCrerateException();
        }
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

    public int score() {
        if (!canCalculateScore()) {
            throw new CannotCalculateException();
        }
        return this.score;
    }

    public Score addPin(Pin pin) {
        if (canCalculateScore()) {
            throw new ScoreAddPinException();
        }
        return new Score(score + pin.value(), left - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }

}
