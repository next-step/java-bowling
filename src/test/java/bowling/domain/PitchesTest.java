package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static bowling.domain.PitchTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PitchesTest {

    @Test
    void testSum() {
        Pitches pitches = new Pitches(Arrays.asList(PITCH_STRIKE, PITCH_STRIKE, PITCH_STRIKE));
        assertThat(pitches.sum()).isEqualTo(new Score(30));

        pitches = new Pitches(Arrays.asList(PITCH_STRIKE, PITCH_STRIKE));
        assertThat(pitches.sum()).isEqualTo(new Score(20));

        pitches = new Pitches(Arrays.asList(PITCH_STRIKE, PITCH_GUTTER));
        assertThat(pitches.sum()).isEqualTo(new Score(10));
    }

    @Test
    void testFull() {
        Pitches pitches = new Pitches(Arrays.asList(PITCH_STRIKE, PITCH_STRIKE));
        assertThat(pitches.full(2)).isTrue();

        pitches = new Pitches(Arrays.asList(PITCH_STRIKE, PITCH_STRIKE, PITCH_STRIKE));
        assertThat(pitches.full(3)).isTrue();
    }

    @Test
    void testIsFirstPitch() {
        Pitches pitches = new Pitches();
        assertThat(pitches.isFirstPitch()).isTrue();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.isFirstPitch()).isFalse();
    }

    @Test
    void testIsSecondPitch() {
        Pitches pitches = new Pitches();
        assertThat(pitches.isSecondPitch()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.isSecondPitch()).isTrue();
    }

    @Test
    void testIsStrike() {
        Pitches pitches = new Pitches();
        assertThat(pitches.isStrike()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.isStrike()).isTrue();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.isStrike()).isTrue();

        pitches = new Pitches();
        pitches.add(PITCH_GUTTER);
        assertThat(pitches.isStrike()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.isStrike()).isFalse();
    }

    @Test
    void testIsSpare() {
        Pitches pitches = new Pitches();
        assertThat(pitches.isSpare()).isFalse();

        pitches.add(PITCH_GUTTER);
        assertThat(pitches.isSpare()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.isSpare()).isTrue();

        pitches = new Pitches();
        pitches.add(PITCH_EIGHT_PINS);
        assertThat(pitches.isSpare()).isFalse();

        pitches.add(PITCH_TWO_PINS);
        assertThat(pitches.isSpare()).isTrue();
    }

    @Test
    void testIsStrikeOrSpare() {
        Pitches pitches = new Pitches();
        assertThat(pitches.isStrikeOrSpare()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.isStrikeOrSpare()).isTrue();

        pitches.add(PITCH_GUTTER);
        assertThat(pitches.isStrikeOrSpare()).isTrue();

        pitches = new Pitches();
        pitches.add(PITCH_GUTTER);
        assertThat(pitches.isStrikeOrSpare()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.isStrikeOrSpare()).isTrue();

        pitches = new Pitches();
        pitches.add(PITCH_TWO_PINS);
        assertThat(pitches.isStrikeOrSpare()).isFalse();

        pitches.add(PITCH_EIGHT_PINS);
        assertThat(pitches.isStrikeOrSpare()).isTrue();
    }
}
