package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("마지막 프레임 테스트")
public class LastScoresTest {

    @DisplayName("짝수번째 시도에서만 스페어 생성")
    @ParameterizedTest
    @MethodSource("getScoresForSpare")
    public void spareScore(LastScores scores, int newScore, boolean isLast, int tryCount, Score expectedScore) {
        Scores newScores = scores.add(newScore);

        assertThat(newScores.getScore(tryCount)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getScoresForSpare() {
        return Stream.of(
                Arguments.arguments(LastScores.of(Arrays.asList(Score.ordinary(2), Score.spare(8))), 10, true, 3, Score.strike())
        );
    }

    @DisplayName("마지막 프레임에서 더 점수를 추가할 수 있는지 확인")
    @ParameterizedTest
    @MethodSource("getScoresAtLast")
    public void isFinishedAtLast(LastScores scores, boolean isFinished) {
        assertThat(scores.isFinished()).isEqualTo(isFinished);
    }

    private static Stream<Arguments> getScoresAtLast() {
        return Stream.of(Arguments.arguments(LastScores.empty(), false),
                Arguments.arguments(LastScores.of(Collections.singletonList(Score.strike())), false),
                Arguments.arguments(LastScores.of(Collections.singletonList(Score.ordinary(9))), false),
                Arguments.arguments(LastScores.of(Collections.singletonList(Score.gutter())), false),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.ordinary(7), Score.ordinary(2))), true),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.gutter(), Score.spare(10))), false),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.strike(), Score.strike())), false),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.gutter(), Score.gutter())), true),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.gutter(), Score.ordinary(1))), true),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.strike(), Score.strike(), Score.strike())), true),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.strike(), Score.strike(), Score.ordinary(10))), true),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.gutter(), Score.spare(10), Score.strike())), true)
        );
    }

    @DisplayName("마지막 프레임 점수 계산")
    @ParameterizedTest
    @MethodSource("getLastScoreParam")
    public void calculateStrikeScore(LastScores lastScores, Integer previousScore, List<Score> nextScores, Integer expectedScore) {
        assertThat(lastScores.calculateScore(previousScore, nextScores)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getLastScoreParam() {
        return Stream.of(
                Arguments.arguments(LastScores.of(Collections.emptyList()), 10, Collections.emptyList(), null),
                Arguments.arguments(LastScores.of(Collections.singletonList(Score.ordinary(1))), 10, Collections.emptyList(), null),
                Arguments.arguments(LastScores.of(Collections.singletonList(Score.gutter())), 10, Collections.emptyList(), null),
                Arguments.arguments(LastScores.of(Collections.singletonList(Score.strike())), 10, Collections.emptyList(), null),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.gutter(), Score.spare(9))), 10, Collections.emptyList(), 19),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.ordinary(1), Score.ordinary(4))), 10, Collections.emptyList(), 15),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.ordinary(1), Score.spare(9))), 10, Collections.emptyList(), null),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.ordinary(1), Score.spare(9), Score.ordinary(3))), 10, Collections.emptyList(), 23),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.strike(), Score.ordinary(9), Score.spare(1))), 10, Collections.emptyList(), 30),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.strike(), Score.strike(), Score.strike())), 10, Collections.emptyList(), 40),
                Arguments.arguments(LastScores.of(Arrays.asList(Score.strike(), Score.strike(), Score.strike())), 10, Arrays.asList(Score.ordinary(1), Score.ordinary(4)), 40)
        );
    }

    @DisplayName("마지막 프레임 최대 점수 추가")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9, 10})
    public void lastFrameMaxScores(int score) {
        assertThatThrownBy(() -> {
            LastScores.empty().add(2).add(10).add(10).add(score);
        }).isInstanceOf(InvalidScoreAddException.class);
    }
}