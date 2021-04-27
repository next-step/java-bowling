package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchesTest {
    @DisplayName("투구 횟수를 반환한다.")
    @Test
    void count() {
        Pitches pitches = new Pitches(Arrays.asList(new Pitch(8), new Pitch(2)));
        assertThat(pitches.count()).isEqualTo(2);
    }

    @DisplayName("투구 합산값을 반환한다.")
    @Test
    void sum() {
        Pitches pitches = new Pitches(Arrays.asList(new Pitch(8), new Pitch(2)));
        assertThat(pitches.sum()).isEqualTo(10);
    }
}
