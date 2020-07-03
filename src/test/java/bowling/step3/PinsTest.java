package bowling.step3;

import bowling.step3.domain.state.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {

    @Test
    @DisplayName("객체 생성테스트")
    void create() {
        Pins pins = new Pins(7);
        assertThat(pins).isEqualTo(new Pins(7));
    }

    @ParameterizedTest
    @DisplayName("객체 유효성 검사")
    @ValueSource(ints = {-1,11})
    void create_invalid(int inputPins) {
        assertThatThrownBy(() -> new Pins(inputPins))
                    .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("객체 상태 테스트 - 스트라이크")
    void check_pins_strike() {
        Pins pins = new Pins(10);
        assertThat(pins.isGutter()).isFalse();
        assertThat(pins.isSpare(new Pins(2))).isFalse();
        assertThat(pins.isStrike()).isTrue();
    }

    @Test
    @DisplayName("객체 상태 테스트 - 스페어")
    void check_pins_spare() {
        Pins pins = new Pins(3);
        assertThat(pins.isStrike()).isFalse();
        assertThat(pins.isGutter()).isFalse();
        assertThat(pins.isSpare(new Pins(7))).isTrue();
    }

    @Test
    @DisplayName("객체 상태 테스트 - 거터")
    void check_pins_gutter() {
        Pins pins = new Pins(0);
        assertThat(pins.isStrike()).isFalse();
        assertThat(pins.isSpare(new Pins(2))).isFalse();
        assertThat(pins.isGutter()).isTrue();
    }
}
