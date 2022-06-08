package refactor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {
    @Test
    void pitch두번하면done() {
        Scores scores = new Scores();
        Scores playing = scores.pitch(5);
        assertThat(playing.done()).isFalse();
        Scores done = playing.pitch(4);
        assertThat(done.done()).isTrue();
    }

    @Test
    void strike이면한번에done() {
        Scores scores = new Scores();
        Scores done = scores.pitch(10);
        assertThat(done.done()).isTrue();
    }

    @Test
    void sum은9점() {
        Scores scores = new Scores();
        Scores playing = scores.pitch(5);
        Scores done = playing.pitch(4);
        assertThat(done.sum()).isEqualTo(9);
    }
}
