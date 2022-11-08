package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoundTest {

    @Test
    void create() {
        Round actual = new Round(10);

        assertThat(actual).isEqualTo(new Round(10));
    }

    @Test
    void isGameEnd_defaultFrame() {
        Round round = new Round(9);
        Frame defaultFrame = new DefaultFrame(1, 2);
        Frame defaultFrameStrike = new DefaultFrame();
        defaultFrameStrike.addScore(Score.of(10));
        Frame defaultFrameSpare = new DefaultFrame(8, 2);

        Assertions.assertAll(
                () -> assertThat(round.isGameEnd(defaultFrame)).isTrue(),
                () -> assertThat(round.isGameEnd(defaultFrameStrike)).isTrue(),
                () -> assertThat(round.isGameEnd(defaultFrameSpare)).isTrue()
        );
    }

    @Test
    void isGameEnd_lastFrame() {
        Round round1 = new Round(9);
        Frame lastFrame = new LastFrame(10, 1, 3);
        Round round2 = new Round(9);
        Frame lastFrameNotStrike = new LastFrame();
        lastFrameNotStrike.addScore(Score.of(5));
        lastFrameNotStrike.addScore(Score.of(4));
        Round round3 = new Round(9);
        Frame lastFrameStrike = new LastFrame();
        lastFrameStrike.addScore(Score.of(10));
        lastFrameStrike.addScore(Score.of(10));
        Round round4 = new Round(9);
        Frame lastFrameSpare = new LastFrame();
        lastFrameSpare.addScore(Score.of(9));
        lastFrameSpare.addScore(Score.of(1));
        Round round5 = new Round(9);
        Frame lastFrameNotSpare = new LastFrame();
        lastFrameNotSpare.addScore(Score.of(8));
        lastFrameNotSpare.addScore(Score.of(1));

        Assertions.assertAll(
                () -> assertThat(round1.isGameEnd(lastFrame)).isTrue(),
                () -> assertThat(round2.isGameEnd(lastFrameSpare)).isFalse(),
                () -> assertThat(round3.isGameEnd(lastFrameNotStrike)).isTrue(),
                () -> assertThat(round4.isGameEnd(lastFrameStrike)).isFalse(),
                () -> assertThat(round5.isGameEnd(lastFrameNotSpare)).isTrue()
        );
    }
}
