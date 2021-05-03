package bowling.domain.status;

import bowling.domain.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusTest {
    @DisplayName("보너스 투구 이전 투구가 스트라이크의 경우 종료여부는 거짓을 반환한다.")
    @Test
    void isEnd_strike() {
        Status bonus = new Bonus(new Pitch(10), 2, new Strike());
        assertThat(bonus.isEnd()).isFalse();
    }

    @DisplayName("보너스 투구 이전 투구가 스페어의 경우 종료여부는 참을 반환한다.")
    @Test
    void isEnd_spare() {
        Status bonus = new Bonus(new Pitch(10), 1, new Spare());
        assertThat(bonus.isEnd()).isTrue();
    }

    @DisplayName("보너스 투구 이전 투구가 스트라이크의 경우 남은 보너스 투구 횟수는 1을 반환한다.")
    @Test
    void bonusPitchCount_strike() {
        Status bonus = new Bonus(new Pitch(10), 2, new Strike());
        assertThat(bonus.bonusPitchCount()).isEqualTo(1);
    }

    @DisplayName("보너스 투구 이전 투구가 스페어의 경우 남은 보너스 투구 횟수는 1을 반환한다.")
    @Test
    void bonusPitchCount_spare() {
        Status bonus = new Bonus(new Pitch(10), 1, new Spare());
        assertThat(bonus.bonusPitchCount()).isEqualTo(0);
    }

    @DisplayName("보너스 투구 이전 투구 결과에서 보너스 투구 횟수가 0인 경우 예외를 반환한다.")
    @Test
    void bonusPitchCount_exception() {
        assertThatThrownBy(() -> new Bonus(new Pitch(10), 0, new Spare()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
