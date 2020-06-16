package bowling.step3.domain.scores;

import bowling.step3.domain.Score;
import bowling.step3.domain.ScoreType;

import java.util.stream.Stream;

public abstract class Scores {
    protected final Score firstScore;
    protected final Score secondScore;

    protected Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public boolean isType(ScoreType scoreType) {
        if (scoreType.equals(ScoreType.STRIKE)) {
            return firstScore == Score.getStrike();
        }
        if (scoreType.equals(ScoreType.SPARED)) {
            return secondScore != null &&
                firstScore.sum(secondScore) == Score.getStrike();
        }
        return false;
    }

    public int totalScore() {
        return firstScore.sum(secondScore).getValue();
    }

    public boolean isFull() {
        return isType(ScoreType.STRIKE) || (firstScore != null && secondScore != null);
    }

    public boolean isEmpty() {
        return firstScore == null && secondScore == null;
    }

    abstract public Scores nextInit(Score score);

    abstract public Stream<Score> stream();

}