package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

public abstract class Frame {
    protected Score score1;
    protected Score score2;

    public Frame next(int number) {
        return setScore(number);
    }

    protected abstract Frame setScore(int number);

    protected boolean isFirstTurn() {
        return ObjectUtils.isEmpty(this.score1);
    }

    protected abstract boolean isSecondTurn();

    public abstract boolean isFinish();

    public Score getScore1() {
        return score1;
    }

    public Score getScore2() {
        return score2;
    }

    public abstract Score getScore3();

    public ScoreType getScoreType1() {
        return score1.getScoreType();
    }

    public ScoreType getScoreType2() {
        return score2.getScoreType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(score1, frame.score1) &&
                Objects.equals(score2, frame.score2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score1, score2);
    }
}
