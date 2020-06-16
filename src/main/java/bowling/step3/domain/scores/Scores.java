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

    abstract public Scores nextInit(Score score);

    public boolean isType(ScoreType scoreType) {
        if (scoreType.equals(ScoreType.STRIKE)) {
            return firstScore == Score.getStrike();
        }
        if (scoreType.equals(ScoreType.SPARED)) {
            return firstScore.sum(secondScore) == Score.getStrike();
        }
        return false;
    }

    public boolean isFullOf() {
        return firstScore != null && secondScore != null;
    }

    public int totalScore() {
        return firstScore.sum(secondScore).getValue();
    }

    public Stream<Score> stream() {
        return Stream.of(firstScore, secondScore);
    }

}