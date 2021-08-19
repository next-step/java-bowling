package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.pitch.Pitch;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("프레임 결과")
class FrameResultTest {

    public static Stream<Arguments> normalFrames() {
        return Stream.of(
            Arguments.of(NormalFrame.of(Pitch.of(10)), FrameResult.STRIKE.getFlag()),
            Arguments.of(NormalFrame.of(Pitch.of(5), Pitch.of(5)), "5|/"),
            Arguments.of(NormalFrame.of(Pitch.of(5), Pitch.of(3)), "5|3"),
            Arguments.of(NormalFrame.of(Pitch.of(0), Pitch.of(0)), "-|-")
        );
    }

    @DisplayName("[성공] 일반 프레임 결과 가져오기")
    @ParameterizedTest
    @MethodSource("normalFrames")
    public void get_normalFrame(final NormalFrame frame, final String expected) {
        // given

        // when
        final String result = FrameResult.get(frame);

        // then
        assertThat(result).isEqualTo(expected);
    }

    public static Stream<Arguments> lastFrames() {
        return Stream.of(
            Arguments.of(LastFrame.of(Pitch.of(10), Pitch.of(10), Pitch.of(10)), "X|X|X"),
            Arguments.of(LastFrame.of(Pitch.of(5), Pitch.of(5), Pitch.of(5)), "5|/|5"),
            Arguments.of(LastFrame.of(Pitch.of(5), Pitch.of(3)), "5|3"),
            Arguments.of(LastFrame.of(Pitch.of(0), Pitch.of(0)), "-|-")
        );
    }

    @Disabled
    @DisplayName("[성공] 마지막 프레임 결과 가져오기")
    @ParameterizedTest
    @MethodSource("lastFrames")
    public void get_lastFrame(final LastFrame frame, final String expected) {
        // given

        // when
        final String result = FrameResult.get(frame);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
