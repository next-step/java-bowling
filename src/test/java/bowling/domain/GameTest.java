package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private String name;

    @BeforeEach
    void setUp() {
        name = "BRK";
    }

    @DisplayName("게임이 끝났는지 확인한다")
    @Test
    void isFinished() {
        Game game = new Game(name, FramesTest.FINISHED_FRAMES);
        assertThat(game.isFinished()).isTrue();
    }

    @DisplayName("쓰러트린 핀 갯수를 추가한다")
    @Test
    void addPinCount() {
        Game game = new Game(name);
        game.addPin(1);
    }

    @DisplayName("원하는 프레임의 핀 갯수 정보를 가져온다")
    @Test
    void getFramePinCount() {
        int first = 1;
        int second = 2;
        Game game = new Game(name, 3);
        game.addPin(first);
        game.addPin(second);

        assertThat(game.getFramePinCount(0)).containsExactly(first, second);
    }
}
