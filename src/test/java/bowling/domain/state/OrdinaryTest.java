package bowling.domain.state;

import bowling.domain.frame.InvalidFrameRecordActionException;
import bowling.domain.score.Score;
import bowling.domain.state.last.LastGutter;
import bowling.domain.state.last.LastOrdinary;
import bowling.domain.state.last.LastSpare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("일반 상태 테스트")
public class OrdinaryTest {
    @DisplayName("다음이 마지막 상태가 될 때")
    @ParameterizedTest
    @MethodSource("getRecordForLast")
    public void nextLast(int pins, Class<State> expectedClass) {
        State state = new Ordinary(7, 1);

        State nextState = state.record(pins);

        assertThat(nextState).isInstanceOf(expectedClass);
    }

    private static Stream<Arguments> getRecordForLast() {
        return Stream.of(
                Arguments.arguments(7, LastOrdinary.class),
                Arguments.arguments(0, LastGutter.class),
                Arguments.arguments(3, LastSpare.class)
        );
    }

    @DisplayName("다음 상태")
    @ParameterizedTest
    @MethodSource("getRecord")
    public void next(int pins, Class<State> expectedClass) {
        State state = new Ordinary(7, 2);

        State nextState = state.record(pins);

        assertThat(nextState).isInstanceOf(expectedClass);
    }

    private static Stream<Arguments> getRecord() {
        return Stream.of(
                Arguments.arguments(7, LastOrdinary.class),
                Arguments.arguments(0, LastGutter.class),
                Arguments.arguments(3, Spare.class)
        );
    }

    @DisplayName("잘못된 상태")
    @Test
    public void invalidState() {
        State state = new Ordinary(7, 2);

        assertThatThrownBy(() -> {
            state.record(10);
        }).isInstanceOf(InvalidFrameRecordActionException.class);
    }

    @DisplayName("점수")
    @Test
    public void score() {
        State state = new Ordinary(1);

        assertThat(state.getScore()).isEqualTo(Score.ordinary(1));
    }

    @DisplayName("종료 여부")
    @Test
    public void isFinished() {
        State state = new Ordinary(1);

        assertThat(state.isFinished()).isEqualTo(false);
    }
}