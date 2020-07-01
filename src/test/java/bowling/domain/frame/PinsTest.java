package bowling.domain.frame;

import bowling.domain.status.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        assertThat(pins.firstBowl(3).printResult()).isEqualTo("3");
        assertThat(pins.bowl(7, pins.firstBowl(3)).printResult()).isEqualTo("3|/");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("투구 값이 핀 개수 범위 내인지 혹인 ")
    void validateRange(int downPin) {
        Pins pins = Pins.init();
        assertThatThrownBy(() -> {
            Status status = pins.firstBowl(downPin);
            pins.bowl(downPin, status);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("연속 투구 합이 핀 개수(10) 범위 내인지 확인")
    void validateRange_sum() {
        Pins pins = Pins.init();
        Status status = pins.firstBowl(7);
        assertThatThrownBy(() -> pins.bowl(5, status))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("투구 결과 확인")
    void status_check() {
        Pins pins = Pins.init();
        Status status = pins.firstBowl(4);
        assertAll(() -> {
            assertThat(Pins.init().firstBowl(10) instanceof Strike).isTrue();
            assertThat(pins.firstBowl(1) instanceof Pending).isTrue();
            assertThat(pins.bowl(6, status) instanceof Spare).isTrue();
            assertThat(pins.bowl(3, status) instanceof Miss).isTrue();
        });
    }


}
