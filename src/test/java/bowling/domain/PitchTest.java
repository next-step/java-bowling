package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.Pitch.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PitchTest {
    public static final Pitch PITCH_STRIKE = new Pitch(10);
    public static final Pitch PITCH_GUTTER = new Pitch(0);
    public static final Pitch PITCH_TWO_PINS = new Pitch(2);
    public static final Pitch PITCH_FIVE_PINS = new Pitch(5);
    public static final Pitch PITCH_EIGHT_PINS = new Pitch(8);

    @DisplayName("The number of pins must be between 0 and 10")
    @Test
    void testPinNumber() {
        assertThatThrownBy(() -> new Pitch(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Pitch(11)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testScore() {
        Pitch pitch = new Pitch(5);
        assertThat(pitch.score()).isEqualTo(new Score(5));
    }

    @Test
    void testState() {
        assertThat(PITCH_STRIKE.state(PITCH_GUTTER)).isEqualTo(STATE_STRIKE);
        assertThat(PITCH_GUTTER.state(PITCH_FIVE_PINS)).isEqualTo(STATE_GUTTER);
        assertThat(PITCH_GUTTER.state(PITCH_STRIKE)).isEqualTo(STATE_SPARE);
        assertThat(PITCH_TWO_PINS.state(PITCH_EIGHT_PINS)).isEqualTo(STATE_SPARE);
        assertThat(PITCH_FIVE_PINS.state(PITCH_FIVE_PINS)).isEqualTo(STATE_SPARE);
        assertThat(PITCH_EIGHT_PINS.state(PITCH_GUTTER)).isEqualTo('8');
        assertThat(PITCH_FIVE_PINS.state(PITCH_GUTTER)).isEqualTo('5');
        assertThat(PITCH_TWO_PINS.state(PITCH_GUTTER)).isEqualTo('2');
    }
}
