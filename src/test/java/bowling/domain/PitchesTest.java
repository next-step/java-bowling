package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchesTest {
    @DisplayName("스트라이크 여부를 반환한다.")
    @Test
    void isStrike() {
        Pitches pitches = new Pitches();
        pitches.pitch(10);
        assertThat(pitches.isStrike()).isTrue();
    }

    @DisplayName("스페어 여부를 반환한다.")
    @Test
    void isSpare() {
        Pitches pitches = new Pitches();
        pitches.pitch(8);
        pitches.pitch(2);
        assertThat(pitches.isSpare()).isTrue();
    }

    @DisplayName("오픈 여부를 반환한다.")
    @Test
    void isOpen() {
        Pitches pitches = new Pitches();
        pitches.pitch(7);
        pitches.pitch(1);
        assertThat(pitches.isOpen()).isTrue();
    }
}
