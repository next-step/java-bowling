package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-28 오후 1:23
 * Developer : Seo
 */
class GridGutterTest {
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
    void gutter() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(0));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void gutter_gutter() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(0));
        frame.spare(playerIndex, new Pins(0));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  0   |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void gutter_gutter_gutter() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(0));
        frame.spare(playerIndex, new Pins(0));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(0));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  0   |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void gutter_gutter_gutter_gutter() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(0));
        frame.spare(playerIndex, new Pins(0));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(0));
        frame.spare(playerIndex, new Pins(0));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  0   |  0   |      |      |      |      |      |      |      |      |");
    }
}
