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
    void first(int count) {
        assertThat(Pitches.first(count).totalCount()).isEqualTo(count);
    }

    @ParameterizedTest(name = "현재 프레임의 모든 투구 결과")
    @MethodSource("totalCountCondition")
    void totalCount(int firstCount, int secondCount) {
        int expected = Math.addExact(firstCount, secondCount);
        assertThat(Pitches.first(firstCount).next(secondCount).totalCount()).isEqualTo(expected);
    }

    private static Stream<Arguments> totalCountCondition() {
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
