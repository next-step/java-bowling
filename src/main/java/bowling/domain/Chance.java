package bowling.domain;

import java.util.Objects;

public class Chance {

    private int chance;

    public Chance(int chance) {
        validatePositive(chance);
        this.chance = chance;
    }

    private void validatePositive(int chance) {
        if (chance < 0) {
            throw new IllegalArgumentException("Chance는 음수일 수 없습니다.");
        }
    }

    public void minusOne() {
        this.chance--;
        validatePositive(this.chance);
    }

    public void minusTwo() {
        this.chance -= 2;
        validatePositive(this.chance);
    }

    public boolean isRemainChance() {
        return this.chance > 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.chance);
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
