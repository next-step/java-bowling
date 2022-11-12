package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FinalFrameTest {

    @ParameterizedTest
    @MethodSource("availablePitchingSource")
    public void available_pitching_test(int firstScore, int secondScore, boolean expect1, boolean expect2) {
        FinalFrame finalFrame = new FinalFrame(new FinalScores());
        finalFrame.pitch((pin) -> firstScore);
        assertThat(finalFrame.availablePitching()).isEqualTo(expect1);

        finalFrame.pitch((pin) -> secondScore);
        assertThat(finalFrame.availablePitching()).isEqualTo(expect2);
    }

    @ParameterizedTest
    @MethodSource("pitchingScoreMarkSource")
    public void final_frame_score_mark_test(int pitching1, int pitching2, int pitching3, String expectMark) {
        FinalFrame finalFrame = new FinalFrame(new FinalScores());
        finalFrame.pitch((pin) -> pitching1);
        finalFrame.pitch((pin) -> pitching2);
        finalFrame.pitch((pin) -> pitching3);

        assertThat(finalFrame.score()).isEqualTo(expectMark);
    }

    @ParameterizedTest
    @MethodSource("pitchingScoreSource")
    public void wrong_score_record(int pitching1, int pitching2) {
        FinalFrame frame = new FinalFrame(new FinalScores());
        frame.pitch((pin) -> pitching1);
        assertThatThrownBy(() -> frame.pitch((pin) -> pitching2)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> pitchingScoreSource() {
        return Stream.of(arguments(5, 8),
                         arguments(5, 9));
    }

    static Stream<Arguments> pitchingScoreMarkSource() {
        return Stream.of(arguments(5, 5, 1, "5|/|1"),
                         arguments(10, 2, 0, "X|2"),
                         arguments(0, 10, 1, "-|/|1"),
                         arguments(0, 0, 0, "-|-"),
                         arguments(5, 0, 0, "5|-"));
    }

    static Stream<Arguments> availablePitchingSource() {
        return Stream.of(arguments(1, 2, true, false),
                         arguments(10, 0, true, false),
                         arguments(1, 9, true, true));
    }
}
