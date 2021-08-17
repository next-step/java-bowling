package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("프레임 결과")
class FrameResultTest {

    public static Stream<Arguments> frameResults() {
        return Stream.of(
            Arguments.of(Frame.of(PitchResult.of(10)), FrameResult.STRIKE.getFlag()),
            Arguments.of(Frame.of(PitchResult.of(5), PitchResult.of(5)), "5|/"),
            Arguments.of(Frame.of(PitchResult.of(5), PitchResult.of(3)), "5|3"),
            Arguments.of(Frame.of(PitchResult.of(0), PitchResult.of(0)), "-|-")
        );
    }

    @DisplayName("[성공] 가져오기")
    @ParameterizedTest
    @MethodSource("frameResults")
    public void get(final Frame frame, final String expected) {
        // given

        // when
        final String string = FrameResult.get(frame);

        // then
        assertThat(string).isEqualTo(expected);
    }
}
