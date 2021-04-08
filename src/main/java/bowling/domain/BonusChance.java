package bowling.domain;

import java.util.Objects;

public class BonusChance {
    private static final String MAX_OVER_CHANCE = "최대 보너스 기회는 2회 입니다.";
    private static final String MIN_UNDER_CHANCE = "최소 보너스 기회는 0회 입니다";
    private static final int MAX_CHANCE = 2;
    private static final int MIN_CHANCE = 0;
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
        return of(bonusChance - 1);
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
