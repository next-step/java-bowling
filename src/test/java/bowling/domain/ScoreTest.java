package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Score: 각 프레임 별 점수")
class ScoreTest {

    @DisplayName("넘긴 핀 수에 따라 남은 핀수가 감소한다")
    @ParameterizedTest
    @MethodSource("source_add_DecreaseRemain")
    public void add_DecreaseRemain(Pin pin, int expected) {
        Score score = new Score();
        score.add(pin);
        assertThat(score.getRemain()).isEqualTo(expected);
    }

    public static Stream<Arguments> source_add_DecreaseRemain() {
        return Stream.of(
                Arguments.of(new Pin(1), 9),
                Arguments.of(new Pin(5), 5),
                Arguments.of(new Pin(9), 1),
                Arguments.of(new Pin(10), 0));
    }
}
