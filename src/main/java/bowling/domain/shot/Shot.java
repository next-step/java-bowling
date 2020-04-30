package bowling.domain.shot;

import bowling.domain.shot.type.ShotType;

import java.util.Objects;

public class Shot {
    private final Score score;
    private final ShotType shotType;

    protected Shot(Score score, ShotType shotType) {
        this.score = score;
        this.shotType = shotType;
    }

    public static Shot init(int shotScore) {
        Score score = Score.of(shotScore);
        return new Shot(score, ShotType.of(score));
    }

    public Shot next(int next) {
        if (!shotType.isFinished()) {
            Score nextScore = Score.of(next);
            return new Shot(nextScore, ShotType.of(score, nextScore));
        }

        return init(next);
    }

    public boolean isClear() {
        return shotType.isCleared();
    }

    public ShotType scoreType() {
        return shotType;
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
                shotType.equals(shot.shotType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, shotType);
    }

    @Override
    public String toString() {
        return "ShotScore{" +
                "score=" + score +
                ", scoreType=" + shotType +
                '}';
    }
}
