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

    @Test
    public void bowlingGameText() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initFrames();

        for (int i = 1; i < BowlingGame.MAX_FRAME_SIZE + 3; i++) {
            bowlingGame.setKnockDownPins(10);
        }
        assertThat(bowlingGame.isEnd()).isTrue();
    }
}
