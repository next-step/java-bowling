package bowling.score;

import bowling.domain.Frame;
import bowling.domain.Point;

import java.util.List;

public enum Score {
    STRIKE(points -> "X"),
    NOMAL(points -> String.valueOf(points.get(0).getPoint())),
    MISS(points -> "-"),

    STRIKE_STRIKE(points -> "X|X"),
    STRIKE_NOMAL(points -> "X|" + points.get(1).getPoint()),
    STRIKE_MISS(points -> "X|-"),
    MISS_SPARE(points -> "-|/"),
    MISS_MISS(points -> "-|-"),
    MISS_NOMAL(points -> "-|" + points.get(1).getPoint()),
    NOMAL_MISS(points -> points.get(0).getPoint() + "|-"),
    NOMAL_SPARE(points -> points.get(0).getPoint() + "|/"),
    NOMAL_NOMAL(points -> points.get(0).getPoint() + "|" + points.get(1).getPoint()),

    STRIKE_STRIKE_STRIKE(points -> "X|X|X"),
    STRIKE_STRIKE_MISS(points -> "X|X|-"),
    STRIKE_STRIKE_NOMAL(points -> "X|X|" + points.get(2).getPoint()),

    STRIKE_MISS_SPARE(points -> "X|-|/"),
    STRIKE_MISS_MISS(points -> "X|-|-"),
    STRIKE_MISS_NOMAL(points -> "X|-|" + points.get(2).getPoint()),

    STRIKE_NOMAL_SPARE(points -> "X|" + points.get(1).getPoint() + "|/"),
    STRIKE_NOMAL_MISS(points -> "X|" + points.get(1).getPoint() + "|-"),
    STRIKE_NOMAL_NOMAL(points -> "X|" + points.get(1).getPoint() + "|" + points.get(2).getPoint()),

    MISS_SPARE_STRIKE(points -> "-|/|X"),
    MISS_SPARE_MISS(points -> "-|/|-"),
    MISS_SPARE_NOMAL(points -> "-|/|" + points.get(2).getPoint()),

    NOMAL_SPARE_STRIKE(points -> points.get(0).getPoint() + "|/|X"),
    NOMAL_SPARE_MISS(points -> points.get(0).getPoint() + "|/|-"),
    NOMAL_SPARE_NOMAL(points -> points.get(0).getPoint() + "|/|" + points.get(2).getPoint());

    Markable markable;

    Score(Markable markable) {
        this.markable = markable;
    }

    public static String getScoreMark(Frame frame) {
        List<Point> points = frame.getPoints();
        int tryCount = points.size();
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
        return NOMAL;
    }

    // 두번 던졌을때
    private static Score whenSecondTry(Point firstPoint, Point secondPoint) {
        if (isStrike(firstPoint)) {
            return whenSecondTryWithFirstStrike(secondPoint);
        }
        if (isMiss(firstPoint)) {
            return whenSecondTryWithFirstMiss(secondPoint);
        }
        return whenSecondTryWithFirstNomal(firstPoint, secondPoint);
    }

    // 두번 던졌을때 & 초구 스트라이크
    private static Score whenSecondTryWithFirstStrike(Point secondPoint) {
        if (isStrike(secondPoint)) {
            return STRIKE_STRIKE;
        }
        if (isMiss(secondPoint)) {
            return STRIKE_MISS;
        }
        return STRIKE_NOMAL;
    }

    // 두번 던졌을때 & 초구 미쓰
    private static Score whenSecondTryWithFirstMiss(Point secondPoint) {
        if (isStrike(secondPoint)) {
            return MISS_SPARE;
        }
        if (isMiss(secondPoint)) {
            return MISS_MISS;
        }
        return MISS_NOMAL;
    }

    // 두번 던졌을때 & 초구가 스트라이크랑 미쓰가 아닐때
    private static Score whenSecondTryWithFirstNomal(Point firstPoint, Point secondPoint) {
        int sumPoint = firstPoint.getPoint() + secondPoint.getPoint();
        if (sumPoint == 10) {
            return NOMAL_SPARE;
        }
        if (isMiss(secondPoint)) {
            return NOMAL_MISS;
        }
        return NOMAL_NOMAL;
    }

    // 세번째까지 던졌을때
    private static Score whenThirdTry(Point firstPoint, Point secondPoint, Point thirdPoint) {
        Score preScore = whenSecondTry(firstPoint, secondPoint);
        int sumPoint = firstPoint.getPoint() + secondPoint.getPoint() + thirdPoint.getPoint();
        if (preScore.equals(STRIKE_STRIKE)) {
            return whenThirdTryWithDoubleStrike(thirdPoint);
        }
        if (preScore.equals(STRIKE_NOMAL)) {
            return whenThirdTryWithStrikeNomal(thirdPoint, sumPoint);
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
        return STRIKE_STRIKE_NOMAL;
    }

    // 세번 던졌을때 & 스트라이크 nomal 후
    private static Score whenThirdTryWithStrikeNomal(Point thirdPoint, int sumPoint) {
        if (sumPoint == 20) {
            return STRIKE_NOMAL_SPARE;
        }
        if (isMiss(thirdPoint)) {
            return STRIKE_NOMAL_MISS;
        }
        return STRIKE_NOMAL_NOMAL;
    }

    // 세번 던졌을때 & 스트라이크 미스 후
    private static Score whenThirdTryWithStrikeMiss(Point thirdPoint, int sumPoint) {
        if (sumPoint == 20) {
            return STRIKE_MISS_SPARE;
        }
        if (isMiss(thirdPoint)) {
            return STRIKE_MISS_MISS;
        }
        return STRIKE_MISS_NOMAL;
    }

    // 세번 던졌을때 & 스페어 후
    private static Score whenThirdTryWithSpare(Score preScore, Point thirdPoint) {
        if (preScore.equals(MISS_SPARE)) {
            return whenThirdTryWithMissSpare(thirdPoint);
        }
        return whenThirdTryWithNomalSpare(thirdPoint);
    }

    // 세번 던졌을때 & 스페어(miss spare) 후
    private static Score whenThirdTryWithMissSpare(Point thirdPoint) {
        if (isStrike(thirdPoint)) {
            return MISS_SPARE_STRIKE;
        }
        if (isMiss(thirdPoint)) {
            return MISS_SPARE_MISS;
        }
        return MISS_SPARE_NOMAL;
    }

    // 세번 던졌을때 & 스페어(miss nomal) 후
    private static Score whenThirdTryWithNomalSpare(Point thirdPoint) {
        if (isStrike(thirdPoint)) {
            return NOMAL_SPARE_STRIKE;
        }
        if (isMiss(thirdPoint)) {
            return NOMAL_SPARE_MISS;
        }
        return NOMAL_SPARE_NOMAL;
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

    private String getMark(List<Point> points) {
        return this.markable.getMark(points);
    }
}
