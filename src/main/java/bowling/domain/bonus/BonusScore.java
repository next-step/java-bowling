package bowling.domain.bonus;

import bowling.domain.point.Points;

public class BonusScore {
    private static final int STRIKE_COUNT = 2;
    private static final int SPARE_COUNT = 1;
    private static final int NON_COUNT = 0;

    private final Points bonusPoints;
    private final BonusScoreInfo bonusScoreInfo;

    private BonusScore(Points bonusPoints, BonusScoreInfo bonusScoreInfo) {
        this.bonusPoints = bonusPoints;
        this.bonusScoreInfo = bonusScoreInfo;
    }

    public static BonusScore noneBonusScores(int frameIndex) {
        return new BonusScore(new Points(), new BonusScoreInfo(NON_COUNT, frameIndex));
    }

    public static BonusScore strikeBonusScore(int frameIndex) {
        return new BonusScore(new Points(), new BonusScoreInfo(STRIKE_COUNT, frameIndex));
    }

    public static BonusScore spareBonusScore(int frameIndex) {
        return new BonusScore(new Points(), new BonusScoreInfo(SPARE_COUNT, frameIndex));
    }

    public void addPoint(int point) {
        bonusPoints.addPoint(point);
    }

    public boolean isAvailableAddBonusPoint() {
        return bonusScoreInfo.getBonusCount() > bonusPoints.size();
    }

    public int calculateBonusPoints() {
        return bonusPoints.sumPoints();
    }
}
