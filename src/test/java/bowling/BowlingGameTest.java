package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BowlingGameTest {
    @Test
    @DisplayName("이름이 3글자가 아닌 경우")
    void createTest() {
        assertThrows(IllegalArgumentException.class, () -> new BowlingGame("test"));
    }

    @Test
    @DisplayName("프레임이 10개 이상인 경우")
    void createBowlingGameException() {
        BowlingGame bowlingGame = new BowlingGame("테스트");
        for (int i = 1; i <= 10; i++) {
            bowlingGame.roll(10);
        }
        bowlingGame.roll(10);
        bowlingGame.roll(10);
        assertThrows(IllegalArgumentException.class, () -> bowlingGame.roll(10
        ));
    }
}
