package bowling;

public class Score {
    private final int hitCount;
    private final int bonusCount;

    public static Score ofZero() {
        return new Score(0, -1);
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofMiss(int hitCount) {
        return new Score(hitCount, 0);
    }

    private Score(int hitCount, int bonusCount) {
        this.hitCount = hitCount;
        this.bonusCount = bonusCount;
    }

    public boolean remainBonus() {
        return bonusCount != 0;
    }

    public Score addBonusScore(int hitCount) {
        return new Score(this.hitCount + hitCount, bonusCount - 1);
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getBonusCount() {
        return bonusCount;
    }

    public boolean isMiss() {
        return hitCount != 10;
    }
}
