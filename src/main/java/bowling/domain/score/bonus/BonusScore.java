package bowling.domain.score.bonus;

import bowling.domain.pin.Pins;

public class BonusScore {
    private static final int STRIKE_BONUS_COUNT = 2;
    private static final int SPARE_BONUS_COUNT = 1;
    private static final int MISS_BONUS_COUNT = 0;

    private final Pins bonusPins;
    private final int frameIndex;
    private final int bonusCount;

    public BonusScore(Pins bonusPins, int frameIndex, int bonusCount) {
        this.bonusPins = bonusPins;
        this.frameIndex = frameIndex;
        this.bonusCount = bonusCount;
    }

    public static BonusScore missBonus(int frameIndex) {
        return new BonusScore(new Pins(), frameIndex, MISS_BONUS_COUNT);
    }

    public static BonusScore strikeBonus(int frameIndex) {
        return new BonusScore(new Pins(), frameIndex, STRIKE_BONUS_COUNT);
    }

    public static BonusScore spareBonus(int frameIndex) {
        return new BonusScore(new Pins(), frameIndex, SPARE_BONUS_COUNT);
    }

    public void add(int pin) {
        bonusPins.add(pin);
    }

    public boolean isAddable() {
        return bonusPins.size() < bonusCount;
    }

    public boolean isEqualFrameIndex(int index) {
        return this.frameIndex == index;
    }
}
