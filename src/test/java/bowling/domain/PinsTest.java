package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("핀 생성 성공")
    void create(int count) {
        assertThat(Pins.of(count)).isInstanceOf(Pins.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    @DisplayName("핀 수가 최소 수 보다 작을 경우")
    void minimumException(int count) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Pins.of(count));
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 12, 13})
    @DisplayName("핀 수가 최소 수 보다 작을 경우")
    void maximumException(int count) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Pins.of(count));
    }

    @Test
    @DisplayName("스트라이크 확인")
    void isStrike() {
        assertAll(() -> assertTrue(Pins.of(10).isStrike()),
            () -> assertFalse(Pins.of(9).isStrike())
        );
    }

    @Test
    @DisplayName("스페어 확인")
    void isSpare() {
        assertAll(() -> assertTrue(Pins.of(8).isSpare(Pins.of(2))),
            () -> assertFalse(Pins.of(8).isSpare(Pins.of(1)))
        );
    }

    @Test
    @DisplayName("거터 확인")
    void isGutter() {
        assertAll(() -> assertTrue(Pins.of(0).isGutter()),
            () -> assertFalse(Pins.of(2).isGutter())
        );
    }

}
