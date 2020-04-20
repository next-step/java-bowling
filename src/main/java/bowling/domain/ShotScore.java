package bowling.domain;

import java.util.Objects;
import java.util.Optional;

public class ShotScore {
    private final Score score;
    private final ScoreType scoreType;
    private ShotScore next;

    private ShotScore(Score score, ScoreType scoreType) {
        this.score = score;
        this.scoreType = scoreType;
    }

    static ShotScore init(int shotScore) {
        Score score = Score.of(shotScore);

        if (score.isMax()) {
            return new ShotScore(score, ScoreType.STRIKE);
        }

        if (score.isMin()) {
            return new ShotScore(score, ScoreType.GUTTER);
        }

        return new ShotScore(score, ScoreType.MISS);
    }

    ShotScore next(int nextScore) {
        if (!isClear() &&
                score.isMax(nextScore)) {
            next = new ShotScore(Score.of(nextScore), ScoreType.SPARE);
            return next;
        }

        next = init(nextScore);
        return next;
    }

    boolean isClear() {
        return scoreType.isIn(ScoreType.STRIKE, ScoreType.SPARE);
    }

    public ScoreType scoreType() {
        return scoreType;
    }

    public int singleScore() {
        return score.score();
    }

    public Integer totalScore() {
        if (scoreType.isIn(ScoreType.STRIKE)) {
            return Optional.ofNullable(next)
                    .flatMap(next -> Optional.ofNullable(next.next)
                            .map(nextAfter -> nextAfter.singleScore() + next.singleScore() + singleScore()))
                    .orElse(null);
        }

        if (scoreType.isIn(ScoreType.SPARE)) {
            return Optional.ofNullable(next)
                    .map(next -> next.singleScore() + singleScore())
                    .orElse(null);
        }

        return singleScore();
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
