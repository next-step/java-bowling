package bowling.domain.score;

import bowling.domain.frame.Pitch;

import static bowling.domain.score.ScoreState.*;

public class Score {
    private ScoreState state;
    private int leftCount = 2;
    private int score;

    private Score(Pitch pitch) {
        if (pitch.getPitch() == 10) {
            this.leftCount = 0;
            this.state = ScoreState.STRIKE;
            this.score = 10;
            return;
        }

        this.score = pitch.getPitch();
        this.leftCount -= 1;
        this.state = ScoreState.PROGRESS;
    }

    private Score(Pitch pitch, int leftCount) {
        this.score = pitch.getPitch();
        this.leftCount = leftCount;
        this.state = PROGRESS;
    }

    public static Score from(Pitch pitch) {
        return new Score(pitch);
    }

    public static Score lastScore(Pitch pitch) {
        return new Score(pitch, 1);
    }

    public void addScore(Pitch pitch) {
        this.score += pitch.getPitch();
        this.leftCount -= 1;
        if (this.score == 10) {
            this.state = SPARE;
            return;
        }
        this.state = DONE;
    }

    public boolean isDone() {
        return state == DONE;
    }

    public boolean isStrike() {
        return state == STRIKE;
    }

    public boolean isSpare() {
        return state == SPARE;
    }

    public boolean isProgress() {
        return state == PROGRESS;
    }

    public void addSpareBonusScore(int bonusScore) {
        this.score += bonusScore;
        state = DONE;
    }

    public void addStrikeBonusScore(Score bonusScore) {
        this.score += bonusScore.score;
        this.state = DONE;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && leftCount == score1.leftCount && state == score1.state;
    }

}
