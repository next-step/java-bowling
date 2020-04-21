package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
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
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DefaultFrameTest {
    private DefaultFrame defaultFrame;

    @BeforeEach
    void setUp() {
        defaultFrame = DefaultFrame.first();
    }

    @DisplayName("첫번째 프레임 생성")
    @Test
    void createFrame() {
        assertThatCode(() -> DefaultFrame.first()).doesNotThrowAnyException();
    }

    @DisplayName("다음 프레임 생성")
    @Test
    void createNextFrame() {
        defaultFrame = DefaultFrame.first();
        defaultFrame.addScore(5);
        defaultFrame.addScore(4);

        assertThatCode(() -> defaultFrame.createNextFrame(0)).doesNotThrowAnyException();
    }

    @DisplayName("마지막 프레임 생성")
    @Test
    void createLastFrame() {
        defaultFrame = DefaultFrame.first();
        defaultFrame.addScore(5);
        defaultFrame.addScore(4);

        assertThatCode(() -> defaultFrame.createLastFrame(0)).doesNotThrowAnyException();
    }

    @DisplayName("점수 추가")
    @ParameterizedTest
    @MethodSource("points")
    void addPoint(List<Integer> values) {
        defaultFrame = DefaultFrame.first();

        assertThatCode(
                () -> {
                    for (Integer value : values) {
                        defaultFrame.addScore(value);
                    }
                }
        );
    }

    static Stream<Arguments> points() {
        return Stream.of(
                arguments(Arrays.asList(1, 2)),
                arguments(Arrays.asList(4, 5))
        );
    }

    @DisplayName("프레임 점수가 10점이 넘을 경우 throws Exception")
    @ParameterizedTest
    @MethodSource("overPoints")
    void addPointFailByOverPoint(List<Integer> values) {
        defaultFrame = DefaultFrame.first();

        assertThatIllegalArgumentException().isThrownBy(
                () -> {
                    for (Integer value : values) {
                        defaultFrame.addScore(value);
                    }
                }
        );
    }

    static Stream<Arguments> overPoints() {
        return Stream.of(
                arguments(Arrays.asList(9, 2)),
                arguments(Arrays.asList(11, 2))
        );
    }

    @DisplayName("볼링공을 던질수 있는 상태")
    @Test
    void isPlayable() {
        defaultFrame = DefaultFrame.first();
        defaultFrame.addScore(1);

        assertThat(defaultFrame.isPlayable()).isTrue();
    }

    @DisplayName("볼링공을 던질수 없는 상태 - 초구 스트라이크")
    @Test
    void isNotPlayableByStrike() {
        defaultFrame = DefaultFrame.first();
        defaultFrame.addScore(10);

        assertThat(defaultFrame.isPlayable()).isFalse();
    }

    @DisplayName("볼링공을 던질수 없는 상태 - 2번의 공을 던졌을때")
    @Test
    void isNotPlayableByCount() {
        defaultFrame = DefaultFrame.first();
        defaultFrame.addScore(1);
        defaultFrame.addScore(2);

        assertThat(defaultFrame.isPlayable()).isFalse();
    }

    @DisplayName("프레임 점수 계산")
    @ParameterizedTest
    @ValueSource(ints = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0})
    void calculateFramePoint(int point) {
        defaultFrame.addScore(point);

        assertThat(defaultFrame.getTotalPoint(0)).isEqualTo(point);
    }

    @DisplayName("프레임 점수 계산 - 스트라이크 보너스 점수")
    @ParameterizedTest
    @MethodSource("strikePoint")
    void calculateStrikeFramePoint(List<Integer> points, int totalPoint) {
        defaultFrame.addScore(points.get(0));

        DefaultFrame secondFrame = defaultFrame.createNextFrame(0);
        secondFrame.addScore(points.get(1));

        DefaultFrame thirdFrame = secondFrame.createNextFrame(1);
        thirdFrame.addScore(points.get(2));

        assertThat(defaultFrame.getTotalPoint(0)).isEqualTo(totalPoint);
    }

    static Stream<Arguments> strikePoint() {
        return Stream.of(
                arguments(Arrays.asList(10, 1, 2), 13),
                arguments(Arrays.asList(10, 10, 10), 30)
        );
    }

    @DisplayName("프레임 점수 계산 - 스페어 보너스 점수")
    @ParameterizedTest
    @MethodSource("sparePoint")
    void calculateSpareFramePoint(List<Integer> points, int totalPoint) {
        defaultFrame.addScore(points.get(0));
        defaultFrame.addScore(points.get(1));

        DefaultFrame secondFrame = defaultFrame.createNextFrame(0);
        secondFrame.addScore(points.get(2));

        assertThat(defaultFrame.getTotalPoint(0)).isEqualTo(totalPoint);
    }

    static Stream<Arguments> sparePoint() {
        return Stream.of(
                arguments(Arrays.asList(1, 9, 2), 12),
                arguments(Arrays.asList(5, 5, 10), 20)
        );
    }

    @DisplayName("스트라이크 추가")
    @Test
    void createStrike() {
        defaultFrame.addScore(10);

        List<Score> scores = defaultFrame.getScores();

        assertThat(scores.get(0).isEqualScoreType(ScoreType.STRIKE)).isTrue();
    }

    @DisplayName("스페어 추가")
    @ParameterizedTest
    @MethodSource("sparePoints")
    void createSpare(List<Integer> points) {
        defaultFrame.addScore(points.get(0));
        defaultFrame.addScore(points.get(1));

        List<Score> scores = defaultFrame.getScores();

        assertThat(scores.get(1).isEqualScoreType(ScoreType.SPARE)).isTrue();
    }

    static Stream<Arguments> sparePoints() {
        return Stream.of(
                arguments(Arrays.asList(5, 5))
        );
    }

    @DisplayName("거터 추가")
    @Test
    void createGutter() {
        defaultFrame.addScore(0);

        List<Score> scores = defaultFrame.getScores();

        assertThat(scores.get(0).isEqualScoreType(ScoreType.GUTTER)).isTrue();
    }

    @DisplayName("미스 추가")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void createMiss(int point) {
        defaultFrame.addScore(point);

        List<Score> scores = defaultFrame.getScores();

        assertThat(scores.get(0).isEqualScoreType(ScoreType.MISS)).isTrue();
    }
}
