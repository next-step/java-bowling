package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프레임별 점수 테스트")
public class ScoresTest {
    @DisplayName("프레임별 첫 점수 생성")
    @Test
    public void createFrameScore() {
        Scores frameScore = Scores.empty().add(10, false);

        assertThat(frameScore.getScore(1)).isEqualTo(Score.strike());
    }

    @DisplayName("짝수번째 시도에서만 스페어 생성")
    @ParameterizedTest
    @MethodSource("getScoresForSpare")
    public void spareScore(Scores scores, int newScore, boolean isLast, int tryCount, Score expectedScore) {
        Scores newScores = scores.add(newScore, isLast);

        assertThat(newScores.getScore(tryCount)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getScoresForSpare() {
        return Stream.of(
                Arguments.arguments(Scores.empty(), 8, false, 1, Score.ordinary(8)),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.ordinary(2))), 8, false, 2, Score.spare(8)),
                Arguments.arguments(Scores.of(Arrays.asList(Score.ordinary(2), Score.spare(8))), 10, false, 3, Score.strike())
        );
    }

    @DisplayName("접수 합")
    @Test
    public void sumScore() {
        Scores frameScore = Scores.empty().add(8, false).add(1, false);

        assertThat(frameScore.sum()).isEqualTo(9);
    }

    @DisplayName("빈 점수리스트 합")
    @Test
    public void sumEmptyScore() {
        Scores frameScore = Scores.empty();

        assertThat(frameScore.sum()).isEqualTo(0);
    }

    @DisplayName("해당 프레임에서 더 점수를 추가할 수 있는지 확인")
    @ParameterizedTest
    @MethodSource("getScores")
    public void isFinished(Scores scores, boolean isFinished) {
        assertThat(scores.isFinished(false)).isEqualTo(isFinished);
    }

    private static Stream<Arguments> getScores() {
        return Stream.of(Arguments.arguments(Scores.empty(), false),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.strike())), true),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.ordinary(9))), false),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.gutter())), false),
                Arguments.arguments(Scores.of(Arrays.asList(Score.gutter(), Score.gutter())), true),
                Arguments.arguments(Scores.of(Arrays.asList(Score.gutter(), Score.ordinary(1))), true),
                Arguments.arguments(Scores.of(Arrays.asList(Score.gutter(), Score.spare(10))), true)
        );
    }

    @DisplayName("마지막 프레임에서 더 점수를 추가할 수 있는지 확인")
    @ParameterizedTest
    @MethodSource("getScoresAtLast")
    public void isFinishedAtLast(Scores scores, boolean isFinished) {
        assertThat(scores.isFinished(true)).isEqualTo(isFinished);
    }

    private static Stream<Arguments> getScoresAtLast() {
        return Stream.of(Arguments.arguments(Scores.empty(), false),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.strike())), false),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.ordinary(9))), false),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.gutter())), false),
                Arguments.arguments(Scores.of(Arrays.asList(Score.ordinary(7), Score.ordinary(2))), true),
                Arguments.arguments(Scores.of(Arrays.asList(Score.gutter(), Score.spare(10))), false),
                Arguments.arguments(Scores.of(Arrays.asList(Score.strike(), Score.strike())), false),
                Arguments.arguments(Scores.of(Arrays.asList(Score.gutter(), Score.gutter())), true),
                Arguments.arguments(Scores.of(Arrays.asList(Score.gutter(), Score.ordinary(1))), true),
                Arguments.arguments(Scores.of(Arrays.asList(Score.strike(), Score.strike(), Score.strike())), true),
                Arguments.arguments(Scores.of(Arrays.asList(Score.strike(), Score.strike(), Score.ordinary(10))), true),
                Arguments.arguments(Scores.of(Arrays.asList(Score.gutter(), Score.spare(10), Score.strike())), true)
        );
    }
}