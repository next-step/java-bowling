package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.Score;
import bowling.model.state.Ready;
import bowling.model.state.Spare;
import bowling.model.state.State;
import bowling.model.state.Strike;

public class FinalFrame extends AbstractFrame {

    public static final int MAX_TRY_NUMBER = 3;
    private int roundNumber = 0;

    public FinalFrame(int number) {
        super(number);
    }

    @Override
    public void bowl(Pin pin) {
        super.bowl(pin);
        roundNumber++;
        if (getCurrentState().isFinished()) {
            getStates().add(new Ready());
        }
    }

    @Override
    public boolean isFinished() {
        if (getFirstState() instanceof Strike || getFirstState() instanceof Spare) {
            return roundNumber == MAX_TRY_NUMBER;
        }

        return getFirstState().isFinished();
    }

    private State getFirstState() {
        return getStates().get(0);
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public Score getScore() {
        Score score = getFirstState().getScore();
        if (score.canCalculate()) {
            return score;
        }

        return addNextBonusScore(score);
    }

    @Override
    public Score addBonusScore(Score beforeScore) {
        Score score = getFirstState().addBonusScore(beforeScore);
        if (score.canCalculate()) {
            return score;
        }

        return addNextBonusScore(score);
    }

    private Score addNextBonusScore(Score beforeScore) {
        Score score = beforeScore;
        for (int i = 1; i < getStates().size(); i++) {
            score = getStates().get(i).addBonusScore(score);
            if (score.canCalculate()) {
                return score;
            }
        }
        return score;
    }
}
