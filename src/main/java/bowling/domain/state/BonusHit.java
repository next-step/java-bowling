package bowling.domain.state;

import bowling.domain.Hit;
import bowling.domain.Score;

import java.util.Objects;

public class BonusHit extends Finished {

    private final Hit bonusHit;

    public BonusHit(int hit) {
        this(Hit.valueOf(hit));
    }

    public BonusHit(Hit bonusHit) {
        this.bonusHit = bonusHit;
    }

    @Override
    public boolean hasBonusChance() {
        return false;
    }

    @Override
    public int bowlingCount() {
        return 1;
    }

    @Override
    public String description() {
        return bonusHit.description();
    }

    @Override
    public Score score() {
        return Score.bonus(bonusHit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusHit bonusHit = (BonusHit) o;
        return Objects.equals(this.bonusHit, bonusHit.bonusHit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusHit);
    }
}
