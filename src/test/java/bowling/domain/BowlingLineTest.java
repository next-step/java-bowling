package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BowlingLineTest {

    @ParameterizedTest
    @MethodSource("pitchingFrameSource")
    public void pitching_available_frame_test(Frame firstFrame, Frame secondFrame, int score1,
                                              boolean expectAvailable) {
        Bowling bowling = new Bowling(new User("abc"));
        BowlingLine bowlingLine = new BowlingLine(bowling, (pin) -> score1);
        bowlingLine.pitching(firstFrame);

        assertThat(bowlingLine.availablePitching(secondFrame)).isEqualTo(expectAvailable);
    }

    static Stream<Arguments> pitchingFrameSource() {
        return Stream.of(
                arguments(Frame.FRAME_1_1, Frame.FRAME_1_2, 5, true),
                arguments(Frame.FRAME_2_1, Frame.FRAME_2_2, 10, false),
                arguments(Frame.FRAME_10_2, Frame.FRAME_10_3, 10, true));
    }
}
