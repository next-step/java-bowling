package bowling.frame.state;

import bowling.score.Pin;
import bowling.score.Score;

import java.util.Arrays;
import java.util.List;

import static bowling.global.utils.CommonConstant.MARK_SPARE;

public class Spare extends Done {

    private final Pin previousPins;
    private final Pin currentPins;

    private Spare(Pin previousPins, Pin currentPins) {
        this.previousPins = previousPins;
        this.currentPins = currentPins;
    }

    public static Spare of(Pin previousPins, Pin nextPins) {
        return new Spare(previousPins, nextPins);
    }

    @Override
    public State bowl(Pin fellPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getBowlResults() {
        return Arrays.asList(previousPins.toString(), MARK_SPARE);
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
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
