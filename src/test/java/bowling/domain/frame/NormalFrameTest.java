package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.exception.PitchResultAddException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("보통 프레임")
class NormalFrameTest {

    public static Stream<Arguments> pitchResults() {
        return Stream.of(
            Arguments.of(Pitch.first(0), Pitch.first(10)),
            Arguments.of(Pitch.first(1), Pitch.first(9)),
            Arguments.of(Pitch.first(9), Pitch.first(1)),
            Arguments.of(Pitch.first(10), Pitch.first(0))
        );
    }

    @DisplayName("[성공] 생성")
    @ParameterizedTest
    @MethodSource("pitchResults")
    public void create(final Pitch first, final Pitch second) {
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
            Arguments.of(Pitch.first(1), Pitch.first(10)),
            Arguments.of(Pitch.first(5), Pitch.first(9)),
            Arguments.of(Pitch.first(10), Pitch.first(1))
        );
    }

    @DisplayName("[실패] 생성 - 유효하지 않은 투구 결과")
    @ParameterizedTest
    @MethodSource("invalidPitchResults")
    public void create_invalidPitchResult(final Pitch first, final Pitch second) {
        // given
        final NormalFrame normalFrame = NormalFrame.of();
        normalFrame.pitch(first);

        // when
        assertThrows(PitchResultAddException.class, () -> normalFrame.pitch(second));

        // then
    }

    public static Stream<Arguments> isEnd() {
        final NormalFrame endNormalFrame = NormalFrame.of(Pitch.first(5));
        endNormalFrame.pitch(Pitch.first(5));
        return Stream.of(
            Arguments.of(NormalFrame.of(Pitch.first(1)), false),
            Arguments.of(NormalFrame.of(Pitch.first(10)), true),
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
