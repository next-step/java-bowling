package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new Player("LSH"));
    }

    @Test
    @DisplayName("처음 생성한 게임에서 nextFrame()함수 호출 시 1번 프레임을 리턴한다.")
    void nextFrame() {
        Frame frame = game.nextFrame();
        assertEquals(frame.order(), 1);
    }

    @Test
    @DisplayName("10번의 frame이 완료하고 isOver()함수를 호출 시 True 임을 확인한다.")
    void isOver() {
        for (int i = 0; i < 10; i++) {
            assertFalse(game.isOver());
            game.pitch(10);
        }
        assertTrue(game.isOver());
    }

    @Test
    @DisplayName("10점 미만의 핏칭을 한번 했을 때 핏칭 전과 후의 프레임이 같음 확인한다.")
    void pitch() {
        Frame beforePitch = game.nextFrame();
        game.pitch(5);
        Frame afterPitch = game.nextFrame();
        assertEquals(beforePitch, afterPitch);
    }

    @Test
    @DisplayName("10점 핏칭을 한번 했을 때 핏칭 전과 후의 프레임이 다름 확인한다.")
    void pitchDifferentFrame() {
        Frame beforePitch = game.nextFrame();
        game.pitch(10);
        Frame afterPitch = game.nextFrame();
        assertNotEquals(beforePitch, afterPitch);
        assertEquals(beforePitch.order() + 1, afterPitch.order());
    }

    @Test
    @DisplayName("열 번째 프레임에서 스트라이크를 치면 세 번 핏칭한다.")
    void strikeAt10thFrame() {
        for (int i = 0; i < 9; i++) {
            game.pitch(10);
        }
        game.pitch(10);
        assertFalse(game.isOver());
        game.pitch(5);
        assertFalse(game.isOver());
        game.pitch(10);
        assertTrue(game.isOver());
    }

    @Test
    @DisplayName("열 번째 프레임에서 스페어를 치면 세 번 핏칭한다.")
    void spareAt10thFrame() {
        for (int i = 0; i < 9; i++) {
            game.pitch(10);
        }
        game.pitch(5);
        assertFalse(game.isOver());
        game.pitch(5);
        assertFalse(game.isOver());
        game.pitch(10);
        assertTrue(game.isOver());
    }
}