package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.exception.BowlingFrameException;
import org.springframework.util.ObjectUtils;

public class FinalFrame extends Frame {
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
        if (isThirdTurn()) {
            this.score3 = new Score(this.score2, number);
            return this;
        }
        throw new BowlingFrameException("투구를 모두 마쳤습니다.");
    }

    @Override
    public boolean isFinish() {
        return !isFirstTurn() && !isSecondTurn() && !isThirdTurn();
    }

    @Override
    protected boolean isSecondTurn() {
        return ObjectUtils.isEmpty(this.score2);
    }

    private boolean isThirdTurn() {
        if (isFirstTurn() || isSecondTurn()) {
            return false;
        }
        if (!ObjectUtils.isEmpty(this.score3)) {
            return false;
        }
        return this.score2.getScoreType().equals(ScoreType.SPARE) ||
                this.score1.getScoreType().equals(ScoreType.STRIKE) ||
                this.score2.getScoreType().equals(ScoreType.STRIKE);
    }
}
