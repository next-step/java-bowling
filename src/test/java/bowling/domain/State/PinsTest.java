package bowling.domain.State;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PinsTest {

    @DisplayName("핀을 쓰러트릴 수 있는 범위에 해당되지 않을 경우 예외처리")
    @ParameterizedTest
    @ValueSource(ints = { -1, 11 })
    void validatePins(int pins) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Pins.bowl(pins);
        });
    }

    @DisplayName("핀이 스트라이크 일 경우")
    @Test
    void strike() {
        Pins pins = Pins.bowl(10);
        assertThat(pins.isStrike()).isTrue();
        assertThat(pins.getDesc()).isEqualTo("X");
    }

    @DisplayName("핀이 스페어 경우")
    @Test
    void spare() {
        Pins pins = Pins.bowl(3);
        assertThat(pins.isSpare(Pins.bowl(7))).isTrue();
        assertThat(pins.getDesc(Pins.bowl(7))).isEqualTo("3|/");
    }

    @DisplayName("핀의 상태가 거터일 경우")
    @Test
    void gutter() {
        Pins pins = Pins.bowl(0);
        assertThat(pins.getDesc(Pins.bowl(0))).isEqualTo("-|-");
    }

    @DisplayName("쓰러트린 전체 핀을 가져온다")
    @Test
    void totalPins() {
        Pins pins = Pins.bowl(2);
        assertEquals(5, pins.totalPins(Pins.bowl(3)));
    }
}
