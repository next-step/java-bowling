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
        Round round1 = new Round(10);
        Frame defaultFrame = new DefaultFrame();
        defaultFrame.addScore(Score.of(1));
        defaultFrame.addScore(Score.of(2));
        Round round2 = new Round(10);
        Frame defaultFrameStrike = new DefaultFrame();
        defaultFrameStrike.addScore(Score.of(10));
        Round round3 = new Round(10);
        Frame defaultFrameSpare = new DefaultFrame();
        defaultFrameSpare.addScore(Score.of(8));
        defaultFrameSpare.addScore(Score.of(2));
        Round round4 = new Round(10);
        Frame defaultFrameRemainChance = new DefaultFrame();
        defaultFrameRemainChance.addScore(Score.of(8));

        Assertions.assertAll(
                () -> assertThat(round1.isGameEnd(defaultFrame)).isTrue(),
                () -> assertThat(round2.isGameEnd(defaultFrameStrike)).isTrue(),
                () -> assertThat(round3.isGameEnd(defaultFrameSpare)).isTrue(),
                () -> assertThat(round4.isGameEnd(defaultFrameRemainChance)).isFalse()
        );
    }

    @Test
    void isGameEnd_lastFrame() {
        Round round1 = new Round(10);
        Frame lastFrame = new LastFrame();
        lastFrame.addScore(Score.of(10));
        lastFrame.addScore(Score.of(1));
        lastFrame.addScore(Score.of(2));
        Round round2 = new Round(10);
        Frame lastFrameNotStrike = new LastFrame();
        lastFrameNotStrike.addScore(Score.of(5));
        lastFrameNotStrike.addScore(Score.of(4));
        Round round3 = new Round(10);
        Frame lastFrameStrike = new LastFrame();
        lastFrameStrike.addScore(Score.of(10));
        lastFrameStrike.addScore(Score.of(10));
        Round round4 = new Round(10);
        Frame lastFrameSpare = new LastFrame();
        lastFrameSpare.addScore(Score.of(9));
        lastFrameSpare.addScore(Score.of(1));
        Round round5 = new Round(10);
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
