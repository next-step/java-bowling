package bowling.step4.domain.scores;

import bowling.step4.domain.ScoreType;
import bowling.step4.domain.Score;

import java.util.stream.Stream;

public abstract class Scores {
    protected final Score firstScore;
    protected final Score secondScore;

    protected Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public int totalScore() {
        return firstScore.sum(secondScore).getValue();
    }

    abstract public Scores nextInit(Score score);

    abstract public Stream<Score> stream();

}