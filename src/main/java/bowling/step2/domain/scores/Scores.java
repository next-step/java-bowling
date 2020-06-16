package bowling.step2.domain.scores;

import bowling.step2.domain.Score;
import bowling.step2.domain.ScoreType;

import java.util.stream.Stream;

public abstract class Scores {
    protected final Score firstScore;
    protected final Score secondScore;

    protected Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    abstract public Scores nextInit(Score score);

    abstract public boolean isFullOf();

    abstract public Stream<Score> stream();

    public boolean isType(ScoreType scoreType) {
        if (scoreType.equals(ScoreType.STRIKE)) {
            return firstScore == Score.getStrike();
        }
        if (scoreType.equals(ScoreType.SPARED)) {
            return firstScore.sum(secondScore) == Score.getStrike();
        }
        return false;
    }

    public int totalScore() {
        return firstScore.sum(secondScore).getValue();
    }

}