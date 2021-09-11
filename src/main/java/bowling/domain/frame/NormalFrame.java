package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import org.springframework.util.ObjectUtils;

public class NormalFrame extends Frame {
    @Override
    protected Frame setScore(int number) {
        if (isFirstTurn()) {
            this.score1 = new Score(number);
            return this;
        }
        if (isSecondTurn()) {
            this.score2 = new Score(this.score1, number);
            return this;
        }
        return new NormalFrame().next(number);
    }

    @Override
    public boolean isFinish() {
        return !isFirstTurn() && !isSecondTurn();
    }

    @Override
    protected boolean isSecondTurn() {
        if (isFirstTurn()) {
            return false;
        }
        if (this.score1.getScoreType().equals(ScoreType.STRIKE)) {
            return false;
        }
        return ObjectUtils.isEmpty(this.score2);
    }
}
