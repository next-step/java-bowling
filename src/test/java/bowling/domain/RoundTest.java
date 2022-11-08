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
        Round round = new Round(9);
        Frame lastFrame = new LastFrame(10, 1, 3);
        Frame lastFrameNotStrike = new LastFrame();
        lastFrameNotStrike.addScore(Score.of(5));
        lastFrameNotStrike.addScore(Score.of(4));
        Frame lastFrameStrike = new LastFrame();
        lastFrameStrike.addScore(Score.of(10));
        lastFrameStrike.addScore(Score.of(10));
        Frame lastFrameSpare = new LastFrame();
        lastFrameSpare.addScore(Score.of(9));
        lastFrameSpare.addScore(Score.of(1));
        Frame lastFrameNotSpare = new LastFrame();
        lastFrameNotSpare.addScore(Score.of(8));
        lastFrameNotSpare.addScore(Score.of(1));

        Assertions.assertAll(
                () -> assertThat(round.isGameEnd(lastFrame)).isTrue(),
                () -> assertThat(round.isGameEnd(lastFrameNotStrike)).isTrue(),
                () -> assertThat(round.isGameEnd(lastFrameStrike)).isFalse(),
                () -> assertThat(round.isGameEnd(lastFrameSpare)).isFalse(),
                () -> assertThat(round.isGameEnd(lastFrameNotSpare)).isTrue()
        );
    }
}
