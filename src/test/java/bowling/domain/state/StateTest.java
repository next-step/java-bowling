package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("State: 프레임 상태를 나타내는 클래스 테스트")
class StateTest {

    @DisplayName("각 상태별로 새로 시작인지 유무를 반환한다")
    @ParameterizedTest
    @MethodSource("source_isNew_ReturnIsNew")
    public void isNew_ReturnIsNew(State state, boolean expected) {
        assertThat(state.isNew()).isEqualTo(expected);
    }

    public static Stream<Arguments> source_isNew_ReturnIsNew() {
        return Stream.of(
                Arguments.of(State.ofNew(), true),
                Arguments.of(State.ofSpare(5), false),
                Arguments.of(State.ofFinish(), false));
    }

    @DisplayName("각 상태별로 종료인지 유무를 반환한다")
    @ParameterizedTest
    @MethodSource("source_isFinish_ReturnIsFinish")
    public void isFinish_ReturnIsFinish(State state, boolean expected) {
        assertThat(state.isFinish()).isEqualTo(expected);
    }

    public static Stream<Arguments> source_isFinish_ReturnIsFinish() {
        return Stream.of(
                Arguments.of(State.ofNew(), false),
                Arguments.of(State.ofSpare(5), false),
                Arguments.of(State.ofFinish(), true));
    }
}
