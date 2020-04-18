package bowling.domain;

import bowling.dto.ShotScoreDto;

import java.util.Objects;

class ShotScore {
    private static final int MAX = 10;
    private static final int MIN = 0;

    private final int score;
    private final ScoreType scoreType;

    private ShotScore(int score, ScoreType scoreType) {
        this.score = score;
        this.scoreType = scoreType;
    }

    static ShotScore of(int score) {
        if (score < MIN || MAX < score) {
            throw new IllegalArgumentException(String.format("create ShotScore fail, score must be %d~%d : score = %d", MIN, MAX, score));
        }
        if (score == MAX) {
            return new ShotScore(score, ScoreType.STRIKE);
        }

        if (score == MIN) {
            return new ShotScore(score, ScoreType.GUTTER);
        }

        return new ShotScore(score, ScoreType.MISS);
    }

    ShotScore next(int nextScore) {
        int totalScore = this.score + nextScore;

        if (MAX < totalScore) {
            throw new IllegalArgumentException(String.format("next ShotScore fail, nextScore + score must be %d~%d : thisScore = %d, nextScore = %d", MIN, MAX, this.score, nextScore));
        }

        if (totalScore == MAX) {
            return new ShotScore(nextScore, ScoreType.SPARE);
        }

        return of(nextScore);
    }

    boolean isClear() {
        return ScoreType.STRIKE.equals(scoreType) ||
                ScoreType.SPARE.equals(scoreType);
    }

    ShotScoreDto getDto() {
        return new ShotScoreDto(scoreType, score);
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
