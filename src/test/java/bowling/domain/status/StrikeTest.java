package bowling.domain.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    @DisplayName("스트라이크 투구 다음 투구에 10핀을 쓰러트리면 보너스 객체를 반환한다.")
    @Test
    void roll_strike() {
        Status strike = new Strike();
        assertThat(strike.roll(10)).isExactlyInstanceOf(Bonus.class);
    }

    @DisplayName("스트라이크 투구 다음 투구에 10핀 이외를 쓰러트리면 보너스 객체를 반환한다.")
    @Test
    void roll_hold() {
        Status strike = new Strike();
        assertThat(strike.roll(2)).isExactlyInstanceOf(Bonus.class);
    }

    @DisplayName("스트라이크의 보너스 투구 가능여부는 참을 반환한다.")
    @Test
    void hasBonusPitch() {
        Status strike = new Strike();
        assertThat(strike.hasBonusPitch()).isTrue();
    }

    @DisplayName("스트라이크의 보너스 투구는 2를 반환.")
    @Test
    void bonusPitchCount_strike() {
        Status strike = new Strike();
        assertThat(strike.bonusPitchCount()).isEqualTo(2);
    }
}
