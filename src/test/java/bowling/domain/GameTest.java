package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    public static final Game FINISHED_GAME =
            (new Game("QWE", 2)).addPin(10).addPin(1).addPin(2);

    private Game game;

    @BeforeEach
    void setUp() {
        String name = "BRK";
        game = new Game(name, 3);
    }

    @DisplayName("게임이 끝났는지 확인한다")
    @Test
    void isFinished() {
        assertThat(FINISHED_GAME.isFinished()).isTrue();
    }

    @DisplayName("쓰러트린 핀 갯수를 추가한다")
    @Test
    void addPinCount() {
        game.addPin(1);
    }

    @DisplayName("원하는 프레임의 핀 갯수 정보를 가져온다")
    @Test
    void getFramePinCount() {
        int first = 1;
        int second = 2;
        game.addPin(first);
        game.addPin(second);

        assertThat(game.getFramePinCounts(0))
                .containsExactly(PinCountTest.PIN_COUNT_1,
                        PinCountTest.PIN_COUNT_2);
    }

    @DisplayName("현재 프레임 정보를 얻어온다")
    @Test
    void getCurrentFrame() {
        game.addPin(10);

        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }

    @DisplayName("핀 갯수 저장 후 현재 프레임이 끝났다면, 다음 프레임으로 넘어간다")
    @Test
    void nextFrame() {
        game.addPin(10);
        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }

    @DisplayName("게임이 끝났는지 여부를 체크한다")
    @Test
    void lastFrame() {
        int dummyPinCount = 10;
        game.addPin(dummyPinCount);
        assertThat(game.isFinished()).isFalse();
        game.addPin(dummyPinCount);
        assertThat(game.isFinished()).isFalse();
        game.addPin(1);
        game.addPin(2);
        assertThat(game.isFinished()).isTrue();
    }
}
