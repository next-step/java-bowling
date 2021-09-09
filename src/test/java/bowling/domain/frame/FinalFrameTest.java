package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.exception.FrameNotCorrectException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("FinalFrame Test")
class FinalFrameTest {

    static Stream<Arguments> provideFinalCheckFrame() {
        return Stream.of(
                Arguments.of(new int[]{10, 10, 10}, true),
                Arguments.of(new int[]{10, 5 ,4}, true),
                Arguments.of(new int[]{9, 1, 10}, true),
                Arguments.of(new int[]{10, 10}, false),
                Arguments.of(new int[]{10, 0}, false),
                Arguments.of(new int[]{9, 1}, false),
                Arguments.of(new int[]{5, 4}, true),
                Arguments.of(new int[]{0, 0}, true),
                Arguments.of(new int[]{0, 9}, true),
                Arguments.of(new int[]{0}, false),
                Arguments.of(new int[]{1}, false)
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("provideFinalCheckFrame")
    @DisplayName("완료된 프레임인지 확인한다.")
    void finalFrameCheck(int[] numbers, boolean actual) {
        // given
        Frame frame = FinalFrame.of(numbers);

        // when
        boolean finished = frame.isFinished();

        // then
        assertThat(finished).isEqualTo(actual);
    }

    static Stream<Arguments> provideFinalFrameException() {
        return Stream.of(
                Arguments.of(Pins.of(9, 2)),
                Arguments.of(Pins.of(10, 2, 9))

        );
    }

    @ParameterizedTest
    @MethodSource("provideFinalFrameException")
    @DisplayName("마지막 프레임 Exception")
    void finalFrameException(Pins pins) {
        // given
        // when
        // then
        assertThatThrownBy(() ->
                FinalFrame.of(pins)
        ).isInstanceOf(FrameNotCorrectException.class);
    }
}