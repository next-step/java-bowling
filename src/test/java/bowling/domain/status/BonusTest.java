package bowling.domain.status;

import bowling.domain.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BonusTest {
    @DisplayName("보너스 투구가 스트라이크의 경우 종료여부는 거짓을 반환한다.")
    @Test
    void isEnd_strike() {
        Status bonus = new Bonus(new Pitch(10), 2, new Strike());
        assertThat(bonus.isEnd()).isFalse();
    }

    @DisplayName("보너스 투구가 스페어의 경우 종료여부는 참을 반환한다.")
    @Test
    void isEnd_spare() {
        Status bonus = new Bonus(new Pitch(10), 1, new Spare());
        assertThat(bonus.isEnd()).isTrue();
    }
}
