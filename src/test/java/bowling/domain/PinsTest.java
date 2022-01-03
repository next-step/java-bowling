package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void 생성(int count) {
        assertThat(Pins.of(count)).isInstanceOf(Pins.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    void 핀수가_최소보다_작을경우(int count) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Pins.of(count));
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 12, 13})
    void 핀수가_최대보다_큰경우(int count) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Pins.of(count));
    }

    @Test
    void strike_인경우() {
        assertAll(() -> assertTrue(Pins.of(10).isStrike()),
            () -> assertFalse(Pins.of(9).isStrike())
        );
    }

    @Test
    void spare_인경우() {
        assertAll(() -> assertTrue(Pins.of(8).isSpare(Pins.of(2))),
            () -> assertFalse(Pins.of(8).isSpare(Pins.of(1)))
        );
    }

    @Test
    void gutter_인경우() {
        assertAll(() -> assertTrue(Pins.of(0).isGutter()),
            () -> assertFalse(Pins.of(2).isGutter())
        );
    }

}
