package bowling.domain.status;

import bowling.domain.Pitch;
import bowling.domain.Pitches;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusFactoryTest {
    @Test
    void factory_strike() {
        Pitches pitches = new Pitches(Arrays.asList(new Pitch(10)));
        assertThat(StatusFactory.status(pitches)).isInstanceOf(Strike.class);
    }

    @Test
    void factory_spare() {
        Pitches pitches = new Pitches(Arrays.asList(new Pitch(8), new Pitch(2)));
        assertThat(StatusFactory.status(pitches)).isInstanceOf(Spare.class);
    }

    @Test
    void factory_open() {
        Pitches pitches = new Pitches(Arrays.asList(new Pitch(0), new Pitch(0)));
        assertThat(StatusFactory.status(pitches)).isInstanceOf(Open.class);
    }

    @Test
    void factory_bonus() {
        Pitches pitches = new Pitches(Arrays.asList(new Pitch(8), new Pitch(2), new Pitch(5)));
        assertThat(StatusFactory.status(pitches)).isInstanceOf(Bonus.class);
        assertThat(pitches.display()).isEqualTo("8|/|5");
    }
}
