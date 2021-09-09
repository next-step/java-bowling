package bowling.domain.score;

import java.util.Objects;

public class Score {
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 10;

    private ScoreType scoreType;
    private int score;
    private String display;

    public Score(Score before, int current){
        validate(before, current);

        this.scoreType = ScoreType.of(before.score, current);
        this.score = current;
        setDisplay();
    }

    public Score(int score){
        validate(score);

        this.scoreType = ScoreType.of(score);
        this.score = score;
        setDisplay();
    }

    private void setDisplay() {
        if (this.scoreType == ScoreType.MISS) {
            this.display = String.valueOf(this.score);
            return;
        }
        this.display = this.scoreType.getDisplay();
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
            throw new IllegalArgumentException("최대 투구 점수는 10점입니다.");
        }
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public int getScore() {
        return score;
    }

    public String getDisplay() {
        return display;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return this.score == score.score &&
                scoreType == score.scoreType &&
                Objects.equals(display, score.display);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreType, score, display);
    }
}
