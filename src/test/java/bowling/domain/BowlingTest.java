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
        int score = bowling.pitching(Frame.FRAME_1_1, (pin) -> 5);

        assertThat(score).isEqualTo(5);
    }

    @ParameterizedTest
    @MethodSource("pitchingScore")
    void pitching_score_second_frame_test(Frame firstFrame, Frame secondFrame, int firstScore, int expectSecondScore) {
        Bowling bowling = new Bowling(new User("a"));
        bowling.pitching(firstFrame, (pin) -> firstScore);
        int score = bowling.pitching(secondFrame, (pin) -> pin);

        assertThat(score).isEqualTo(expectSecondScore);
    }

    @ParameterizedTest
    @MethodSource("pitchingScoreMarkSource")
    void score_mark_test(Frame firstFrame, Frame secondFrame, int firstScore, int secondScore, String expectMark1,
                         String expectMark2) {
        Bowling bowling = new Bowling(new User("a"));
        bowling.pitching(firstFrame, (pin) -> firstScore);
        String mark1 = bowling.mark(firstFrame);

        bowling.pitching(secondFrame, (pin) -> secondScore);
        String mark2 = bowling.mark(secondFrame);

        assertThat(mark1).isEqualTo(expectMark1);
        assertThat(mark2).isEqualTo(expectMark2);
    }

    static Stream<Arguments> pitchingScore() {
        return Stream.of(arguments(Frame.FRAME_1_1, Frame.FRAME_1_2, 5, 5),
                         arguments(Frame.FRAME_2_1, Frame.FRAME_2_2, 10, 0),
                         arguments(Frame.FRAME_2_1, Frame.FRAME_2_2, 0, 10),
                         arguments(Frame.FRAME_2_1, Frame.FRAME_2_2, 0, 10));
    }

    static Stream<Arguments> pitchingScoreMarkSource() {
        return Stream.of(arguments(Frame.FRAME_1_1, Frame.FRAME_1_2, 5, 5, "5", "/"),
                         arguments(Frame.FRAME_2_1, Frame.FRAME_2_2, 10, 0, "X", "/"),
                         arguments(Frame.FRAME_2_1, Frame.FRAME_2_2, 0, 10, "-", "/"),
                         arguments(Frame.FRAME_2_1, Frame.FRAME_2_2, 0, 0, "-", "-"),
                         arguments(Frame.FRAME_2_1, Frame.FRAME_2_2, 5, 0, "5", "-"));
    }
}
