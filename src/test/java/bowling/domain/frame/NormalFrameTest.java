package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.frame.exception.PitchResultAddException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("보통 프레임")
class NormalFrameTest {

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
        final NormalFrame normalFrame = NormalFrame.of();

        // when
        normalFrame.pitch(first);
        normalFrame.pitch(second);

        // then
        assertThat(normalFrame).isNotNull();
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
        final NormalFrame normalFrame = NormalFrame.of();
        normalFrame.pitch(first);

        // when
        assertThrows(PitchResultAddException.class, () -> normalFrame.pitch(second));

        // then
    }

    public static Stream<Arguments> isEnd() {
        final NormalFrame endNormalFrame = NormalFrame.of(PitchResult.of(5));
        endNormalFrame.pitch(PitchResult.of(5));
        return Stream.of(
            Arguments.of(NormalFrame.of(PitchResult.of(1)), false),
            Arguments.of(NormalFrame.of(PitchResult.of(10)), true),
            Arguments.of(endNormalFrame, true)
        );
    }

    @DisplayName("[실패] 완료된 프레임")
    @ParameterizedTest
    @MethodSource("isEnd")
    public void isEnd(final NormalFrame normalFrame, final boolean expected) {
        // given

        // when
        assertThat(normalFrame.isEnd()).isEqualTo(expected);

        // then
    }
}
