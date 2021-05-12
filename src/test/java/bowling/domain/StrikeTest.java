package bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    @Test
    public void knockedPins() {
        assertThat(new Strike().knockedPins()).isEqualTo(KnockedPins.from(10));
    }

    @Test
    public void play_strike() {
        Pitch pitch = new Strike();
        pitch = pitch.play(10);

        assertThat(pitch).isInstanceOf(Strike.class);
    }

    @Test
    public void play_normal() {
        Pitch pitch = new Strike();
        pitch = pitch.play(6);

        assertThat(pitch).isInstanceOf(Normal.class);
        assertThat(pitch.knockedPins()).isEqualTo(KnockedPins.from(6));
    }
}
