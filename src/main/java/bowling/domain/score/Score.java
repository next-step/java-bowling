package bowling.domain.score;

import bowling.exception.BowlingScoreException;

import java.util.Objects;

public class Score {
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 10;

    private ScoreType scoreType;
    private int score;

    public Score(Score before, int current){
        validate(before, current);

        this.scoreType = ScoreType.of(before.score, current);
        this.score = current;
    }

    public Score(int score){
        validate(score);

        this.scoreType = ScoreType.of(score);
        this.score = score;
    }

    private void validate(Score before, int current){
        if(before.getScoreType() == ScoreType.STRIKE || before.getScoreType() == ScoreType.SPARE){
            validate(current);
            return;
        }
        validate(before.getScore() + current);
    }

    private void validate(int score){
        if(score < MIN_SCORE || score > MAX_SCORE){
            throw new BowlingScoreException("최대 투구 점수는 10점입니다.");
        }
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                scoreType == score1.scoreType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreType, score);
    }
}
