package bowling.domain.shot;

import bowling.domain.shot.type.ScoreType;

import java.util.Objects;

public class ShotScore {
    private final Score score;
    private final ScoreType scoreType;

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
            return new ShotScore(nextScore, ScoreType.of(score, nextScore));
        }

        return init(next);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShotScore shotScore = (ShotScore) o;
        return score.equals(shotScore.score) &&
                scoreType.equals(shotScore.scoreType);
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
