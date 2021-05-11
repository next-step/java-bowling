package bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class BowlingTest {
    @Test
    public void init() {
        final Bowling bowling = Bowling.init();
        assertThat(bowling.playing()).isTrue();
    }

    @Test
    public void play() {
        Bowling bowling = Bowling.init();

        while (bowling.playing()) {
            bowling = bowling.play(10);
        }

        assertThat(bowling.frames().size()).isEqualTo(10);
        assertThat(bowling.frames().get(9)).isInstanceOf(LastFrame.class);
    }

    @Test
    public void play_invalid() {
        assertThatIllegalStateException().isThrownBy(() -> {
            Bowling bowling = Bowling.init();

            while (bowling.playing()) {
                bowling = bowling.play(10);
            }

            bowling.play(10);
        });
    }
}
