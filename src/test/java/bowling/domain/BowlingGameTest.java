package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class BowlingGameTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("jjy");
    }

    @Test
    @DisplayName("볼링게임 생성 테스트")
    void createBowlingGameTest() {
        assertThatCode(
                () -> new BowlingGame(player)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("볼링게임 공던지기 테스트")
    void throwBallTest() {
        BowlingGame bowlingGame = new BowlingGame(player);
        Frames frames = bowlingGame.getFrames();
        assertThat(
                bowlingGame.throwBall(Point.of(10))
        ).isEqualTo(frames.getFrame(1));
    }

    @Test
    @DisplayName("첫 프레임 가져오기 테스트")
    void getFirstFrameTest() {
        BowlingGame bowlingGame = new BowlingGame(player);
        Frames frames = bowlingGame.getFrames();
        assertThat(
                bowlingGame.getFirstFrame()
        ).isEqualTo(frames.getFrame(0));
    }
}
