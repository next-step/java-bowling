package bowling.step3.domain.scores;

import bowling.step3.domain.Score;

import java.util.stream.Stream;

public class FinalScores extends Scores {
    private final Score bonusScore;

    private FinalScores(Score firstScore, Score secondScore, Score bonusScore) {
        super(firstScore, secondScore);
        this.bonusScore = bonusScore;
    }

    public static FinalScores of(Score firstScore, Score secondScore) {
        return new FinalScores(firstScore, secondScore, null);
    }

    public static FinalScores init() {
        return of(null, null);
    }

    public boolean filledBonus() {
        return bonusScore != null;
    }

    @Override
    public Scores nextInit(Score score) {
        if (firstScore == null) {
            return of(score, null);
        }
        if (secondScore == null) {
            return of(firstScore, score);
        }
        return new FinalScores(firstScore, secondScore, score);
    }

    @Override
    public Stream<Score> stream() {
        return Stream.of(firstScore, secondScore, bonusScore);
    }
}