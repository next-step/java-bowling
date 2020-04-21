package bowling.domain;

import bowling.domain.player.Player;
import bowling.domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class BowlingGameTest {
    private Player player;
    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        player = new Player("jjy");
        bowlingGame = new BowlingGame(player);
    }

    @Test
    @DisplayName("볼링게임 공던지기 테스트")
    void throwBallTest() {
        assertThatCode(
                () -> bowlingGame.throwBall(Point.of(10))
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("진행중인 프레임 번호 가져오기")
    void currentFrameNoTest() {
        for (int i = 0; i < 2; i++) {
            bowlingGame.throwBall(Point.of(10));
        }
        assertThat(
                bowlingGame.currentFrameNo()
        ).isEqualTo(2);
    }

    @Test
    @DisplayName("게임 끝 테스트")
    void isEndTest() {
        for (int i = 0; i < 13; i++) {
            bowlingGame.throwBall(Point.of(10));
        }
        assertThat(
                bowlingGame.isEnd()
        ).isTrue();
    }
}
