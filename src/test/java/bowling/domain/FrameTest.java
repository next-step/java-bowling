package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.exception.PitchResultAddException;
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

    @DisplayName("[생성]")
    @ParameterizedTest
    @MethodSource("pitchResults")
    public void create(final PitchResult first, final PitchResult second) {
        // given

        // when
        final Frame frame = Frame.of(first).second(second);

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

    @DisplayName("[생성] - 유효하지 않은 투구 결과")
    @ParameterizedTest
    @MethodSource("invalidPitchResults")
    public void create_invalidPitchResult(final PitchResult first, final PitchResult second) {
        // given

        // when
        assertThrows(PitchResultAddException.class, () -> Frame.of(first).second(second));

        // then
    }
}
