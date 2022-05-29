package bowling.domain.pitch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchesTest {

    @ParameterizedTest(name = "현재 프레임의 첫 번째 투구 결과")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void first(int pins) {
        assertThat(Pitches.first(pins).totalPins()).isEqualTo(pins);
    }

    @ParameterizedTest(name = "현재 프레임의 모든 투구 결과")
    @MethodSource("totalPinsCondition")
    void totalPins(int firstPins, int secondPins) {
        int expected = Math.addExact(firstPins, secondPins);
        assertThat(Pitches.first(firstPins).next(secondPins).totalPins()).isEqualTo(expected);
    }

    private static Stream<Arguments> totalPinsCondition() {
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
