package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class NormalTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    public void knockedPins(final int count) {
        assertThatCode(() -> new Normal(KnockedPins.from(count))).doesNotThrowAnyException();
    }

    @Test
    public void invalid() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Normal(KnockedPins.from(10)));
    }

    @Test
    public void play_spare() {
        Pitch pitch = new Normal(KnockedPins.from(8));
        pitch = pitch.play(KnockedPins.from(2));

        assertThat(pitch).isInstanceOf(Spare.class);
        assertThat(pitch.knockedPins()).isEqualTo(KnockedPins.from(2));
    }

    @Test
    public void play_normal() {
        Pitch pitch = new Normal(KnockedPins.from(8));
        pitch = pitch.play(KnockedPins.from(1));

        assertThat(pitch).isInstanceOf(Normal.class);
        assertThat(pitch.knockedPins()).isEqualTo(KnockedPins.from(1));
    }
}
