package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    @Test
    void play() {
        BowlingGame bowlingGame = new BowlingGame("PES");

        bowlingGame.play(10);
        bowlingGame.play(8);
        bowlingGame.play(1);

        assertThat(bowlingGame.getBowling().getFrames().getFrames()).hasSize(2);
        assertThat(bowlingGame.getBowling().getPlayer().getName()).isEqualTo("PES");
    }

    @Test
    void getLastFrameNumber() {
        BowlingGame bowlingGame = new BowlingGame("PES");
        bowlingGame.play(10);
        bowlingGame.play(8);
        bowlingGame.play(1);

        int lastFrameNumber = bowlingGame.getLastFrameNumber();

        assertThat(lastFrameNumber).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:true", "8:false"}, delimiter = ':')
    void isEnd(int value, boolean expected) {
        BowlingGame bowlingGame = new BowlingGame("PES");
        for (int i = 0; i < value; i++) {
            bowlingGame.play(10);
        }

        assertThat(bowlingGame.isEnd()).isEqualTo(expected);
    }
}
