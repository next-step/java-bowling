package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.frame.FrameNumber;

class BowlingLineTest {

    @ParameterizedTest
    @MethodSource("pitchingFrameSource")
    public void pitching_available_frame_test(FrameNumber frame, int score1, boolean expectAvailable) {
        Bowling bowling = new Bowling(new User("abc"));
        BowlingLine bowlingLine = new BowlingLine(bowling, (pin) -> score1);
        bowlingLine.pitching(frame);

        assertThat(bowlingLine.availablePitching(frame)).isEqualTo(expectAvailable);
    }

    static Stream<Arguments> pitchingFrameSource() {
        return Stream.of(
                arguments(FrameNumber.FRAME_5, true),
                arguments(FrameNumber.FRAME_10, false),
                arguments(FrameNumber.FRAME_10, true));
    }
}
