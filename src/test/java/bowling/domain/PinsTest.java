package bowling.domain;

import bowling.exception.InvalidPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PinsTest {
    @DisplayName("생성 테스트")
    @Test
    void create() {
        Pins pins = Pins.of(4);

        assertThat(pins).isEqualTo(Pins.of(4));
    }

    @DisplayName("Exception 테스트(Pins 생성시 0 과 10 사이의 값 이어야 합니다.)")
    @Test
    void exception() {
        assertThatExceptionOfType(InvalidPinsException.class)
                .isThrownBy(() -> {
                    Pins pins = Pins.of(-1);
                }).withMessageMatching("핀은 0 부터 10 사이의 값 이어야 합니다.");
    }

    @DisplayName("get 테스트")
    @Test
    void getFallenPins() {
        Pins pins = Pins.of(4);

        assertThat(pins.getFallenPins()).isEqualTo(4);
    }

    @DisplayName("Strike 테스트")
    @Test
    void isStrike() {
        Pins pins = Pins.of(10);

        assertThat(pins.isMax()).isTrue();
    }

    @DisplayName("Sum 테스트")
    @Test
    void isSumTest() {
        Pins pins = Pins.of(3);
        Pins secondPins = Pins.of(5);
        Pins sum = pins.sum(secondPins);

        assertThat(sum).isEqualTo(Pins.of(8));
    }

    @DisplayName("isMax 테스트")
    @Test
    void isMaxTest() {
        Pins pins = Pins.of(10);

        assertThat(pins.isMax()).isTrue();
    }

    @DisplayName("isMin 테스트")
    @Test
    void isMinTest() {
        Pins pins = Pins.of(0);

        assertThat(pins.isMin()).isTrue();
    }
}
