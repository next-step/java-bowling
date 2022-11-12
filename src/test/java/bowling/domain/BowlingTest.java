package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BowlingTest {

    @Test
    void pitching_score_init_frame_test() {
        Bowling bowling = new Bowling(new User("a"));
        int score = bowling.pitching(FrameNumber.FRAME_1, (pin) -> 5);

        assertThat(score).isEqualTo(5);
    }

    @ParameterizedTest
    @MethodSource("pitchingScore")
    void pitching_score_second_frame_test(int firstScore, int expectSecondScore) {
        Bowling bowling = new Bowling(new User("a"));
        bowling.pitching(FrameNumber.FRAME_1, (pin) -> firstScore);
        int score = bowling.pitching(FrameNumber.FRAME_1, (pin) -> pin);

        assertThat(score).isEqualTo(expectSecondScore);
    }

    @ParameterizedTest
    @MethodSource("pitchingScoreMarkSource")
    void score_mark_test(FrameNumber frameNumber, int firstScore, int secondScore, String expectMark1) {
        Bowling bowling = new Bowling(new User("a"));
        bowling.pitching(frameNumber, (pin) -> firstScore);
        bowling.pitching(frameNumber, (pin) -> secondScore);
        String mark = bowling.score(frameNumber);

        assertThat(mark).isEqualTo(expectMark1);
    }

    static Stream<Arguments> pitchingScore() {
        return Stream.of(arguments(5, 5),
                         arguments(10, 0),
                         arguments(0, 10),
                         arguments(0, 10));
    }

    static Stream<Arguments> pitchingScoreMarkSource() {
        return Stream.of(arguments(FrameNumber.FRAME_1, 5, 5, "5|/"),
                         arguments(FrameNumber.FRAME_1, 10, 0, "X"),
                         arguments(FrameNumber.FRAME_1, 0, 10, "-|/"),
                         arguments(FrameNumber.FRAME_1, 0, 0, "-|-"),
                         arguments(FrameNumber.FRAME_1, 5, 0, "5|-"));
    }
}
