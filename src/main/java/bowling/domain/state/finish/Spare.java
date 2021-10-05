package bowling.domain.state.finish;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.exception.state.SpareStateFirstPinStrikeException;
import bowling.exception.state.SpareStatePinsException;

public class Spare extends Finish {

    private final Pin first;
    private final Pin second;

    public Spare(Pin first, Pin second) {
        checkFirstPinStrike(first);
        checkPinsSpare(first, second);

        this.first = first;
        this.second = second;
    }

    private static void checkFirstPinStrike(Pin first) {
        if (first.isStrike()) {
            throw new SpareStateFirstPinStrikeException();
        }
    }

    private static void checkPinsSpare(Pin first, Pin second) {
        if (!first.isSpare(second)) {
            throw new SpareStatePinsException();
        }
    }

    @Override
    public Score createScore() {
        return Score.spare();
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = score.addPin(first);
        if (score.canCalculateScore()) {
            return score;
        }
        return score.addPin(second);
    }

}
