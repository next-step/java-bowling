package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class PinTest {
    @DisplayName("Pin 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void create(int input) {
        Pin pin = new Pin(input);

        assertThat(pin).isEqualTo(new Pin(input));
    }

    @DisplayName("Pin 생성 - 유효하지 않은 핀")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void create_invalid(int input) {
        assertThatThrownBy(() -> new Pin(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Pin 생성 - 다음 핀")
    @Test
    void next() {
        Pin pin = new Pin(9);
        Pin nextPin = pin.next(1);

        assertThat(nextPin).isEqualTo(new Pin(1));
    }

    @DisplayName("Pin 생성 - 유효하지 않은 개수")
    @Test
    void next_invalid() {
        Pin pin = new Pin(9);
        assertThatThrownBy(() -> pin.next(2))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("게임 종료된 pin")
    @Test
    void isEnd() {
        Pin pin = new Pin(10);

        assertThat(pin.isEnd()).isTrue();
    }
}
