package bowling.domain;

public class BonusShotCount {

    private static final int STRIKE_BONUS_COUNT = 2;

    private int count;

    public BonusShotCount(int count) {
        this.count = count;
    }

    public static BonusShotCount ofStrike() {
        return new BonusShotCount(STRIKE_BONUS_COUNT);
    }

    public void minus() {
        if (count > 0)
            --count;
    }

    public int get() {
        return count;
    }

    public boolean isRemained() {
        return count > 0;
    }
}
