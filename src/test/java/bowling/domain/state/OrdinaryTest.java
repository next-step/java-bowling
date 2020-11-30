package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("일반 상태 테스트")
public class OrdinaryTest {
    State state = new Ordinary(Pin.of(7), 2, Scores.of(Collections.singletonList(Score.ordinary(7))));

    @DisplayName("다음 상태")
    @ParameterizedTest
    @MethodSource("getRecord")
    public void next(int pins, Class<State> expectedClass) {

        State nextState = state.record(Pin.of(pins));

        assertThat(nextState).isInstanceOf(expectedClass);
    }

    private static Stream<Arguments> getRecord() {
        return Stream.of(
                Arguments.arguments(7, Ordinary.class),
                Arguments.arguments(0, Gutter.class),
                Arguments.arguments(3, Spare.class)
        );
    }

    @DisplayName("잘못된 상태")
    @Test
    public void invalidState() {
        assertThatThrownBy(() -> {
            state.record(Pin.of(10));
        }).isInstanceOf(InvalidRecordInOrdinaryException.class);
    }

    @DisplayName("종료 여부")
    @Test
    public void isFinished() {
        assertThat(state.isFinished()).isEqualTo(false);
    }
}