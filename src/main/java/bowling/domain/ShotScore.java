package bowling.domain;

import bowling.domain.scoreType.ScoreType;

import java.util.Objects;

public class ShotScore {
    protected final Score score;
    protected final ScoreType scoreType;
    protected ShotScore next;

    protected ShotScore(Score score, ScoreType scoreType) {
        this.score = score;
        this.scoreType = scoreType;
    }

    static ShotScore init(int shotScore) {
        Score score = Score.of(shotScore);
        return new ShotScore(score, ScoreType.of(score));
    }

    ShotScore next(int next) {
        if (!scoreType.isFinished()) {
            Score nextScore = Score.of(next);
            this.next = new ShotScore(nextScore, ScoreType.of(score, nextScore));
            return this.next;
        }

        this.next = init(next);
        return this.next;
    }

    boolean isClear() {
        return scoreType.isCleared();
    }

    int totalScore() {
        return calculateTotalScore(scoreType.getBonusCount());
    }

    boolean isScoreCalculated() {
        return isNextCalculated(scoreType.getBonusCount());
    }

    private boolean isNextCalculated(int bonusCount) {
        boolean calculated = true;
        ShotScore shotScore = this;
        for (int i = 0; (i < bonusCount) && calculated; i++) {
            calculated = shotScore.next != null;
            shotScore = shotScore.next;
        }
        return calculated;
    }

    private int calculateTotalScore(int bonusCount) {
        int score = singleScore();
        ShotScore shotScore = this;
        for (int i = 0; i < bonusCount; i++) {
            shotScore = shotScore.next;
            score += shotScore.singleScore();
        }
        return score;
    }

    public ScoreType scoreType() {
        return scoreType;
    }

    public int singleScore() {
        return score.score();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShotScore shotScore = (ShotScore) o;
        return score == shotScore.score &&
                scoreType == shotScore.scoreType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, scoreType);
    }

    @Override
    public String toString() {
        return "ShotScore{" +
                "score=" + score +
                ", scoreType=" + scoreType +
                '}';
    }
}
