package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.frame.Score;

public abstract class State {
    private static final String CANNOT_ADD_SCORE_EXCEPTION_MESSAGE = "점수를 더 이상 합산할 수 없습니다.";

    protected Score addBonusScore(Score score, int bonusScore) {
        if (score.canCalculateScore()) {
            throw new IllegalStateException(CANNOT_ADD_SCORE_EXCEPTION_MESSAGE);
        }

        return score.add(bonusScore);
    }

    public abstract State bowl(int pins);

    public abstract Score getScore();

    public abstract Score calculateBonusScore(Score previousScore);

    public abstract boolean isFinish();

    public abstract boolean canBonusBowl();

    public abstract List<Pins> getFalledPins();

    public abstract boolean isStrike();

    public abstract boolean isSpare();
}
