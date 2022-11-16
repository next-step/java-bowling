package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Scores;

class NormalFrameTest {
    @ParameterizedTest
    @MethodSource("availablePitchingSource")
    public void available_pitching_test(int score, boolean expect) {
        NormalFrame frame = new NormalFrame(new Scores());
        frame.pitch((pin) -> score);

        assertThat(frame.availablePitching()).isEqualTo(expect);
    }

    @ParameterizedTest
    @MethodSource("pitchingScoreMarkSource")
    public void final_frame_score_mark_test(int pitching1, int pitching2, String expectMark) {
        NormalFrame frame = new NormalFrame(new Scores());
        frame.pitch((pin) -> pitching1);
        frame.pitch((pin) -> pitching2);

        assertThat(frame.score()).isEqualTo(expectMark);
    }

    @ParameterizedTest
    @MethodSource("pitchingScoreSource")
    public void wrong_score_record(int pitching1, int pitching2) {
        NormalFrame frame = new NormalFrame(new Scores());
        frame.pitch((pin) -> pitching1);
        assertThatThrownBy(() -> frame.pitch((pin) -> pitching2)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> pitchingScoreSource() {
        return Stream.of(arguments(5, 6),
                         arguments(10, 1),
                         arguments(5, 9));
    }

    static Stream<Arguments> pitchingScoreMarkSource() {
        return Stream.of(arguments(5, 5, "5|/"),
                         arguments(10, 0, "X"),
                         arguments(0, 10, "-|/"),
                         arguments(0, 0, "-|-"),
                         arguments(5, 0, "5|-"));
    }

    static Stream<Arguments> availablePitchingSource() {
        return Stream.of(arguments(1, true),
                         arguments(10, false),
                         arguments(0, true));
    }
}
