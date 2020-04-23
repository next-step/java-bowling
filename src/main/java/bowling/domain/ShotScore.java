package bowling.domain;

import java.util.Objects;

public class ShotScore {
    private final Score score;
    private final ScoreType scoreType;
    private ShotScore next;

    protected ShotScore(Score score, ScoreType scoreType) {
        this.score = score;
        this.scoreType = scoreType;
    }

    static ShotScore init(int shotScore) {
        Score score = Score.of(shotScore);

        if (score.isMax()) {
            return new ShotScore(score, ScoreType.STRIKE);
        }

        if (score.isMin()) {
            return new ShotScore(score, ScoreType.GUTTER_FIRST);
        }

        return new ShotScore(score, ScoreType.MISS_FIRST);
    }

    ShotScore next(int next) {//TODO 리팩토링
        if (!scoreType.finish()) {
            Score nextScore = Score.of(next);
            if (score.isLeftPins(next)) {
                this.next = new ShotScore(nextScore, ScoreType.SPARE);
                return this.next;
            }
            if (nextScore.isMin()) {
                this.next = new ShotScore(nextScore, ScoreType.GUTTER_SECOND);
                return this.next;
            }

            this.next = new ShotScore(nextScore, ScoreType.MISS_SECOND);
            return this.next;
        }

        this.next = init(next);
        return this.next;
    }

    boolean isClear() {
        return scoreType.isCleared();
    }

    public ScoreType scoreType() {
        return scoreType;
    }

    public int singleScore() {
        return score.score();
    }

    int totalScore() {
        if (ScoreType.STRIKE.equals(scoreType)) {
            return getStrikeTotalScore();
        }

        if (ScoreType.SPARE.equals(scoreType)) {
            return getSpareTotalScore();
        }

        return singleScore();
    }

    boolean isScoreCalculated() {
        if (ScoreType.STRIKE.equals(scoreType)) {
            return next != null && next.next != null;
        }

        if (ScoreType.SPARE.equals(scoreType)) {
            return next != null;
        }

        return true;
    }

    private Integer getSpareTotalScore() {
        return next.singleScore() + singleScore();
    }

    private int getStrikeTotalScore() {
        return next.next.singleScore() + next.singleScore() + singleScore();
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
