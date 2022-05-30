package bowling.domain.pitch;

import bowling.domain.pitch.exception.OverPinsException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PitchTest {

    @ParameterizedTest(name = "쓰러트린 볼링 핀이 0개 미만인 경우 예외 처리 - {0}개, {1}개")
    @CsvSource(delimiter = '|', value = {"-1|0", "0|-11", "-1|9", "9|-1"})
    void exception(int firstPins, int secondPins) {
        assertThatThrownBy(() -> Pitch.of(firstPins).next(secondPins)).isExactlyInstanceOf(OverPinsException.class);
    }

    @ParameterizedTest(name = "첫 번째 투구 결과 - {0}개 쓰러짐")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void first(int pins) {
        assertThat(Pitch.of(pins)).isEqualTo(Pitch.of(pins));
    }


    @ParameterizedTest(name = "두 번째 투구 결과 = {0}, {1}개 쓰러짐")
    @MethodSource("secondCondition")
    void second(int firstPins, int secondPins) {
        int expected = Math.addExact(firstPins, secondPins);
        Pitch first = Pitch.of(firstPins);
        Pitch second = first.next(secondPins);
        assertThat(Math.addExact(first.pins(), second.pins())).isEqualTo(expected);
    }

    private static Stream<Arguments> secondCondition() {
        return Stream.of(
                Arguments.of(0, 10),
                Arguments.of(1, 9),
                Arguments.of(2, 8),
                Arguments.of(3, 7),
                Arguments.of(4, 6),
                Arguments.of(5, 5)
        );
    }
}
