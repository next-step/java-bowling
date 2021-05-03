package bowling.domain.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    @DisplayName("첫투구에서 10핀을 쓰러트리면 스트라이크 객체를 반환한다.")
    @Test
    void roll_strike() {
        Ready ready = new Ready();
        assertThat(ready.roll(10)).isExactlyInstanceOf(Strike.class);
    }

    @DisplayName("첫투구에서 10핀을 쓰러트리지 못하면 홀드 객체를 반환한다.")
    @Test
    void roll_hold() {
        Ready ready = new Ready();
        assertThat(ready.roll(7)).isExactlyInstanceOf(Hold.class);
    }

    @DisplayName("첫투구의 보너스 투구 가능여부는 거짓을 반환한다.")
    @Test
    void hasBonusPitch() {
        Ready ready = new Ready();
        assertThat(ready.hasBonusPitch()).isFalse();
    }

    @DisplayName("보너스 투구는 0을 반환.")
    @Test
    void bonusPitchCount() {
        Ready ready = new Ready();
        assertThat(ready.bonusPitchCount()).isEqualTo(0);
    }
}
