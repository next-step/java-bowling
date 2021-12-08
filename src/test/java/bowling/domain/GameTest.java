package bowling.domain;

import org.junit.jupiter.api.Test;

import static bowling.controller.BowlingGame.LAST_FRAME;
import static bowling.controller.BowlingGame.NINTH_FRAME;
import static bowling.domain.Pitch.STRIKE_PIN_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {

    @Test
    void testFrameNumber() {
        Game game = ofFinishedGame();
        assertThat(game.frameNumber()).isEqualTo(LAST_FRAME);

        game = ofUnfinishedGame();
        assertThat(game.frameNumber()).isEqualTo(LAST_FRAME);
    }

    @Test
    void testFinished() {
        Game game = ofFinishedGame();
        assertThat(game.finished()).isTrue();

        game = ofUnfinishedGame();
        assertThat(game.finished()).isFalse();
    }

    private Game ofFinishedGame() {
        Frames frames = FramesTest.ofStrikeFrames(LAST_FRAME);
        frames.pitch(STRIKE_PIN_NUMBER);
        frames.pitch(STRIKE_PIN_NUMBER);

        return new Game("SSY", frames);
    }

    private Game ofUnfinishedGame() {
        Frames frames = FramesTest.ofStrikeFrames(NINTH_FRAME);

        return new Game("SSY", frames);
    }
}
