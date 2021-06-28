package bowling.domain.pitch;

import bowling.domain.KnockedPins;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class NormalTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    public void knockedPins(final int count) {
        assertThatCode(() -> new Normal(count)).doesNotThrowAnyException();
    }

    @Test
    public void invalid() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Normal(10));
    }

    @Test
    public void play_spare() {
        Pitch pitch = new Normal(8);
        pitch = pitch.play(2);

        assertThat(pitch).isInstanceOf(Spare.class);
        assertThat(pitch.knockedPins()).isEqualTo(KnockedPins.from(2));
    }

    @Test
    public void play_normal() {
        Pitch pitch = new Normal(8);
        pitch = pitch.play(1);

        assertThat(pitch).isInstanceOf(Normal.class);
        assertThat(pitch.knockedPins()).isEqualTo(KnockedPins.from(1));
    }
}
