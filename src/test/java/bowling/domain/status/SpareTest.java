package bowling.domain.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    @DisplayName("스페어 투구 다음 투구(10핀)는 보너스 상태를 반환한다.")
    @Test
    void roll_strike() {
        Status spare = new Spare();
        assertThat(spare.roll(10)).isExactlyInstanceOf(Bonus.class);
    }

    @DisplayName("스페어 투구 다음 투구(10핀 이외)는 보너스 상태를 반환한다.")
    @Test
    void roll_hold() {
        Status spare = new Spare();
        assertThat(spare.roll(8)).isExactlyInstanceOf(Bonus.class);
    }

    @DisplayName("스페어의 보너스 투구 가능여부는 참을 반환한다.")
    @Test
    void hasBonusPitch() {
        Status spare = new Spare();
        assertThat(spare.hasBonusPitch()).isTrue();
    }

    @DisplayName("스페어의 보너스 투구는 1을 반환.")
    @Test
    void bonusPitchCount_spare() {
        Spare spare = new Spare();
        assertThat(spare.bonusPitchCount()).isEqualTo(1);
    }
}
