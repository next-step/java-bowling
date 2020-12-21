package bowling.domain.score;

import bowling.domain.point.Point;

public enum ScoreType {

    STRIKE(2),
    SPARED(1),
    MISS(0),
    NONE(0),
    END(0);

    private final int bonusCount;

    ScoreType(int bonusCount) {
        this.bonusCount = bonusCount;
    }


    public int getBonusCount() {
        return bonusCount;
    }

    public static final int ALL_PITCH_COUNT = 10;

    static ScoreType valueOf(Point firstPoint, Point secondPoint) {
        if (isStrike(firstPoint, secondPoint)) {
            return ScoreType.STRIKE;
        }
        if (isSpared(firstPoint, secondPoint)) {
            return ScoreType.SPARED;
        }

        if (!hasScoreTurn(firstPoint, secondPoint)) {
            return ScoreType.MISS;
        }

        return ScoreType.NONE;
    }

    private static boolean isStrike(Point firstPoint, Point secondPoint) {
        return firstPoint != null && secondPoint == null && firstPoint.getPoint() == ALL_PITCH_COUNT;
    }

    private static boolean isSpared(Point firstPoint, Point secondPoint) {
        return firstPoint != null && secondPoint != null && sumPoint(firstPoint, secondPoint) == ALL_PITCH_COUNT;
    }

    public static int sumPoint(Point firstPoint, Point secondPoint) {
        int sum = 0;
        if (firstPoint != null) {
            sum += firstPoint.getPoint();
        }
        if (secondPoint != null) {
            sum += secondPoint.getPoint();
        }
        return sum;
    }

    public static boolean hasScoreTurn(Point firstPoint, Point secondPoint) {
        if (secondPoint != null) {
            return false;
        }

        if (sumPoint(firstPoint, secondPoint) == ALL_PITCH_COUNT) {
            return false;
        }

        return true;
    }
}
