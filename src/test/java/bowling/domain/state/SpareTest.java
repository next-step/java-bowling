package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.all.Spare;
import bowling.domain.state.all.Strike;
import bowling.domain.state.rest.Gutter;
import bowling.domain.state.rest.Ordinary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스페어 상태 테스트")
public class SpareTest {
    State state = new Spare(1, Scores.of(Arrays.asList(Score.ordinary(3), Score.spare(7))));

    @DisplayName("다음 상태")
    @ParameterizedTest
    @MethodSource("getRecordForLast")
    public void nextLast(int pins, Class<State> expectedClass) {
        State nextState = state.record(Pin.of(pins));

        assertThat(nextState).isInstanceOf(expectedClass);
    }

    private static Stream<Arguments> getRecordForLast() {
        return Stream.of(
                Arguments.arguments(10, Strike.class),
                Arguments.arguments(0, Gutter.class),
                Arguments.arguments(3, Ordinary.class)
        );
    }

    @DisplayName("종료 여부")
    @Test
    public void isFinished() {
        assertThat(state.isFinished()).isEqualTo(false);
    }
}