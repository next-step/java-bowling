package bowling.domain;

import bowling.domain.status.Miss;
import bowling.domain.status.Spare;
import bowling.domain.status.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BonusPitchTest {
    @DisplayName("스트라이크의 경우 보너스 투구 횟수는 2를 반환한다.")
    @Test
    void isAbleToPitch_strike() {
        BonusPitch bonusPitch = new BonusPitch(new Strike());
        assertThat(bonusPitch.addedBonusCount()).isEqualTo(2);
    }

    @DisplayName("스페어의 경우 보너스 투구 횟수는 1을 반환한다.")
    @Test
    void isAbleToPitch_spare() {
        BonusPitch bonusPitch = new BonusPitch(new Spare());
        assertThat(bonusPitch.addedBonusCount()).isEqualTo(1);
    }

    @DisplayName("미스의 경우 보너스 투구 횟수는 0을 반환한다.")
    @Test
    void isAbleToPitch_miss() {
        BonusPitch bonusPitch = new BonusPitch(new Miss(new Pitch(8)));
        assertThat(bonusPitch.addedBonusCount()).isEqualTo(0);
    }
}
