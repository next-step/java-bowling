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
    @DisplayName("볼링게임 예외발생")
    void createBowlingGameException() {
        BowlingGame bowlingGame = new BowlingGame("test");
        for (int i = 0; i < 10; i++) {
            bowlingGame.addFrame(new Frame());
        }
        assertThrows(IllegalArgumentException.class, () -> bowlingGame.addFrame(new Frame()));
    }
}
