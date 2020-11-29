package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프레임별 점수 테스트")
public class ScoresTest {
    @DisplayName("빈 점수리스트 합")
    @Test
    public void sumEmptyScore() {
        Scores frameScore = Scores.empty();

        assertThat(frameScore.sum()).isEqualTo(0);
    }

    @DisplayName("접수 합")
    @Test
    public void sumScore() {
        Scores frameScore = Scores.of(Arrays.asList(Score.ordinary(1), Score.ordinary(8)));

        assertThat(frameScore.sum()).isEqualTo(9);
    }

    @DisplayName("프레임이 다음 프레임 점수가 필요 없을 때 점수 계산")
    @ParameterizedTest
    @MethodSource("getOrdinaryScoreParams")
    public void calculateOrdinaryScore(Integer previousScore, List<Score> nextScores, Integer expectedScore) {
        Scores scores = Scores.of(Arrays.asList(Score.ordinary(3), Score.ordinary(4)));
        assertThat(scores.calculate(previousScore, nextScores)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getOrdinaryScoreParams() {
        return Stream.of(
                Arguments.arguments(10, Collections.emptyList(), 17),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.spare(9)), 17)
        );
    }

    @DisplayName("프레임이 스페어일 때 점수 계산")
    @ParameterizedTest
    @MethodSource("getSpareScoreParam")
    public void calculateSpareScore(Integer previousScore, List<Score> nextScores, Integer expectedScore) {
        Scores scores = Scores.of(Arrays.asList(Score.ordinary(3), Score.spare(7)));
        assertThat(scores.calculate(previousScore, nextScores)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getSpareScoreParam() {
        return Stream.of(
                Arguments.arguments(10, Collections.emptyList(), null),
                Arguments.arguments(10, Arrays.asList(Score.gutter(), Score.spare(9)), 20),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.ordinary(4)), 21),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.spare(9)), 21),
                Arguments.arguments(10, Arrays.asList(Score.strike(), Score.ordinary(9), Score.spare(1)), 30),
                Arguments.arguments(10, Arrays.asList(Score.strike(), Score.strike(), Score.strike()), 30)
        );
    }

    @DisplayName("프레임이 스트라이크일 때 점수 계산")
    @ParameterizedTest
    @MethodSource("getStrikeScoreParam")
    public void calculateStrikeScore(Integer previousScore, List<Score> nextScores, Integer expectedScore) {
        Scores scores = Scores.of(Collections.singletonList(Score.strike()));
        assertThat(scores.calculate(previousScore, nextScores)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getStrikeScoreParam() {
        return Stream.of(
                Arguments.arguments(10, Collections.emptyList(), null),
                Arguments.arguments(10, Collections.singletonList(Score.ordinary(1)), null),
                Arguments.arguments(10, Collections.singletonList(Score.gutter()), null),
                Arguments.arguments(10, Collections.singletonList(Score.strike()), null),
                Arguments.arguments(10, Arrays.asList(Score.gutter(), Score.spare(9)), 29),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.ordinary(4)), 25),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.spare(9)), 30),
                Arguments.arguments(10, Arrays.asList(Score.strike(), Score.ordinary(9), Score.spare(1)), 39),
                Arguments.arguments(10, Arrays.asList(Score.strike(), Score.strike(), Score.strike()), 40)
        );
    }


}