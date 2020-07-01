package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    final BowlingGame bowlingGame = new BowlingGame(new Player("ksj"));

    @Test
    void play() {
        bowlingGame.play(new Pin(1));

        assertThat(bowlingGame.getCurrentFrameNumber()).isEqualTo(1);
    }

    @Test
    void isEndGame() {
        boolean actual = bowlingGame.isEndGame();

        assertThat(actual).isFalse();
    }

    @Test
    void getCurrentFrameNumber() {
        bowlingGame.play(new Pin(10));

        int actual = bowlingGame.getCurrentFrameNumber();

        assertThat(actual).isEqualTo(2);
    }

    @Test
    void isEndFrame() {
        bowlingGame.play(new Pin(10));

        boolean isEndFrame = bowlingGame.isEndFrame(1);

        assertThat(isEndFrame).isTrue();
    }
}
