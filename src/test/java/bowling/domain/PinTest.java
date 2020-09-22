package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class PinTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void create(int input) {
        Pin pin = new Pin(input);

        assertThat(pin).isEqualTo(new Pin(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void create_invalid(int input) {
        assertThatThrownBy(() -> new Pin(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void next() {
        Pin pin = new Pin(9);
        Pin nextPin = pin.next(1);

        assertThat(nextPin).isEqualTo(new Pin(1));
    }

    @Test
    void next_invalid() {
        Pin pin = new Pin(9);
        assertThatThrownBy(() -> pin.next(2))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void isEnd() {
        Pin pin = new Pin(10);

        assertThat(pin.isEnd()).isTrue();
    }

    @Test
    void getSymbol_strike() {
        Pin pin = new Pin(10);
        assertThat(pin.getSymbol()).isEqualTo(ScoreSymbol.STRIKE);
    }

    @Test
    void getSymbol_miss() {
        Pin pin = new Pin(0);
        assertThat(pin.getSymbol()).isEqualTo(ScoreSymbol.MISS);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void getSymbol(int first, int second, ScoreSymbol expect) {
        Pin pin = new Pin(first);
        Pin nextPin = pin.next(second);

        assertThat(nextPin.getSymbol()).isEqualTo(expect);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(1, 9, ScoreSymbol.SPARE),
                Arguments.of(5, 5, ScoreSymbol.SPARE),
                Arguments.of(0, 0, ScoreSymbol.GUTTER),
                Arguments.of(1, 2, ScoreSymbol.MISS)
        );
    }
}
