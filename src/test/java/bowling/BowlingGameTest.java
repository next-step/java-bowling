package bowling;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BowlingGameTest {
    @Test
    public void createTest() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initFrames();
        assertThat(bowlingGame.size()).isEqualTo(10);
    }
}
