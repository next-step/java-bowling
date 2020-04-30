package bowling.domain.shot;

import bowling.domain.shot.type.ScoreType;

import java.util.Objects;

public class Shot {
    private final Score score;
    private final ScoreType scoreType;

    protected Shot(Score score, ScoreType scoreType) {
        this.score = score;
        this.scoreType = scoreType;
    }

    public static Shot init(int shotScore) {
        Score score = Score.of(shotScore);
        return new Shot(score, ScoreType.of(score));
    }

    public Shot next(int next) {
        if (!scoreType.isFinished()) {
            Score nextScore = Score.of(next);
            return new Shot(nextScore, ScoreType.of(score, nextScore));
        }

        return init(next);
    }

    public boolean isClear() {
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
        Shot shot = (Shot) o;
        return score.equals(shot.score) &&
                scoreType.equals(shot.scoreType);
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
