package bowling;

import java.util.Objects;

import static bowling.CommonConstans.*;

public class BonusChance {


    private final int bonusChance;

    private BonusChance(int bonusChance) {
        this.bonusChance = bonusChance;
    }

    public static BonusChance of(int bonusChance) {
        validMaxBonusChance(bonusChance);
        validMinBonusChance(bonusChance);
        return new BonusChance(bonusChance);
    }

    public BonusChance minus() {
        return new BonusChance(bonusChance - 1);
    }

    public boolean isAddScore() {
        return bonusChance > MIN_CHANCE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusChance that = (BonusChance) o;
        return bonusChance == that.bonusChance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusChance);
    }

    private static void validMinBonusChance(int bonusChance) {
        if (MIN_CHANCE > bonusChance) {
            throw new IllegalArgumentException(MIN_UNDER_CHANCE);
        }
    }

    private static void validMaxBonusChance(int bonusChance) {
        if (MAX_CHANCE < bonusChance) {
            throw new IllegalArgumentException(MAX_OVER_CHANCE);
        }
    }
}
