package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스페어 상태 테스트")
public class SpareTest {
    State state = new Spare(1, 1, Scores.empty());

    @DisplayName("다음이 마지막 상태가 될 때")
    @ParameterizedTest
    @MethodSource("getRecordForLast")
    public void nextLast(int pins, Class<State> expectedClass) {
        State state = new Spare(pins, 1, Scores.empty());

        State nextState = state.record(pins);

        assertThat(nextState).isInstanceOf(expectedClass);
    }

    private static Stream<Arguments> getRecordForLast() {
        return Stream.of(
                Arguments.arguments(10, Strike.class),
                Arguments.arguments(0, Gutter.class),
                Arguments.arguments(3, Ordinary.class)
        );
    }

    @DisplayName("점수")
    @Test
    public void score() {
        assertThat(state.getScore()).isEqualTo(Score.spare(1));
    }

    @DisplayName("종료 여부")
    @Test
    public void isFinished() {
        assertThat(state.isFinished()).isEqualTo(false);
    }
}