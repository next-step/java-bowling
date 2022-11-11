package bowling.domain;

import java.util.Objects;

public class Chance {

    private final int chance;

    public Chance(int chance) {
        validatePositive(chance);
        this.chance = chance;
    }

    private void validatePositive(int chance) {
        if (chance < 0) {
            throw new IllegalArgumentException("Chance는 음수일 수 없습니다.");
        }
    }

    public Chance minusOne() {
        return new Chance(this.chance - 1);
    }

    public Chance minusTwo() {
        return new Chance(this.chance - 2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.chance);
    }

    public boolean isRemainChance() {
        return this.chance > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chance chance1 = (Chance) o;
        return this.chance == chance1.chance;
    }
}
