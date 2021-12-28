package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HittingPinsTest {

    @Test
    @DisplayName("볼링 핀 객체 생성")
    void create() {
        HittingPins pins = new HittingPins(10);
        assertThat(pins).isEqualTo(new HittingPins(10));
    }

    @ParameterizedTest
    @DisplayName("볼링 핀 검증 0-10 아닐 경우 -> IllegalArgumentException 반환")
    @ValueSource(ints = {-1, 11})
    void invalid(int pin) {
        assertThatThrownBy(() -> new HittingPins(pin))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("count 10 일 경우 스트라이크")
    void isStrike() {
        HittingPins pins = new HittingPins(10);
        assertThat(pins.isStrike()).isTrue();
    }

}
