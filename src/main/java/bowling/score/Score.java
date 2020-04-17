package bowling.score;

import bowling.domain.Frame;
import bowling.domain.Point;
import bowling.domain.Points;

public enum Score {
    STRIKE(points -> "X"),
    NORMAL(points -> String.valueOf(points.getPoint(0))),
    MISS(points -> "-"),

    STRIKE_STRIKE(points -> "X|X"),
    STRIKE_NORMAL(points -> "X|" + points.getPoint(1)),
    STRIKE_MISS(points -> "X|-"),
    MISS_SPARE(points -> "-|/"),
    MISS_MISS(points -> "-|-"),
    MISS_NORMAL(points -> "-|" + points.getPoint(1)),
    NORMAL_MISS(points -> points.getPoint(0) + "|-"),
    NORMAL_SPARE(points -> points.getPoint(0) + "|/"),
    NORMAL_NORMAL(points -> points.getPoint(0) + "|" + points.getPoint(1)),

    STRIKE_STRIKE_STRIKE(points -> "X|X|X"),
    STRIKE_STRIKE_MISS(points -> "X|X|-"),
    STRIKE_STRIKE_NORMAL(points -> "X|X|" + points.getPoint(2)),

    STRIKE_MISS_SPARE(points -> "X|-|/"),
    STRIKE_MISS_MISS(points -> "X|-|-"),
    STRIKE_MISS_NORMAL(points -> "X|-|" + points.getPoint(2)),

    STRIKE_NORMAL_SPARE(points -> "X|" + points.getPoint(1) + "|/"),
    STRIKE_NORMAL_MISS(points -> "X|" + points.getPoint(1) + "|-"),
    STRIKE_NORMAL_NORMAL(points -> "X|" + points.getPoint(1) + "|" + points.getPoint(2)),

    MISS_SPARE_STRIKE(points -> "-|/|X"),
    MISS_SPARE_MISS(points -> "-|/|-"),
    MISS_SPARE_NORMAL(points -> "-|/|" + points.getPoint(2)),

    NORMAL_SPARE_STRIKE(points -> points.getPoint(0) + "|/|X"),
    NORMAL_SPARE_MISS(points -> points.getPoint(0) + "|/|-"),
    NORMAL_SPARE_NORMAL(points -> points.getPoint(0) + "|/|" + points.getPoint(2));

    Markable markable;

    Score(Markable markable) {
        this.markable = markable;
    }

    public static String getScoreMark(Frame frame) {
        Points points = frame.getPoints();
        int tryCount = points.getTryCount();
        if (tryCount == 1) {
            return whenFirstTry(points.get(0)).getMark(points);
        }
        if (tryCount == 2) {
            return whenSecondTry(points.get(0), points.get(1)).getMark(points);
        }
        if (tryCount == 3) {
            return whenThirdTry(points.get(0), points.get(1), points.get(2)).getMark(points);
        }
        return "";
    }

    // 한번 던졌을 때
    private static Score whenFirstTry(Point firstPoint) {
        if (isStrike(firstPoint)) {
            return STRIKE;
        }
        if (isMiss(firstPoint)) {
            return MISS;
        }
        return NORMAL;
    }

    // 두번 던졌을때
    private static Score whenSecondTry(Point firstPoint, Point secondPoint) {
        if (isStrike(firstPoint)) {
            return whenSecondTryWithFirstStrike(secondPoint);
        }
        if (isMiss(firstPoint)) {
            return whenSecondTryWithFirstMiss(secondPoint);
        }
        return whenSecondTryWithFirstNormal(firstPoint, secondPoint);
    }

    // 두번 던졌을때 & 초구 스트라이크
    private static Score whenSecondTryWithFirstStrike(Point secondPoint) {
        if (isStrike(secondPoint)) {
            return STRIKE_STRIKE;
        }
        if (isMiss(secondPoint)) {
            return STRIKE_MISS;
        }
        return STRIKE_NORMAL;
    }

    // 두번 던졌을때 & 초구 미쓰
    private static Score whenSecondTryWithFirstMiss(Point secondPoint) {
        if (isStrike(secondPoint)) {
            return MISS_SPARE;
        }
        if (isMiss(secondPoint)) {
            return MISS_MISS;
        }
        return MISS_NORMAL;
    }

    // 두번 던졌을때 & 초구가 스트라이크랑 미쓰가 아닐때
    private static Score whenSecondTryWithFirstNormal(Point firstPoint, Point secondPoint) {
        int sumPoint = firstPoint.getPoint() + secondPoint.getPoint();
        if (sumPoint == 10) {
            return NORMAL_SPARE;
        }
        if (isMiss(secondPoint)) {
            return NORMAL_MISS;
        }
        return NORMAL_NORMAL;
    }

    // 세번째까지 던졌을때
    private static Score whenThirdTry(Point firstPoint, Point secondPoint, Point thirdPoint) {
        Score preScore = whenSecondTry(firstPoint, secondPoint);
        int sumPoint = firstPoint.getPoint() + secondPoint.getPoint() + thirdPoint.getPoint();
        if (preScore.equals(STRIKE_STRIKE)) {
            return whenThirdTryWithDoubleStrike(thirdPoint);
        }
        if (preScore.equals(STRIKE_NORMAL)) {
            return whenThirdTryWithStrikeNormal(thirdPoint, sumPoint);
        }
        if (preScore.equals(STRIKE_MISS)) {
            return whenThirdTryWithStrikeMiss(thirdPoint, sumPoint);
        }
        return whenThirdTryWithSpare(preScore, thirdPoint);
    }

    // 세번 던졌을때 & 더블 스트라이크 후
    private static Score whenThirdTryWithDoubleStrike(Point thirdPoint) {
        if (isStrike(thirdPoint)) {
            return STRIKE_STRIKE_STRIKE;
        }
        if (isMiss(thirdPoint)) {
            return STRIKE_STRIKE_MISS;
        }
        return STRIKE_STRIKE_NORMAL;
    }

    // 세번 던졌을때 & 스트라이크 normal 후
    private static Score whenThirdTryWithStrikeNormal(Point thirdPoint, int sumPoint) {
        if (sumPoint == 20) {
            return STRIKE_NORMAL_SPARE;
        }
        if (isMiss(thirdPoint)) {
            return STRIKE_NORMAL_MISS;
        }
        return STRIKE_NORMAL_NORMAL;
    }

    // 세번 던졌을때 & 스트라이크 미스 후
    private static Score whenThirdTryWithStrikeMiss(Point thirdPoint, int sumPoint) {
        if (sumPoint == 20) {
            return STRIKE_MISS_SPARE;
        }
        if (isMiss(thirdPoint)) {
            return STRIKE_MISS_MISS;
        }
        return STRIKE_MISS_NORMAL;
    }

    // 세번 던졌을때 & 스페어 후
    private static Score whenThirdTryWithSpare(Score preScore, Point thirdPoint) {
        if (preScore.equals(MISS_SPARE)) {
            return whenThirdTryWithMissSpare(thirdPoint);
        }
        return whenThirdTryWithNormalSpare(thirdPoint);
    }

    // 세번 던졌을때 & 스페어(miss spare) 후
    private static Score whenThirdTryWithMissSpare(Point thirdPoint) {
        if (isStrike(thirdPoint)) {
            return MISS_SPARE_STRIKE;
        }
        if (isMiss(thirdPoint)) {
            return MISS_SPARE_MISS;
        }
        return MISS_SPARE_NORMAL;
    }

    // 세번 던졌을때 & 스페어(miss normal) 후
    private static Score whenThirdTryWithNormalSpare(Point thirdPoint) {
        if (isStrike(thirdPoint)) {
            return NORMAL_SPARE_STRIKE;
        }
        if (isMiss(thirdPoint)) {
            return NORMAL_SPARE_MISS;
        }
        return NORMAL_SPARE_NORMAL;
    }

    private static boolean isStrike(Point point) {
        if (point.getPoint() == 10) {
            return true;
        }
        return false;
    }

    private static boolean isMiss(Point point) {
        if (point.getPoint() == 0) {
            return true;
        }
        return false;
    }

    private String getMark(Points points) {
        return this.markable.getMark(points);
    }
}
