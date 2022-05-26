package bowling.domain.pitch;

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
    void exception(int firstCount, int secondCount) {
        assertThatThrownBy(() -> Pitch.of(firstCount).next(secondCount)).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "첫 번째 투구 결과 - {0}개 쓰러짐")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void first(int count) {
        assertThat(Pitch.of(count)).isEqualTo(Pitch.of(count));
    }


    @ParameterizedTest(name = "두 번째 투구 결과 = {0}, {1}개 쓰러짐")
    @MethodSource("secondCondition")
    void second(int firstCount, int secondCount) {
        int expected = Math.addExact(firstCount, secondCount);
        Pitch first = Pitch.of(firstCount);
        Pitch second = first.next(secondCount);
        assertThat(Math.addExact(first.count(), second.count())).isEqualTo(expected);
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
