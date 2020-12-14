package bowling.frame.state;

import bowling.global.exception.OutOfScoreRangeException;
import bowling.score.Pin;
import bowling.score.Score;

import java.util.Arrays;
import java.util.List;

import static bowling.global.utils.ExceptionMessage.INVALID_SCORE_RANGE;
import static bowling.score.Pin.PINS_MAX_VALUE;

public class Miss extends Done {

    private final Pin previousPins;
    private final Pin currentPins;

    private Miss(Pin previousPins, Pin currentPins) {
        this.previousPins = previousPins;
        this.currentPins = currentPins;
        validateOutOfScoreRange(previousPins.getFellPins(), currentPins.getFellPins());
    }

    public static Miss of(Pin previousPins, Pin currentPins) {
        return new Miss(previousPins, currentPins);
    }

    @Override
    public State bowl(Pin fellPins) {
        throw new UnsupportedOperationException();
    }

    private void validateOutOfScoreRange(int previousPins, int currentPins) {
        int totalScore = previousPins + currentPins;
        if (totalScore > PINS_MAX_VALUE) {
            throw new OutOfScoreRangeException(INVALID_SCORE_RANGE);
        }
    }

    @Override
    public List<String> getBowlResults() {
        return Arrays.asList(previousPins.toString(), currentPins.toString());
    }

    @Override
    public Score getScore() {
        int resultScore = previousPins.getFellPins() + currentPins.getFellPins();
        return Score.ofMiss(resultScore);
    }

    @Override
    public Score calculateScore(Score previousScore) {
        previousScore = Score.of(previousPins.getFellPins()).calculate(previousScore);
        if (previousScore.isCalculateScore()) {
            return previousScore;
        }
        return Score.of(currentPins.getFellPins()).calculate(previousScore);
    }

}
