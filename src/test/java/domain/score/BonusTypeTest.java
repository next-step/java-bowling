package domain.score;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BonusTypeTest {

    @Test
    public void strike() {
        BonusType type = BonusType.strike();
        assertThat(type.hasBonus()).isTrue();

        BonusType next = type.next();
        assertThat(next).isEqualTo(BonusType.spare());
    }

    @Test
    public void spares() {
        BonusType type = BonusType.spare();
        assertThat(type.hasBonus()).isTrue();

        BonusType next = type.next();
        assertThat(next).isEqualTo(BonusType.normal());
    }

    @Test
    public void open() {
        BonusType type = BonusType.normal();
        assertThat(type.hasBonus()).isFalse();

        BonusType next = type.next();
        assertThat(next).isEqualTo(BonusType.normal());
    }
}