package step2.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    @Test
    @DisplayName("핀 객체 생성")
    void createPin() {
        assertThat(Pins.from(3)).isInstanceOf(Pins.class);
    }

    @Test
    @DisplayName("떨어진 핀이 0개 밑일 경우 예외 처리")
    void exceptZero() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Pins.from(-1));
    }

    @Test
    @DisplayName("핀이 10개 초과일 경우 예외 처리")
    void exceptTen() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Pins.from(11));
    }

}