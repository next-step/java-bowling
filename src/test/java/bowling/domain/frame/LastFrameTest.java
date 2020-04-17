package bowling.domain.frame;

import bowling.domain.bonusscore.BonusScores;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LastFrameTest {
    private LastFrame lastFrame;
    @BeforeEach
    void setUp() {
        lastFrame = new LastFrame(new BonusScores());
    }

    @DisplayName("마지막 프레임 생성")
    @Test
    void create() {
        assertThatCode(() -> new LastFrame(new BonusScores()));
    }

    @DisplayName("2구 이내 스트라이크 또는 스페어 인 경우 3구 실행")
    @ParameterizedTest
    @MethodSource("points")
    void addPoint(List<Integer> values) {
        assertThatCode(
                () -> {
                    for (Integer value : values) {
                        lastFrame.addScore(value);
                    }
                }
        );
    }

    static Stream<Arguments> points() {
        return Stream.of(
                arguments(Arrays.asList(10, 2, 1)),
                arguments(Arrays.asList(1, 9, 10)),
                arguments(Arrays.asList(4, 6, 10))
        );
    }

    @DisplayName("1구가 스트라이크가 아닌경우 1구와 2구의 합은 10점을 넘을 수 없다.")
    @ParameterizedTest
    @MethodSource("overPoints")
    void addPointFailByPointOver(List<Integer> values) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> {
                    for (Integer value : values) {
                        lastFrame.addScore(value);
                    }
                }
        );
    }

    static Stream<Arguments> overPoints() {
        return Stream.of(
                arguments(Arrays.asList(2, 9)),
                arguments(Arrays.asList(1, 10))
        );
    }

    @DisplayName("볼링공을 던질수 있는 상태")
    @Test
    void isPlayable() {
        lastFrame.addScore(1);

        assertThat(lastFrame.isPlayable()).isTrue();
    }

    @DisplayName("3구째 볼링공을 던질수 있는 상태 - 2구 이내 스트라이크 또는 스페어 인 경우")
    @ParameterizedTest
    @MethodSource("strikeOrSparePoints")
    void isPlayableByStrikteOrSpare(List<Integer> values) {
        for (Integer value : values) {
            lastFrame.addScore(value);
        }

        assertThat(lastFrame.isPlayable()).isTrue();
    }

    static Stream<Arguments> strikeOrSparePoints() {
        return Stream.of(
                arguments(Arrays.asList(10, 2)),
                arguments(Arrays.asList(1, 9)),
                arguments(Arrays.asList(4, 6))
        );
    }

    @DisplayName("마지막 프레임 점수 계산")
    @ParameterizedTest
    @MethodSource("pointResult")
    void calculateFramePoint(List<Integer> values, int totalPoint) {
        lastFrame.addScore(values.get(0));
        lastFrame.addScore(values.get(1));

        assertThat(lastFrame.getTotalPoint(0)).isEqualTo(totalPoint);
    }

    static Stream<Arguments> pointResult() {
        return Stream.of(
                arguments(Arrays.asList(1, 2), 3),
                arguments(Arrays.asList(2, 4), 6),
                arguments(Arrays.asList(4, 5), 9),
                arguments(Arrays.asList(0, 0), 0)
        );
    }

    @DisplayName("마지막 프레임 점수 계산 - 2구 이내 스트라이크 또는 스페어 인 경우")
    @ParameterizedTest
    @MethodSource("strikeOrSparePointResult")
    void calculateSpareOrStrikeFramePoint(List<Integer> values, int totalPoint) {
        lastFrame.addScore(values.get(0));
        lastFrame.addScore(values.get(1));
        lastFrame.addScore(values.get(2));

        assertThat(lastFrame.getTotalPoint(0)).isEqualTo(totalPoint);
    }

    static Stream<Arguments> strikeOrSparePointResult() {
        return Stream.of(
                arguments(Arrays.asList(10, 10, 10), 30),
                arguments(Arrays.asList(10, 9, 1), 20),
                arguments(Arrays.asList(4, 6, 0), 10),
                arguments(Arrays.asList(10, 4, 0), 14)
        );
    }

    @DisplayName("2구 이내 스트라이크, 스페어가 아닐 때 10점을 넘으면 throw Exception")
    @ParameterizedTest
    @MethodSource("invalidPointList")
    void addPointFailByInvalidPoint(List<Integer> values) {
        lastFrame.addScore(values.get(0));

        assertThatIllegalArgumentException().isThrownBy(() -> lastFrame.addScore(values.get(1)));
    }

    static Stream<Arguments> invalidPointList() {
        return Stream.of(
                arguments(Arrays.asList(9, 2)),
                arguments(Arrays.asList(7, 4)),
                arguments(Arrays.asList(4, 8)),
                arguments(Arrays.asList(5, 7))
        );
    }

    @DisplayName("2구 가 스트라이크 아닐 때 20점을 넘으면 throw Exception")
    @ParameterizedTest
    @MethodSource("invalidPointNotStrikeSecondPlay")
    void addPointFailByNotStrikeSecondPlay(List<Integer> values) {
        lastFrame.addScore(values.get(0));
        lastFrame.addScore(values.get(1));

        assertThatIllegalArgumentException().isThrownBy(() -> lastFrame.addScore(values.get(2)));
    }

    static Stream<Arguments> invalidPointNotStrikeSecondPlay() {
        return Stream.of(
                arguments(Arrays.asList(10, 2, 10)),
                arguments(Arrays.asList(10, 4, 7)),
                arguments(Arrays.asList(10, 8, 5))
        );
    }

    @DisplayName("스트라이크 추가")
    @ParameterizedTest
    @MethodSource("strikePoint")
    void createStrike(List<Integer> points) {
        lastFrame.addScore(points.get(0));
        lastFrame.addScore(points.get(1));
        lastFrame.addScore(points.get(2));

        List<Score> scores = lastFrame.getScores();

        assertAll(
                () -> {
                    for (Score score : scores) {
                        assertThat(score.isEqualScoreType(ScoreType.STRIKE)).isTrue();
                    }
                }
        );
    }

    static Stream<Arguments> strikePoint() {
        return Stream.of(
                arguments(Arrays.asList(10, 10, 10))
        );
    }

    @DisplayName("스페어 추가")
    @ParameterizedTest
    @MethodSource("sparePoint")
    void createSpare(List<Integer> points) {
        lastFrame.addScore(points.get(0));
        lastFrame.addScore(points.get(1));

        List<Score> scores = lastFrame.getScores();

        assertThat(scores.get(1).isEqualScoreType(ScoreType.SPARE)).isTrue();
    }

    static Stream<Arguments> sparePoint() {
        return Stream.of(
                arguments(Arrays.asList(5, 5))
        );
    }

    @DisplayName("거터 추가")
    @Test
    void createGutter() {
        lastFrame.addScore(0);

        List<Score> scores = lastFrame.getScores();

        assertThat(scores.get(0).isEqualScoreType(ScoreType.GUTTER)).isTrue();
    }

    @DisplayName("미스 추가")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void createMiss(int point) {
        lastFrame.addScore(point);

        List<Score> scores = lastFrame.getScores();

        assertThat(scores.get(0).isEqualScoreType(ScoreType.MISS)).isTrue();
    }
}
