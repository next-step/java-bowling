package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PinsTest {
    @Test
    @DisplayName("생성 테스트")
    void init() {
        assertThatCode(() -> Pins.init()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투구 테스트")
    void bowl() {
        Pins pins = Pins.init();
        assertThat(pins.bowl(3).getDownPin()).isEqualTo(3);
        assertThat(pins.bowl(7).getDownPin()).isEqualTo(10);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("투구 값이 핀 개수 범위 내인지 혹인 ")
    void validateRange(int downPin) {
        Pins pins = Pins.init();
        assertThatThrownBy(() -> pins.bowl(downPin))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("연속 투구 합이 핀 개수(10) 범위 내인지 확인")
    void validateRange_sum() {
        Pins pins = Pins.init();
        assertThatThrownBy(() -> pins.bowl(10).bowl(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
