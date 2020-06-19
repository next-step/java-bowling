package bowling.domain.score;

public class Score {
    private final static int MAX_SCORE = 10;
    private final static int MIN_SCORE = 0;

    private int point;
    private ScoreType scoreType;

    private Score(int point, ScoreType scoreType) {
        this.validatePoint(point);
        this.point = point;
        this.scoreType = scoreType;
    }

    private void validatePoint(int point) {
        if (point > MAX_SCORE || point < MIN_SCORE) {
            throw new IllegalArgumentException("not valid point");
        }
    }

    public int getPoint() {
        return point;
    }

    public static Score generateFirstScore(int point) {
        return new Score(point, generateFirstScoreType(point));
    }

    public static Score generateNextScore(Score beforeScore, int point) {
        return new Score(point, generateScoreType(beforeScore, point));
    }

    private static ScoreType generateFirstScoreType(int point) {
        if (point == MAX_SCORE) {
            return ScoreType.STRIKE;
        }

        if (point == MIN_SCORE) {
            return ScoreType.GUTTER;
        }

        return ScoreType.MISS;
    }

    private static ScoreType generateScoreType(Score beforeScore, int point) {
        if (beforeScore.getPoint() + point == MAX_SCORE) {
            return ScoreType.SPARE;
        }

        if (point == MIN_SCORE) {
            return ScoreType.GUTTER;
        }

        return ScoreType.MISS;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public boolean isStrike() {
        return scoreType == ScoreType.STRIKE;
    }

    public boolean isSpare() {
        return scoreType == ScoreType.SPARE;
    }
}
