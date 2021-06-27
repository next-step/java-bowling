package bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GameTest {
    @Test
    public void init() {
        final Game game = Game.init();
        assertThat(game.playing()).isTrue();
    }

    @Test
    public void play() {
        Game game = Game.init();

        while (game.playing()) {
            game = game.play(10);
        }

        assertThat(game.frames().size()).isEqualTo(10);
        assertThat(game.frames().get(9)).isInstanceOf(FinalFrame.class);
    }

    @Test
    public void play_invalid() {
        assertThatIllegalStateException().isThrownBy(() -> {
            Game game = Game.init();

            while (game.playing()) {
                game = game.play(10);
            }

            game.play(10);
        });
    }
}
