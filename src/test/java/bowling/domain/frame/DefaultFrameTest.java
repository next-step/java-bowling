package bowling.domain.frame;

import bowling.domain.score.ScoreKind;
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
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DefaultFrameTest {
    private DefaultFrame defaultFrame;

    @BeforeEach
    void setUp() {
        defaultFrame = DefaultFrame.firstFrame();
    }

    @DisplayName("다음 프레임 생성 테스")
    @Test
    void createNextFrameTest() {
        defaultFrame.addScore(5);
        defaultFrame.addScore(4);
        assertThatCode(() -> defaultFrame.nextFrame(0)).doesNotThrowAnyException();
    }

    @DisplayName("마지막 프레임 생성트 테스트")
    @Test
    void createLastFrameTest() {
        defaultFrame.addScore(5);
        defaultFrame.addScore(4);
        assertThatCode(() -> defaultFrame.lastFrame(0)).doesNotThrowAnyException();
    }

    @DisplayName("점수 추가 테스트")
    @ParameterizedTest
    @MethodSource("pins")
    void addScoreTest(List<Integer> values) {
        assertThatCode(
                () -> {
                    for (Integer value : values) {
                        defaultFrame.addScore(value);
                    }
                }
        );
    }

    static Stream<Arguments> pins() {
        return Stream.of(
                arguments(Arrays.asList(1, 2)),
                arguments(Arrays.asList(4, 5))
        );
    }

    @DisplayName("프레임 점수가 10점 넘을 경우 예외처리 테스트")
    @ParameterizedTest
    @MethodSource("overPins")
    void addScoreFailedTest(List<Integer> values) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> {
                    for (Integer value : values) {
                        defaultFrame.addScore(value);
                    }
                }
        );
    }

    static Stream<Arguments> overPins() {
        return Stream.of(
                arguments(Arrays.asList(9, 2)),
                arguments(Arrays.asList(11, 2))
        );
    }

    @DisplayName("플레이 가능 여부 테스트")
    @Test
    void isPlayableTest() {
        defaultFrame.addScore(1);
        assertThat(defaultFrame.isPlayable()).isTrue();
    }

    @DisplayName("플레이 가능 여부 테스트 - 스트라이크")
    @Test
    void isNotPlayableOnStrikeTest() {
        defaultFrame.addScore(10);
        assertThat(defaultFrame.isPlayable()).isFalse();
    }

    @DisplayName("볼링공을 던질수 없는 상태 - 2구 던졌을때")
    @Test
    void isNotPlayableOnFinishTest() {
        defaultFrame.addScore(1);
        defaultFrame.addScore(2);
        assertThat(defaultFrame.isPlayable()).isFalse();
    }

    @DisplayName("스트라이크 테스트")
    @Test
    void frameStrikeTest() {
        defaultFrame.addScore(10);
        Scores scores = defaultFrame.getScores();
        assertThat(scores.getScores().get(0).isEqualKind(ScoreKind.STRIKE)).isTrue();
    }

    @DisplayName("스페어 테스트")
    @Test
    void frameSpareTest() {
        defaultFrame.addScore(5);
        defaultFrame.addScore(5);
        Scores scores = defaultFrame.getScores();

        assertThat(scores.getScores().get(1).isEqualKind(ScoreKind.SPARE)).isTrue();
    }

    @DisplayName("거터 테스트")
    @Test
    void frameGutterTest() {
        defaultFrame.addScore(0);
        Scores scores = defaultFrame.getScores();
        assertThat(scores.getScores().get(0).isEqualKind(ScoreKind.GUTTER)).isTrue();
    }

    @DisplayName("미스 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void frameMissTest(int point) {
        defaultFrame.addScore(point);
        Scores scores = defaultFrame.getScores();
        assertThat(scores.getScores().get(0).isEqualKind(ScoreKind.MISS)).isTrue();
    }
}