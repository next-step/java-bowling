package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScoresTest {

    @ParameterizedTest
    @MethodSource("scoreSource")
    public void remain_pin_calc_test(int score1, int remain1, int score2, int remain2) {
        Scores scores = new Scores();
        scores.recordingScore(new Score(score1));
        assertThat(scores.remainPin()).isEqualTo(remain1);

        scores.recordingScore(new Score(score2));
        assertThat(scores.remainPin()).isEqualTo(remain2);
    }

    static Stream<Arguments> scoreSource() {
        return Stream.of(
                arguments(9, 1, 1, 0),
                arguments(0, 10, 10, 0),
                arguments(2, 8, 5, 3));
    }
}
