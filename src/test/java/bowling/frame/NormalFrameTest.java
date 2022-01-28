package bowling.frame;

import bowling.domain.Game;
import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pitch.Normal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

public class NormalFrameTest {
    @Test
    void play() {
        Frame normalFrame = new NormalFrame(Arrays.asList(new Normal(5)));
        normalFrame = normalFrame.play(new KnockedPins(3));

        assertThat(normalFrame.getFirstKnockedPins().getCount()).isEqualTo(5);
        assertThat(normalFrame.getSecondKnockedPins().getCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("normal pitch 의 get score test")
    void calculateScore() {
        Frame normalFrame = new NormalFrame(Arrays.asList(new Normal(3)));
        normalFrame = normalFrame.play(new KnockedPins(5));
        Game game = new Game(Arrays.asList(normalFrame));
        Score score = normalFrame.calculateScore(game);

        assertThat(score.canCalucateScore()).isEqualTo(true);
        assertThat(score.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("spare pitch 의 calculateScore test")
    void calculateSpareScore() {
        Frame normalFrame = new NormalFrame(Arrays.asList(new Normal(3)));
        normalFrame = normalFrame.play(new KnockedPins(7));
        Frame nextFrame = normalFrame.createNextFrame().play(new KnockedPins(7));
        Game game = new Game(Arrays.asList(normalFrame,nextFrame));
        Score score = normalFrame.calculateScore(game);

        assertThat(score.canCalucateScore()).isEqualTo(true);
        assertThat(score.getScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("strike pitch 의 calculateScore test")
    void calculateStrikeScore() {
        Frame normalFrame = new NormalFrame(Arrays.asList(new Normal(10)));
        Frame nextFrame = normalFrame.createNextFrame().play(new KnockedPins(3));
        nextFrame = nextFrame.play(new KnockedPins(7));

        Game game = new Game(Arrays.asList(normalFrame,nextFrame));
        Score score = normalFrame.calculateScore(game);

        assertThat(score.canCalucateScore()).isEqualTo(true);
        assertThat(score.getScore()).isEqualTo(20);
    }
}
