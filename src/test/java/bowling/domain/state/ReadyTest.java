package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @DisplayName("준비 상태에서 쓰러진 핀이 10미만이면 첫번째볼링을 반환한다")
    @Test
    void bowl_toFirstBowling() {
        assertThat(new Ready().bowl(FallenPin.of(9))).isEqualTo(new FirstBowling(FallenPin.of(9)));
    }

    @DisplayName("준비 상태에서 쓰러진 핀이 10이면 스트라이크를 반환한다")
    @Test
    void bowl_toStrike() {
        assertThat(new Ready().bowl(FallenPin.of(10))).isEqualTo(new Strike());
    }

    @Test
    void description() {
        assertThat(new Ready().description()).isEqualTo("");
    }

    @Test
    void tries() {
        assertThat(new Ready().tries()).isEqualTo(0);
    }
}
