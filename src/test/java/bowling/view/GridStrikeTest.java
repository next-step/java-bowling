package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 2020-12-28 오후 3:48
 * Developer : Seo
 */
class GridStrikeTest {
    private Frames frames;
    private Player player;
    private int playerIndex;
    private int frameIndex;

    @BeforeEach
    void setUp() {
        frames = new Frames();
        player = new Player("aaa");
        playerIndex = 0;
        frameIndex = 0;
    }

    @Test
    void strike() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void strike_strike() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void strike_strike_strike() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void strike_strike_strike_strike() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  30  |  60  |      |      |      |      |      |      |      |      |");
    }

    @Test
    void strike_strike_miss() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(10));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(8));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  28  |      |      |      |      |      |      |      |      |      |");
    }
}
