package bowling.bowlinggame;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {
    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame(Frames.of(), new Player("LDC"));
    }

    @DisplayName("볼링게임 생성")
    @Test
    void create() {
        assertThatCode(() -> new BowlingGame(Frames.of(), new Player("LDC")));
    }

    @DisplayName("진행중인 게임인지 판단")
    @Test
    void isPlaying() {
        assertThat(bowlingGame.isOver()).isFalse();
    }

    @DisplayName("종료된 게임인지 판단")
    @Test
    void isOver() {
        for (int i = 0; i < 10; i++) {
            bowlingGame.addNextFrame();
        }
        assertThat(bowlingGame.isOver()).isTrue();
    }

    @DisplayName("다음 프레임 생성")
    @Test
    void createNextFrame() {
        Frames frames = bowlingGame.getFrames();
        assertThat(frames.size()).isEqualTo(0);

        bowlingGame.addNextFrame();
        frames = bowlingGame.getFrames();
        assertThat(frames.size()).isEqualTo(1);
    }

    @DisplayName("마지막 프레임 반환")
    @Test
    void getLastFrame() {
        bowlingGame.addNextFrame();
        Frame frame = bowlingGame.getLastFrame();

        assertThat(frame).isNotNull();
    }
}
