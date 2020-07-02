package bowling.step2_1.domain.score;

import java.util.Objects;

public class Score {
    private static final String ONE_PITCH_EXCEPTION = "한번의 투구는 0보다 작거나 10보다 큰 값이 올 수 없습니다.";
    private static final int MIN_PITCH = 0;
    private static final int MAX_PITCH = 10;

    private int score;
    private ScoreType scoreType;

    private Score(int score, ScoreType scoreType) {
        validateScore(score);
        this.score = score;
        this.scoreType = scoreType;
    }

    public static Score of(int pitch, ScoreType scoreType) {
        return new Score(pitch, scoreType);
    }

    private void validateScore(int score) {
        if(score < MIN_PITCH || score > MAX_PITCH){
            throw new IllegalArgumentException(ONE_PITCH_EXCEPTION);
        }
    }

    public int getScore() {
        return score;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public String getDisplay() {
        return scoreType.convertToDisplay(this.score);
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
        return Objects.hash(score, scoreType);
    }
}
