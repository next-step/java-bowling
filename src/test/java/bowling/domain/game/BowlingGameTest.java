package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {
    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame(Player.of("JHP"));
    }

    @DisplayName("진행중 게임 여부 테스트")
    @Test
    void isGameOverTest() {
        assertThat(bowlingGame.isGameOver()).isFalse();
    }

    @DisplayName("다음 프레임 생성 테스트")
    @Test
    void createNextFrameTest() {
        Frames frames = bowlingGame.getFrames();
        assertThat(frames.size()).isZero();

        bowlingGame.nextFrame();
        assertThat(bowlingGame.getFrames().size()).isOne();
    }
}