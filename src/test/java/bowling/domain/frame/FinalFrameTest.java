package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.exception.FrameNotCorrectException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
                Arguments.of(new int[]{10, 5, 4}, true),
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
    void finalFrameCheck(int[] pins, boolean expected) {
        // given
        Frame frame = FinalFrame.of(pins);

        // when
        boolean finished = frame.isFinished();

        // then
        assertThat(finished).isEqualTo(expected);
    }

    static Stream<Arguments> provideFinalFrameException() {
        return Stream.of(
                Arguments.of(Pins.of(9, 2)),
                Arguments.of(Pins.of(10, 2, 9))

        );
    }

    @ParameterizedTest
    @MethodSource("provideFinalFrameException")
    @DisplayName("프레임 Exception")
    void finalFrameException(Pins pins) {
        // given
        // when
        // then
        assertThatThrownBy(() ->
                FinalFrame.of(pins)
        ).isInstanceOf(FrameNotCorrectException.class);
    }

    @Test
    @DisplayName("프레임 진행 후 Exception")
    void finalFrameException() {
        assertThatThrownBy(() ->
                FinalFrame.of().bowl(9).bowl(2)
        ).isInstanceOf(FrameNotCorrectException.class);
    }

    @Test
    @DisplayName("프레임 진행 후 완료 확인")
    void bowlFinalFrameFinished() {
        //given
        Frame finalFrame = FinalFrame.of().bowl(10).bowl(10).bowl(5);

        //when
        boolean isFinished = finalFrame.isFinished();

        //then
        assertThat(isFinished).isTrue();
    }

    @Test
    @DisplayName("프레임 진행 후 미완료 확인")
    void bowlFinalFrameNotFinished() {
        //given
        Frame finalFrame = FinalFrame.of().bowl(10).bowl(5);

        //when
        boolean isFinished = finalFrame.isFinished();

        //then
        assertThat(isFinished).isFalse();
    }

    static Stream<Arguments> provideFinalFrameCalculateScore() {
        return Stream.of(
                Arguments.of(Pins.of(10, 10, 10), 30),
                Arguments.of(Pins.of(10, 10, 0), 20),
                Arguments.of(Pins.of(10, 5, 5), 20),
                Arguments.of(Pins.of(5, 5, 7), 17),
                Arguments.of(Pins.of(7, 2), 9),
                Arguments.of(Pins.of(0, 0), 0)
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("provideFinalFrameCalculateScore")
    @DisplayName("마지막 프레임 점수 계산")
    void calculateScore(Pins pins, int expectedScore) {
        //given
        Frame frame = FinalFrame.of(pins);

        //when
        int actualScore = frame.getScore();

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }
}