package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PinsTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("점수는 0 ~ 10 의 숫자만 가능하다.")
    void validate_score(int score) {
        assertThatThrownBy(() -> Pins.of(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("볼링핀의 갯수는 0 에서 10 까지만 가능합니다.");
    }

    @ParameterizedTest
    @MethodSource("printScore")
    @DisplayName("점수 출력 테스트")
    void print_score(int score, String expected) {
        assertThat(Pins.of(score).toString()).isEqualTo(expected);
    }

    static Stream<Arguments> printScore() {
        return Stream.of(
                arguments(0, "-"),
                arguments(1, "1"),
                arguments(10, "X"));
    }

    @ParameterizedTest
    @MethodSource("scoreForSum")
    @DisplayName("두 개의 Score 객체의 합을 구할 수 있다.")
    void sum(Pins firstPins, Pins secondPins, int expectedSum) {
        assertThat(Pins.sum(firstPins, secondPins)).isEqualTo(expectedSum);
    }

    static Stream<Arguments> scoreForSum() {
        return Stream.of(
                arguments(Pins.of(1), Pins.of(2), 3),
                arguments(Pins.of(2), Pins.of(3), 5),
                arguments(Pins.of(0), Pins.of(0), 0));
    }
}