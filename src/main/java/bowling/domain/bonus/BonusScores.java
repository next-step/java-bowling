package bowling.domain.bonus;

import bowling.domain.point.Points;

public class BonusScores {
    private static final int STRIKE_COUNT = 2;
    private static final int SPARE_COUNT = 1;

    private int bonusCount;
    private Points points;

    private BonusScores(int bonusCount) {
        this.bonusCount = bonusCount;
        this.points = new Points();
    }

    public static BonusScores strikeBonusScore() {
        return new BonusScores(STRIKE_COUNT);
    }

    public static BonusScores spareBonusScore() {
        return new BonusScores(SPARE_COUNT);
    }

    public void addPoint(int point) {
        if (!availableAddBonusScore()) {
            throw new IllegalArgumentException("bonusScores can not add point");
        }
        points.addPoint(point);
        bonusCount--;
    }

    public boolean availableAddBonusScore() {
        return bonusCount > 0;
    }

    public int calculateBonusScore() {
        return points.sumPoints();
    }
}
