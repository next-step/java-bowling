package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    Player player;
    @BeforeEach
    void makePlayer() {
        player = new Player("JHJ");
    }

    @Test
    void create() {
        Frames frames = new Frames(player);

        assertThat(frames.currentFrame()).isEqualTo(String.valueOf(Frame.MIN_FRAME_INDEX));
    }

    @Test
    void playerTest() {
        String playerName = player.name();
        Frames frames = new Frames(player);

        assertThat(frames.getPlayerName()).isEqualTo(playerName);
    }


    @Test
    void frameSizeTest() {

        Frames frames = new Frames(player);

        assertThat(frames.getFrames()).hasSize(1);
    }

    @Test
    void frameFinishTest() {
        Frames frames = new Frames(player);
        for(int i = 0; i < 12; i++) {
            frames.play(10);
        }
        assertTrue(frames.allFrameEnd());
    }
}
