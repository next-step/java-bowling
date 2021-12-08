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
    void testContainingFirstStrike() {
        Pitches pitches = new Pitches();
        assertThat(pitches.containingFirstStrike()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.containingFirstStrike()).isTrue();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.containingFirstStrike()).isTrue();

        pitches = new Pitches();
        pitches.add(PITCH_GUTTER);
        assertThat(pitches.containingFirstStrike()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.containingFirstStrike()).isFalse();
    }

    @Test
    void testContainingSpare() {
        Pitches pitches = new Pitches();
        assertThat(pitches.containingSpare()).isFalse();

        pitches.add(PITCH_GUTTER);
        assertThat(pitches.containingSpare()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.containingSpare()).isTrue();

        pitches = new Pitches();
        pitches.add(PITCH_EIGHT_PINS);
        assertThat(pitches.containingSpare()).isFalse();

        pitches.add(PITCH_TWO_PINS);
        assertThat(pitches.containingSpare()).isTrue();
    }

    @Test
    void testContiningStrikeOrSpare() {
        Pitches pitches = new Pitches();
        assertThat(pitches.containingStrikeOrSpare()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.containingStrikeOrSpare()).isTrue();

        pitches.add(PITCH_GUTTER);
        assertThat(pitches.containingStrikeOrSpare()).isTrue();

        pitches = new Pitches();
        pitches.add(PITCH_GUTTER);
        assertThat(pitches.containingStrikeOrSpare()).isFalse();

        pitches.add(PITCH_STRIKE);
        assertThat(pitches.containingStrikeOrSpare()).isTrue();

        pitches = new Pitches();
        pitches.add(PITCH_TWO_PINS);
        assertThat(pitches.containingStrikeOrSpare()).isFalse();

        pitches.add(PITCH_EIGHT_PINS);
        assertThat(pitches.containingStrikeOrSpare()).isTrue();
    }
}
