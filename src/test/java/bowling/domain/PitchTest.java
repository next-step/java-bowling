package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.Pitch.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PitchTest {
    public static final int TWO_PIN_NUMBER = 2;
    public static final int FIVE_PIN_NUMBER = 5;
    public static final int EIGHT_PIN_NUMBER = 8;

    public static final Pitch PITCH_STRIKE = new Pitch(STRIKE_PIN_NUMBER);
    public static final Pitch PITCH_GUTTER = new Pitch(GUTTER_PIN_NUMBER);
    public static final Pitch PITCH_TWO_PINS = new Pitch(TWO_PIN_NUMBER);
    public static final Pitch PITCH_FIVE_PINS = new Pitch(FIVE_PIN_NUMBER);
    public static final Pitch PITCH_EIGHT_PINS = new Pitch(EIGHT_PIN_NUMBER);

    @DisplayName("The number of pins must be between 0 and 10")
    @Test
    void testPinNumber() {
        assertThatThrownBy(() -> new Pitch(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Pitch(11)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testScore() {
        Pitch pitch = new Pitch(FIVE_PIN_NUMBER);
        assertThat(pitch.score()).isEqualTo(new Score(FIVE_PIN_NUMBER));
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
