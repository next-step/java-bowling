package bowling.step2.domain.scores;

import bowling.step2.domain.Score;

import java.util.List;
import java.util.stream.Stream;

public class Scores {
    protected final Score firstScore;
    protected final Score secondScore;

    protected Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public Scores nextInit(Score score) {
        return null;
    }

    public boolean isStrike() {
        return firstScore == Score.getStrike();
    }

    public boolean isSpared() {
        return firstScore.sum(secondScore) == Score.getStrike();
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