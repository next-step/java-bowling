package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FinalScoresTest {

    @ParameterizedTest
    @MethodSource("scoreSource")
    public void remain_pin_calc_test(int score1, int remain1) {
        FinalScores finalScores = new FinalScores();
        finalScores.recordingScore(new Score(score1));
        assertThat(finalScores.remainPin()).isEqualTo(remain1);
    }

    @ParameterizedTest
    @MethodSource("bonusScoreSource")
    public void bonus_stage_remain_pin_test(int score1, int score2, int score3, int remain) {
        FinalScores finalScores = new FinalScores();
        finalScores.recordingScore(new Score(score1));
        finalScores.recordingScore(new Score(score2));
        finalScores.recordingScore(new Score(score3));

        assertThat(finalScores.remainPin()).isEqualTo(remain);
    }

    static Stream<Arguments> bonusScoreSource() {
        return Stream.of(
                arguments(9, 1, 10, 0),
                arguments(0, 10, 2, 8));
    }

    static Stream<Arguments> scoreSource() {
        return Stream.of(
                arguments(9, 1),
                arguments(0, 10),
                arguments(2, 8));
    }
}
