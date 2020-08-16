package bowling.domian.state;

import bowling.domian.frame.Score;
import bowling.domian.frame.exception.InvalidScoreCalculateException;

public class FirstBowl implements State {
    private static final String GET_SCORE_IMPOSSIBLE_ERROR_MESSAGE = "아직 프레임이 끝나지 않아 계산이 불가능합니다!";

    private final Pins firstPins;

    public FirstBowl(Pins pins) {
        this.firstPins = pins;
    }

    @Override
    public State bowl(int falledPinsCount) {
        Pins secondPins = firstPins.secondBowl(falledPinsCount);

        if (secondPins.isSpare(firstPins)) {
            return new Spare(firstPins, secondPins);
        }

        return new Miss(firstPins, secondPins);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean canGetScore() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new InvalidScoreCalculateException(GET_SCORE_IMPOSSIBLE_ERROR_MESSAGE);
    }

    @Override
    public Score calculateAdditional(Score lastScore) {
        lastScore = firstPins.addScore(lastScore);

        return lastScore;
    }
}
