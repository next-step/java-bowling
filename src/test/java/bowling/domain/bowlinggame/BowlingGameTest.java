package bowling.domain.bowlinggame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BowlingGameTest {

    private BowlingGame createBowlingGame() {
        return new BowlingGame("WIJ");
    }

    @Test
    @DisplayName("프레임 생성 테스트")
    void addNextFrameTest() {
        BowlingGame bowlingGame = createBowlingGame();
        assertThatCode(() -> bowlingGame.addNextFrame());
    }

    @Test
    @DisplayName("현재 frame이 종료되지 않은 상태에서 다음 frame 생성 시 Exception")
    void addNextFrameExceptionTest() {
        BowlingGame bowlingGame = createBowlingGame();
        bowlingGame.addNextFrame();
        bowlingGame.writePoint(5);

        assertThatThrownBy(() -> bowlingGame.addNextFrame())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("frame을 10개 초과해 생성 시 Exception")
    void addNextFrameMaxExceptionTest() {
        BowlingGame bowlingGame = createBowlingGame();
        for (int i = 0; i < 10; i++) {
            bowlingGame.addNextFrame();
            bowlingGame.writePoint(10);
        }

        assertThatThrownBy(() -> bowlingGame.addNextFrame())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수 기록 테스트")
    void writePointTest() {
        BowlingGame bowlingGame = createBowlingGame();
        bowlingGame.addNextFrame();

        assertThatCode(() -> bowlingGame.writePoint(10));
    }

    @ParameterizedTest
    @MethodSource("provideGameOverArgument")
    @DisplayName("볼링 게임이 종료되었는지 테스트")
    void gameOverTest(List<Integer> points, boolean isGameOver) {
        BowlingGame bowlingGame = createBowlingGame();
        bowlingGame.addNextFrame();
        for (Integer point : points) {
            if (!bowlingGame.isCurrentFramePlayable()) {
                bowlingGame.addNextFrame();
            }
            bowlingGame.writePoint(point);
        }

        assertThat(bowlingGame.isGameOver()).isEqualTo(isGameOver);
    }

    private static Stream<Arguments> provideGameOverArgument() {
        return Stream.of(
                Arguments.of(Arrays.asList(10), false),
                Arguments.of(Arrays.asList(10, 5, 4, 3), false),
                Arguments.of(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10), true)
        );
    }

    @ParameterizedTest
    @MethodSource("providePlayableArguments")
    @DisplayName("현재 frame이 게임이 가능한지 테스트")
    void currentFramePlayableTest(Integer point, boolean isPlayable) {
        BowlingGame bowlingGame = createBowlingGame();
        bowlingGame.addNextFrame();
        bowlingGame.writePoint(point);

        assertThat(bowlingGame.isCurrentFramePlayable()).isEqualTo(isPlayable);
    }

    private static Stream<Arguments> providePlayableArguments() {
        return Stream.of(
                Arguments.of(10, false),
                Arguments.of(5, true)
        );
    }
}