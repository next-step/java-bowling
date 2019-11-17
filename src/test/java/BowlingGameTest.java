import game.BowlingGame;
import game.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BowlingGameTest {
    @Test
    @DisplayName("볼링게임 생성")
    void createTest() {
        BowlingGame bowlingGame = new BowlingGame("test");
        assertThat(bowlingGame).isEqualTo(new BowlingGame("test"));
    }

    @Test
    @DisplayName("프레임이 10개 이상인 경우")
    void createBowlingGameException() {
        BowlingGame bowlingGame = new BowlingGame("test");
        for (int i = 0; i < 10; i++) {
            bowlingGame.addFrame(Frame.of(10));
        }
        assertThrows(IllegalArgumentException.class, () -> bowlingGame.addFrame(Frame.of(10)));
    }
}
