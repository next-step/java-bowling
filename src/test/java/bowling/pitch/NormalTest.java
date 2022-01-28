package bowling.pitch;

import bowling.domain.KnockedPins;
import bowling.domain.pitch.Normal;
import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.Spare;
import bowling.domain.pitch.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class NormalTest {
    @Test
    @DisplayName("normal pitch 에서 spare 플레이 경우 spare 반환")
    void playSpare() {
        Normal normal = new Normal(new KnockedPins(4));
        Pitch spare = normal.play(new KnockedPins(6));
        assertThat(spare.getKnockedPins().getKnockedPins()).isEqualTo(new KnockedPins(6).getKnockedPins());
        assertThat(spare.getClass()).isEqualTo(Spare.class);
    }

    @Test
    @DisplayName("normal pitch 에서 normal 플레이 경우 normal 반환")
    void playNormal() {
        Normal normal = new Normal(new KnockedPins(4));
        Pitch normalPitch = normal.play(new KnockedPins(3));
        assertThat(normalPitch.getKnockedPins().getKnockedPins()).isEqualTo(new KnockedPins(3).getKnockedPins());
        assertThat(normalPitch.getClass()).isEqualTo(Normal.class);
    }

    @Test
    @DisplayName("normal pitch 에서 strike 플레이 경우 strike 반환")
    void playStrike() {
        Normal normal = new Normal();
        Pitch strike = normal.play(new KnockedPins(10));
        assertThat(strike.getKnockedPins().getKnockedPins()).isEqualTo(new KnockedPins(10).getKnockedPins());
        assertThat(strike.getClass()).isEqualTo(Strike.class);
    }
}
