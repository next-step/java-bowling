package bowling.bowlingdrawing.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

class PitchingsTest {

    @Test
    @DisplayName("nextPitching 반환")
    void nextPitching() {
        // given
        Pitchings pitchings = new Pitchings();
        int pins = 9;
        // when
        Pitching pitching = pitchings.nextPitching(pins);
        // then
        assertThat(pitching).isEqualTo(Pitching.of(pins));
    }

}