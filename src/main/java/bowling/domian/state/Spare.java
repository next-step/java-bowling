package bowling.domian.state;

import bowling.domian.frame.Score;
import bowling.domian.state.exception.BowlFinishedException;

public class Spare implements State {
    private static final String BOWL_FINISHED_EXCEPTION_MSG = "Spare 후에는 투구가 불가능 합니다!";

    private Pins firstPins;
    private Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        this.firstPins= firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State bowl(int falledPinsCount) {
        throw new BowlFinishedException(BOWL_FINISHED_EXCEPTION_MSG);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean canGetScore() {
        return true;
    }

    @Override
    public Score getScore() {
        return Score.spare();
    }

    @Override
    public Score calculateAdditional(Score score) {
        score = firstPins.addScore(score);

        if (!score.isCalculateDone()) {
            score = secondPins.addScore(score);
        }

        return score;
    }
}
