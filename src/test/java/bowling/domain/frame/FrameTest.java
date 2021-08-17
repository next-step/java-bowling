package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.frame.exception.PitchResultAddException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("프레임")
class FrameTest {

    public static Stream<Arguments> pitchResults() {
        return Stream.of(
            Arguments.of(PitchResult.of(0), PitchResult.of(10)),
            Arguments.of(PitchResult.of(1), PitchResult.of(9)),
            Arguments.of(PitchResult.of(9), PitchResult.of(1)),
            Arguments.of(PitchResult.of(10), PitchResult.of(0))
        );
    }

    @DisplayName("[성공] 생성")
    @ParameterizedTest
    @MethodSource("pitchResults")
    public void create(final PitchResult first, final PitchResult second) {
        // given
        final Frame frame = Frame.of();

        // when
        frame.pitch(first);
        frame.pitch(second);

        // then
        assertThat(frame).isNotNull();
    }

    public static Stream<Arguments> invalidPitchResults() {
        return Stream.of(
            Arguments.of(PitchResult.of(1), PitchResult.of(10)),
            Arguments.of(PitchResult.of(5), PitchResult.of(9)),
            Arguments.of(PitchResult.of(10), PitchResult.of(1))
        );
    }

    @DisplayName("[실패] 생성 - 유효하지 않은 투구 결과")
    @ParameterizedTest
    @MethodSource("invalidPitchResults")
    public void create_invalidPitchResult(final PitchResult first, final PitchResult second) {
        // given
        final Frame frame = Frame.of();
        frame.pitch(first);

        // when
        assertThrows(PitchResultAddException.class, () -> frame.pitch(second));

        // then
    }

    public static Stream<Arguments> isEnd() {
        final Frame endFrame = Frame.of(PitchResult.of(5));
        endFrame.pitch(PitchResult.of(5));
        return Stream.of(
            Arguments.of(Frame.of(PitchResult.of(1)), false),
            Arguments.of(Frame.of(PitchResult.of(10)), true),
            Arguments.of(endFrame, true)
        );
    }

    @DisplayName("[실패] 완료된 프레임")
    @ParameterizedTest
    @MethodSource("isEnd")
    public void isEnd(final Frame frame, final boolean expected) {
        // given

        // when
        assertThat(frame.isEnd()).isEqualTo(expected);

        // then
    }
}
