package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class SpareTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    public void knockedPins(final int count) {
        assertThatCode(() -> new Spare(count)).doesNotThrowAnyException();
    }

    @Test
    public void invalid() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Spare(0));
    }

    @Test
    public void play_strike() {
        Pitch pitch = new Spare(8);
        pitch = pitch.play(10);

        assertThat(pitch).isInstanceOf(Strike.class);
    }

    @Test
    public void play_normal() {
        Pitch pitch = new Spare(8);
        pitch = pitch.play(4);

        assertThat(pitch).isInstanceOf(Normal.class);
        assertThat(pitch.knockedPins()).isEqualTo(KnockedPins.from(4));
    }
}
