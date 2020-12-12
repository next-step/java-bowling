package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalScoresTest {

    @DisplayName("초구 시 add 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "3"})
    void add_first_bowl(String firstValue) {
        Scores normalScores = NormalScores.init();
        Score firstScore = Score.of(firstValue);

        normalScores.add(firstScore);

        assertThat(normalScores.hasFirstScore()).isTrue();
        assertThat(normalScores.scores.size()).isEqualTo(1);
    }

    @DisplayName("2구 시 add 테스트")
    @ParameterizedTest
    @CsvSource(value = {"3:5", "5:5", "0:10"}, delimiter = ':')
    void add_second_bowl(String firstValue, String secondValue) {
        Scores normalScores = NormalScores.init();
        Score firstScore = Score.of(firstValue);
        normalScores.add(firstScore);

        Score secondScore = Score.of(secondValue);
        normalScores.add(secondScore);

        assertThat(normalScores.hasFirstScore()).isTrue();
        assertThat(normalScores.scores.size()).isEqualTo(2);
        assertThat(normalScores.scores.get(0)).isEqualTo(firstScore);
        assertThat(normalScores.scores.get(1)).isEqualTo(secondScore);
    }

    @DisplayName("strike 시 투구 가능 테스트")
    @Test
    void canBowl_strike() {
        Scores normalScores = NormalScores.init();
        Score firstScore = ScoreTest.MAX_SCORE;
        normalScores.add(firstScore);

        boolean actual = normalScores.canBowl();

        assertThat(actual).isFalse();
    }

    @DisplayName("2구까지 완료 시, 다음 투구 가능 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1:2", "0:8"}, delimiter = ':')
    void canBowl_isDone(String firstValue, String secondValue) {
        Scores normalScores = NormalScores.init();
        Score firstScore = Score.of(firstValue);
        normalScores.add(firstScore);

        Score secondScore = Score.of(secondValue);
        normalScores.add(secondScore);

        boolean actual = normalScores.canBowl();

        assertThat(actual).isFalse();
    }

    @DisplayName("Scores 전체 점수 합산 결과 확인 테스트")
    @ParameterizedTest
    @MethodSource("makeGetTwoTestScore")
    void getTotalScore(Score firstScore, Score secondScore, Score expectedTotalScore) {
        Scores scores = NormalScores.init();
        scores.add(firstScore);

        if (secondScore != null) {
            scores.add(secondScore);
        }

        Score actualTotalScore = scores.getTotalScore();

        assertThat(actualTotalScore).isEqualTo(expectedTotalScore);
    }

    private static Stream<Arguments> makeGetTwoTestScore() {
        return Stream.of(
                Arguments.of(Score.of("2"), Score.of("3"), Score.of("5")),
                Arguments.of(Score.of("10"), null, Score.of("10")),
                Arguments.of(Score.of("8"), Score.of("2"), Score.of("10"))
        );
    }

}
