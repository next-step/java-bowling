package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("State: 프레임 종료 여부 테스트")
class FrameStateTest {

    @DisplayName("각 상태별로 더 던질 수 있는지 유무를 반환한다")
    @ParameterizedTest
    @MethodSource("source_isFinish_ReturnFinish")
    public void isFinish_ReturnFinish(FrameState frameState, boolean expected) {
        assertThat(frameState.isFinish()).isEqualTo(expected);
    }

    public static Stream<Arguments> source_isFinish_ReturnFinish() {
        return Stream.of(
                Arguments.of(FrameState.ofNew(), false),
                Arguments.of(FrameState.ofNotFinish(5), false),
                Arguments.of(FrameState.ofFinish(), true),
                Arguments.of(FrameState.ofNotFinish(10), false));
    }
}
