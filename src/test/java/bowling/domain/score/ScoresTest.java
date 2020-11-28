package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
                Arguments.arguments(Scores.of(Arrays.asList(Score.ordinary(2), Score.spare(8))), 10, true, 3, Score.strike())
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

    @DisplayName("프레임에 점수가 없을 때 점수 계산")
    @ParameterizedTest
    @MethodSource("getEmptyScoreParams")
    public void calculateEmptyScore(Integer previousScore, List<Score> nextScores, boolean isLast, Integer expectedScore) {
        Scores scores = Scores.empty();
        assertThat(scores.calculateScore(previousScore, nextScores, isLast)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getEmptyScoreParams() {
        return Stream.of(
                Arguments.arguments(10, Collections.emptyList(), false, null),
                Arguments.arguments(10, Collections.emptyList(), true, null),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.spare(9)), false, null)
        );
    }

    @DisplayName("프레임이 끝나지 않았을 때 점수 계산")
    @ParameterizedTest
    @MethodSource("getNotFinishedParam")
    public void calculateNotFinishedScore(Integer previousScore, List<Score> nextScores, boolean isLast, Integer expectedScore) {
        Scores scores = Scores.of(Collections.singletonList(Score.ordinary(3)));
        assertThat(scores.calculateScore(previousScore, nextScores, isLast)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getNotFinishedParam() {
        return Stream.of(
                Arguments.arguments(10, Collections.emptyList(), false, null),
                Arguments.arguments(10, Collections.emptyList(), true, null),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.spare(9)), false, null)
        );
    }

    @DisplayName("프레임이 다음 프레임 점수가 필요 없을 때 점수 계산")
    @ParameterizedTest
    @MethodSource("getOrdinaryScoreParams")
    public void calculateOrdinaryScore(Integer previousScore, List<Score> nextScores, boolean isLast, Integer expectedScore) {
        Scores scores = Scores.of(Arrays.asList(Score.ordinary(3), Score.ordinary(4)));
        assertThat(scores.calculateScore(previousScore, nextScores, isLast)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getOrdinaryScoreParams() {
        return Stream.of(
                Arguments.arguments(10, Collections.emptyList(), false, 17),
                Arguments.arguments(10, Collections.emptyList(), true, 17),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.spare(9)), false, 17)
        );
    }

    @DisplayName("프레임이 스페어일 때 점수 계산")
    @ParameterizedTest
    @MethodSource("getSpareScoreParam")
    public void calculateSpareScore(Integer previousScore, List<Score> nextScores, boolean isLast, Integer expectedScore) {
        Scores scores = Scores.of(Arrays.asList(Score.ordinary(3), Score.spare(7)));
        assertThat(scores.calculateScore(previousScore, nextScores, isLast)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getSpareScoreParam() {
        return Stream.of(
                Arguments.arguments(10, Collections.emptyList(), false, null),
                Arguments.arguments(10, Collections.emptyList(), true, null),
                Arguments.arguments(10, Arrays.asList(Score.gutter(), Score.spare(9)), false, 20),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.ordinary(4)), false, 21),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.spare(9)), false, 21),
                Arguments.arguments(10, Arrays.asList(Score.strike(), Score.ordinary(9), Score.spare(1)), false, 30),
                Arguments.arguments(10, Arrays.asList(Score.strike(), Score.strike(), Score.strike()), false, 30)
        );
    }

    @DisplayName("프레임이 스트라이크일 때 점수 계산")
    @ParameterizedTest
    @MethodSource("getStrikeScoreParam")
    public void calculateStrikeScore(Integer previousScore, List<Score> nextScores, boolean isLast, Integer expectedScore) {
        Scores scores = Scores.of(Collections.singletonList(Score.strike()));
        assertThat(scores.calculateScore(previousScore, nextScores, isLast)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getStrikeScoreParam() {
        return Stream.of(
                Arguments.arguments(10, Collections.emptyList(), false, null),
                Arguments.arguments(10, Collections.emptyList(), true, null),
                Arguments.arguments(10, Collections.singletonList(Score.ordinary(1)), false, null),
                Arguments.arguments(10, Collections.singletonList(Score.gutter()), false, null),
                Arguments.arguments(10, Collections.singletonList(Score.strike()), false, null),
                Arguments.arguments(10, Arrays.asList(Score.gutter(), Score.spare(9)), false, 29),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.ordinary(4)), false, 25),
                Arguments.arguments(10, Arrays.asList(Score.ordinary(1), Score.spare(9)), false, 30),
                Arguments.arguments(10, Arrays.asList(Score.strike(), Score.ordinary(9), Score.spare(1)), false, 39),
                Arguments.arguments(10, Arrays.asList(Score.strike(), Score.strike(), Score.strike()), false, 40)
        );
    }

    @DisplayName("마지막 프레임 점수 계산")
    @ParameterizedTest
    @MethodSource("getLastScoreParam")
    public void calculateStrikeScore(Scores lastScores, Integer previousScore, List<Score> nextScores, Integer expectedScore) {
        assertThat(lastScores.calculateScore(previousScore, nextScores, true)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getLastScoreParam() {
        return Stream.of(
                Arguments.arguments(Scores.of(Collections.emptyList()), 10, Collections.emptyList(), null),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.ordinary(1))), 10, Collections.emptyList(), null),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.gutter())), 10, Collections.emptyList(), null),
                Arguments.arguments(Scores.of(Collections.singletonList(Score.strike())), 10, Collections.emptyList(), null),
                Arguments.arguments(Scores.of(Arrays.asList(Score.gutter(), Score.spare(9))), 10, Collections.emptyList(), 19),
                Arguments.arguments(Scores.of(Arrays.asList(Score.ordinary(1), Score.ordinary(4))), 10, Collections.emptyList(), 15),
                Arguments.arguments(Scores.of(Arrays.asList(Score.ordinary(1), Score.spare(9))), 10, Collections.emptyList(), null),
                Arguments.arguments(Scores.of(Arrays.asList(Score.ordinary(1), Score.spare(9), Score.ordinary(3))), 10, Collections.emptyList(), 23),
                Arguments.arguments(Scores.of(Arrays.asList(Score.strike(), Score.ordinary(9), Score.spare(1))), 10, Collections.emptyList(), 30),
                Arguments.arguments(Scores.of(Arrays.asList(Score.strike(), Score.strike(), Score.strike())), 10, Collections.emptyList(), 40),
                Arguments.arguments(Scores.of(Arrays.asList(Score.strike(), Score.strike(), Score.strike())), 10, Arrays.asList(Score.ordinary(1), Score.ordinary(4)), 40)
        );
    }

    @DisplayName("일반 프레임 최대 점수 추가")
    @ParameterizedTest
    @ValueSource(ints = {8, 9, 10})
    public void normalFrameMaxScores(int score) {
        assertThatThrownBy(() -> {
            Scores.empty().add(3, false).add(score, false);
        }).isInstanceOf(InvalidMaxScoresException.class);
    }

    @DisplayName("마지막 프레임 최대 점수 추가")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9, 10})
    public void lastFrameMaxScores(int score) {
        assertThatThrownBy(() -> {
            Scores.empty().add(2, true).add(10, true).add(10, true).add(score, true);
        }).isInstanceOf(InvalidScoreAddException.class);
    }
}